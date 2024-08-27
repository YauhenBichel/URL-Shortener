package com.bichel.urlshortener.vo;

import java.util.UUID;

public class UrlShortenerResponseVO {

    private UUID id;
    private String urlShortener;
    private String urlOriginal;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUrlShortener() {
        return this.urlShortener;
    }

    public void setUrlShortener(String urlShortener) {
        this.urlShortener = urlShortener;
    }

    public String getUrlOriginal() {
        return this.urlOriginal;
    }

    public void setUrlOriginal(String urlOriginal) {
        this.urlOriginal = urlOriginal;
    }
}
