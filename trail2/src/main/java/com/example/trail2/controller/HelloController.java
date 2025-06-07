package com.example.trail2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(Authentication authentication) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return "Hello, " + username + "!";
    }
}
