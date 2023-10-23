package com.petproject.linklite.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user_shortened_url")
@Data
public class UserShortenedUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String originalUrl;
    private long userId;
    private long urlId;
}
