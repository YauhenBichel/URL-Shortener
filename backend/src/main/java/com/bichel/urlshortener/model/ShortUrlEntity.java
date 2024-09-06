package com.bichel.urlshortener.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@RedisHash("ShortUrl")
@Data
public class ShortUrlEntity implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @ToString.Include
    private String id; //shortUrl
    @EqualsAndHashCode.Include
    @ToString.Include
    private String originalUrl;
}
