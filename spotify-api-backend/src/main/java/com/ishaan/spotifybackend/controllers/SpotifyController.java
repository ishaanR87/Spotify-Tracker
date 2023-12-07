package com.ishaan.spotifybackend.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class SpotifyController {
    
    @GetMapping("/login")
    public String login() {
        return new SomeData();
    }
    
}