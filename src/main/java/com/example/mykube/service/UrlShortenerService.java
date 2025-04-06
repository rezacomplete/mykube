package com.example.mykube.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class UrlShortenerService {

    private final Map<String, String> internalCache = new HashMap<>();

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String generateShortURl(String longUrl) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(longUrl.getBytes(StandardCharsets.UTF_8));

        // Encode using Base64 and take first 6 characters
        String shortUrl = Base64.getUrlEncoder().withoutPadding().encodeToString(hash).substring(0, 6);

        // Save mapping to Redis
//        redisTemplate.opsForValue().set(shortUrl, longUrl);

        internalCache.put(shortUrl, longUrl);
        return shortUrl;
    }


    public String getOriginalUrl(String shortUrl) {
//        return redisTemplate.opsForValue().get(shortUrl);
        return internalCache.get(shortUrl);
    }
}
