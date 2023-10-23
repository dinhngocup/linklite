package com.petproject.linklite.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petproject.linklite.entity.Url;
import com.petproject.linklite.repository.UrlRepository;

@Service
public class UrlService {
    UrlRepository urlRepository;

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public List<Url> findAll() {
        return urlRepository.findAllByExpiredDateIsGreaterThanEqual(new Date());
    }

    public Url findByOriginalUrl(String originalUrl) {
        Url url = urlRepository.findByOriginalUrl(originalUrl);
        if (url != null && url.getExpiredDate().after(new Date())) {
            return url;
        }
        return null;
    }

    public Url findByShortenedUrl(String shortenedUrl) {
        Url url = urlRepository.findByShortenedUrl(shortenedUrl);
        if (url != null && url.getExpiredDate().after(new Date())) {
            return url;
        }
        return null;
    }

    public Url add(Url url) {
        return urlRepository.save(url);
    }

    public void update(Url url) {

    }

    public void delete(long url) {

    }
}
