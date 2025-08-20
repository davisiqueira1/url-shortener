package com.siqueira.davi.url_shortener.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "url")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlModel {

    @Id
    private String id;

    private String longUrl;
    private String shortCode;

    @Indexed(name = "expirationDate_ttl", expireAfter = "0s")
    private Date expirationDate;
}
