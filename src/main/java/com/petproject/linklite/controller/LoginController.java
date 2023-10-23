package com.petproject.linklite.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost() {

        return "loginPost";
    }

}
