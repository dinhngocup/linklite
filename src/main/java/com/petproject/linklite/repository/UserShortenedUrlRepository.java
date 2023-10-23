package com.petproject.linklite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petproject.linklite.entity.UserShortenedUrl;

public interface UserShortenedUrlRepository extends JpaRepository<UserShortenedUrl, Integer> {}
