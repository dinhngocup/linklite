package com.petproject.linklite.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petproject.linklite.dto.UserUrlTransactionDTO;
import com.petproject.linklite.entity.Url;
import com.petproject.linklite.service.UrlService;
import com.petproject.linklite.service.UserUrlTransactionService;

@RestController
@RequestMapping("/url")
public class UrlController {
    private final Logger log = LoggerFactory.getLogger(UrlController .class);
    private final UrlService urlService;
    private final UserUrlTransactionService userUrlTransactionService;

    @Autowired
    public UrlController(UrlService urlService, UserUrlTransactionService userUrlTransactionService) {
        this.urlService = urlService;
        this.userUrlTransactionService = userUrlTransactionService;
    }

    @GetMapping
    public List<Url> findAll() {
        return urlService.findAll();
    }

    @GetMapping("/{shortenedUrl}")
    public Object findByShortenUrl(@PathVariable String shortenedUrl) {
        Url url = urlService.findByShortenedUrl(shortenedUrl);
        if (url != null) {
            return url.getOriginalUrl();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public Object add(@RequestBody UserUrlTransactionDTO dto) {
        try {
            Url url = userUrlTransactionService.addNewUrl(dto.getUrl(), dto.getUserId());
            return new ResponseEntity<>(url, HttpStatus.CREATED);
        } catch (Exception ex) {
            log.error("Failed to add new url {} {}.", ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
