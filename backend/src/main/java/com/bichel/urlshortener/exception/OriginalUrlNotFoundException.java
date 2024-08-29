package com.bichel.urlshortener.exception;

public class OriginalUrlNotFoundException extends RuntimeException {
    public OriginalUrlNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
