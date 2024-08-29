package com.bichel.urlshortener.storage;

import com.bichel.urlshortener.exception.OriginalUrlNotFoundException;
import com.bichel.urlshortener.exception.OriginalsTheSameShortsAreDiffException;
import com.bichel.urlshortener.exception.ShortUrlNotFoundException;
import com.bichel.urlshortener.exception.ShortsTheSameOriginalsAreDiffException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public final class UrlMemoryStorage implements UrlStorage {
    //key is original url
    //value is short url
    private final Map<String, String> mapOriginalToShort;
    //key is short url
    //value is original url
    private final Map<String, String> mapShortToOriginal;

    public UrlMemoryStorage() {
        mapOriginalToShort = new HashMap<>();
        mapShortToOriginal = new HashMap<>();
    }

    public void addMapOriginalToShort(String originalUrl, String shortUrl) {
        if(mapOriginalToShort.containsKey(originalUrl) &&
            mapOriginalToShort.get(originalUrl).equals(shortUrl)) {
            return;
        }

        if(mapOriginalToShort.containsKey(originalUrl) &&
            !mapOriginalToShort.get(originalUrl).equals(shortUrl)) {
            throw new OriginalsTheSameShortsAreDiffException(
                    String.format("Original URL is '%s', short URL is '%s'", originalUrl, shortUrl));
        }
        if(mapShortToOriginal.containsKey(shortUrl) &&
                !mapShortToOriginal.get(shortUrl).equals(originalUrl)) {
            throw new ShortsTheSameOriginalsAreDiffException(
                    String.format("Original URL is '%s', short URL is '%s'", originalUrl, shortUrl));
        }

        this.mapOriginalToShort.put(originalUrl, shortUrl);
    }

    public void addMapShortToOriginal(String shortUrl, String originalUrl) {
        if(mapShortToOriginal.containsKey(shortUrl) &&
            mapShortToOriginal.get(shortUrl).equals(originalUrl)) {
            return;
        }

        if(mapOriginalToShort.containsKey(originalUrl) &&
                !mapOriginalToShort.get(originalUrl).equals(shortUrl)) {
            throw new OriginalsTheSameShortsAreDiffException(
                    String.format("Original URL is '%s', short URL is '%s'", originalUrl, shortUrl));
        }
        if(mapShortToOriginal.containsKey(shortUrl) &&
                !mapShortToOriginal.get(shortUrl).equals(originalUrl)) {
            throw new ShortsTheSameOriginalsAreDiffException(
                    String.format("Original URL is '%s', short URL is '%s'", originalUrl, shortUrl));
        }

        this.mapShortToOriginal.put(shortUrl, originalUrl);
    }

    public String getOriginalUrl(String shortUrl) {
        if(!mapShortToOriginal.containsKey(shortUrl)) {
            throw new ShortUrlNotFoundException(shortUrl);
        }

        return this.mapShortToOriginal.get(shortUrl);
    }

    public String getShortUrl(String originalUrl) {
        if(!mapOriginalToShort.containsKey(originalUrl)) {
            throw new OriginalUrlNotFoundException(originalUrl);
        }

        return this.mapOriginalToShort.get(originalUrl);
    }
}
