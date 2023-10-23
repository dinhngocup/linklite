package com.petproject.linklite.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petproject.linklite.entity.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    List<Url> findAllByExpiredDateIsGreaterThanEqual(Date currentDate);
    Url findByOriginalUrl(String url);
    Url findByShortenedUrl(String url);
}
