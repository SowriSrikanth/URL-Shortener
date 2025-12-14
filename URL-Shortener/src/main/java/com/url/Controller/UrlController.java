package com.url.Controller;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.url.Service.UrlService;
import com.url.dto.UrlRequest;

@RestController
public class UrlController {

    @Autowired
    private UrlService service;

    @PostMapping("/api/shorten")
    public Map<String, String> shorten(@RequestBody UrlRequest request) {
        String code = service.shortenUrl(request.getLongUrl());
        return Map.of("shortUrl", "http://localhost:8080/" + code);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(@PathVariable("code") String code) {
        String longUrl = service.getOriginalUrl(code);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(longUrl))
                .build();
    }
}