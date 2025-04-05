package com.example.mykube.controller;

import com.example.mykube.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @PostMapping("/shorten")
    public String generateShortUrl(@RequestBody String longUrl) throws Exception {
        //TODO: some input validation here
        return urlShortenerService.generateShortURl(longUrl);
    }
}
