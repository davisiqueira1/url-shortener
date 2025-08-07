package com.siqueira.davi.url_shortener.repository;

import com.siqueira.davi.url_shortener.model.UrlModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends MongoRepository<UrlModel, String> {
    Optional<UrlModel> findByLongUrl(String longUrl);
    Optional<UrlModel> findByShortCode(String shortCode);
}
