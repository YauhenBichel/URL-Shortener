package com.bichel.urlshortener.storage;

public interface UrlStorage {
    void addMapOriginalToShort(String originalUrl, String shortUrl);
    void addMapShortToOriginal(String shortUrl, String originalUrl);
    String getOriginalUrl(String shortUrl);
    String getShortUrl(String originalUrl);
}
