package com.petproject.linklite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petproject.linklite.entity.UserShortenedUrl;
import com.petproject.linklite.repository.UserShortenedUrlRepository;

@Service
public class UserShortenedUrlService {
    UserShortenedUrlRepository userShortenedUrlRepository;

    @Autowired
    public UserShortenedUrlService(UserShortenedUrlRepository userShortenedUrlRepository) {
        this.userShortenedUrlRepository = userShortenedUrlRepository;
    }

    public void add(UserShortenedUrl userShortenedUrl) {
        userShortenedUrlRepository.save(userShortenedUrl);
    }
}
