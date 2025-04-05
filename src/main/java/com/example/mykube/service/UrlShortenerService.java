package com.example.mykube.service;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

@Service
public class UrlShortenerService {
    public String generateShortURl(String longUrl) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(longUrl.getBytes(StandardCharsets.UTF_8));

        // Encode using Base64 and take first 6 characters
        return Base64.getUrlEncoder().withoutPadding().encodeToString(hash).substring(0, 6);
    }
}
