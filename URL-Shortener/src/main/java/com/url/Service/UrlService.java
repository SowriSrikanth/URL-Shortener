package com.url.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.url.Model.UrlMapping;
import com.url.Repository.UrlRepository;

@Service
public class UrlService {

    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Autowired
    private UrlRepository repository;

    public String shortenUrl(String longUrl) {
        UrlMapping url = new UrlMapping();
        url.setLongUrl(longUrl);
        repository.save(url);

        String shortCode = encodeBase62(url.getId());
        url.setShortCode(shortCode);
        repository.save(url);

        return shortCode;
    }

    public String getOriginalUrl(String shortCode) {
        return repository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("URL not found"))
                .getLongUrl();
    }

    private String encodeBase62(Long id) {
        StringBuilder sb = new StringBuilder();
        while (id > 0) {
            sb.append(BASE62.charAt((int) (id % 62)));
            id /= 62;
        }
        return sb.reverse().toString();
    }
}
