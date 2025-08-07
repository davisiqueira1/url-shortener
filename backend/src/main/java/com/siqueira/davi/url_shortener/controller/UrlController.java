package com.siqueira.davi.url_shortener.controller;

import com.siqueira.davi.url_shortener.dto.UrlRequestDTO;
import com.siqueira.davi.url_shortener.dto.UrlResponseDTO;
import com.siqueira.davi.url_shortener.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UrlController {
    private final UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }

    @PostMapping("/shorten")
    public ResponseEntity<UrlResponseDTO> shortenUrl(@RequestBody UrlRequestDTO dto) {
        UrlResponseDTO responseDTO = service.shortenUrl(dto.longUrl());
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
