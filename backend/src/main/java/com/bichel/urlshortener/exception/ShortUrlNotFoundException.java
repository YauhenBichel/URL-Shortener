package com.bichel.urlshortener.exception;

public class ShortUrlNotFoundException extends RuntimeException {
    public ShortUrlNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
