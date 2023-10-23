package com.petproject.linklite.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.petproject.linklite.entity.Url;
import com.petproject.linklite.entity.UserShortenedUrl;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserUrlTransactionService {
    private final Logger log = LoggerFactory.getLogger(UserUrlTransactionService.class);

    //    private static final String HOST = "http://localhost:8080/url/";
    private final UrlService urlService;
    private final UserShortenedUrlService userShortenedUrlService;

    public UserUrlTransactionService(UrlService urlService, UserShortenedUrlService userShortenedUrlService) {
        this.urlService = urlService;
        this.userShortenedUrlService = userShortenedUrlService;
    }

    public Url addNewUrl(Url newUrl, long userId) {
        Url url = createUrl(newUrl);
        createUserShortenedUrl(url, userId);

        return url;
    }

    private Url createUrl(Url newUrl) {
        newUrl.setShortenedUrl(hashOriginalUrl(newUrl.getOriginalUrl()));

        LocalDateTime now = LocalDateTime.now().plusDays(1);
        Date out = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        newUrl.setExpiredDate(out);

        Url url = urlService.add(newUrl);

        log.info("Add new url successfully: {}", url);
        return url;
    }

    private void createUserShortenedUrl(Url url, long userId) {
        UserShortenedUrl userShortenedUrl = new UserShortenedUrl();
        userShortenedUrl.setOriginalUrl(url.getOriginalUrl());
        userShortenedUrl.setUserId(userId);
        userShortenedUrl.setUrlId(url.getId());

        userShortenedUrlService.add(userShortenedUrl);
    }

    private String hashOriginalUrl(String originalUrl) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = messageDigest.digest(originalUrl.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error("SHA-256 algorithm not available.");
            return originalUrl;
        }
    }
}
