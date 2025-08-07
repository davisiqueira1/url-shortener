package com.siqueira.davi.url_shortener.service;

import com.siqueira.davi.url_shortener.dto.UrlResponseDTO;
import com.siqueira.davi.url_shortener.model.UrlModel;
import com.siqueira.davi.url_shortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlService {
    private final UrlRepository repository;

    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    public UrlResponseDTO shortenUrl(String longUrl) {
        // Checks if the longUrl is already mapped to a shortUrl.
        Optional<UrlModel> existing = repository.findByLongUrl(longUrl);

        if (existing.isPresent()) {
            return new UrlResponseDTO(existing.get().getShortUrl());
        }

        // If not, creates it.
        String shortUrl;

        do {
            shortUrl = UUID.randomUUID().toString().substring(0, 8);
        } while (repository.findByShortUrl(shortUrl).isPresent());

        UrlModel url = repository.save(new UrlModel(longUrl, shortUrl, LocalDateTime.now().plusDays(7)));

        return new UrlResponseDTO(url.getShortUrl());
    }
}
