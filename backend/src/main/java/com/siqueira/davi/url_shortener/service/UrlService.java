package com.siqueira.davi.url_shortener.service;

import com.siqueira.davi.url_shortener.dto.UrlResponseDTO;
import com.siqueira.davi.url_shortener.model.UrlModel;
import com.siqueira.davi.url_shortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlService {
    private final UrlRepository repository;

    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    public UrlResponseDTO shortenUrl(String longUrl) {
        // Checks if the longUrl is already mapped to a shortCode.
        Optional<UrlModel> existing = repository.findByLongUrl(longUrl);

        if (existing.isPresent()) {
            return new UrlResponseDTO(existing.get().getShortCode());
        }

        // If not, creates it.
        String shortCode;

        do {
            shortCode = UUID.randomUUID().toString().substring(0, 8);
        } while (repository.findByShortCode(shortCode).isPresent());

        UrlModel url = repository.save(new UrlModel(longUrl, shortCode, Date.from(LocalDateTime.now().plusDays(7).atZone(ZoneId.systemDefault()).toInstant())));

        return new UrlResponseDTO(url.getShortCode());
    }

    public Optional<String> getLongUrl(String shortCode) {
        return repository.findByShortCode(shortCode)
                .map(UrlModel::getLongUrl);
    }
}
