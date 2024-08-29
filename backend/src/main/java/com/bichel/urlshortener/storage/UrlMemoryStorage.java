package com.bichel.urlshortener.storage;

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

        //TODO: handle collision

        this.mapOriginalToShort.put(originalUrl, shortUrl);
    }

    public void addMapShortToOriginal(String shortUrl, String originalUrl) {
        if(mapShortToOriginal.containsKey(shortUrl) &&
            mapShortToOriginal.get(shortUrl).equals(originalUrl)) {
            return;
        }

        //TODO: handle collision

        this.mapShortToOriginal.put(shortUrl, originalUrl);
    }

    public String getOriginalUrl(String shortUrl) {
        return this.mapShortToOriginal.get(shortUrl);
    }

    public String getShortUrl(String originalUrl) {
        return this.mapOriginalToShort.get(originalUrl);
    }
}
