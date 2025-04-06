package com.example.mykube.controller;

import com.example.mykube.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @PostMapping("/shorten")
    public String generateShortUrl(@RequestBody String longUrl) throws Exception {
        //TODO: some input validation here
        return urlShortenerService.generateShortURl(longUrl);
    }

    @GetMapping("/{shortUrl}")
    public RedirectView getOriginalUrl(@PathVariable String shortUrl) {
        String originalUrl = urlShortenerService.getOriginalUrl(shortUrl);

        if (originalUrl != null) {
            return new RedirectView(originalUrl);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Short URL not found");
    }

    @GetMapping("/health")
    public boolean isAlive() {
        return true;
    }

    @GetMapping("/ready")
    public boolean isReady() {
        return true;
    }
}
