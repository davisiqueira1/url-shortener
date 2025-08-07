package com.siqueira.davi.url_shortener.controller;

import com.siqueira.davi.url_shortener.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping
public class RedirectController {
    private final UrlService service;

    public RedirectController(UrlService service) {
        this.service = service;
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Object> redirect(@PathVariable String shortCode) {
        Optional<String> longUrl = service.getLongUrl(shortCode);
        return longUrl
                .map(url -> ResponseEntity.status(HttpStatus.FOUND)
                        .location(URI.create(longUrl.get()))
                        .build())
                .orElse(ResponseEntity.notFound().build());
    }
}
