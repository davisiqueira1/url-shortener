package com.siqueira.davi.url_shortener.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "url")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UrlModel {

    @Id
    private String id;

    private String longUrl;
    private String shortUrl;

    private LocalDateTime expirationDate;

    public UrlModel(String longUrl, String shortUrl, LocalDateTime expirationDate) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.expirationDate = expirationDate;
    }
}
