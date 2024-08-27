package com.bichel.urlshortener.exception;

public class OriginalUrlIsMissingException extends RuntimeException {
    public OriginalUrlIsMissingException(String errorMessage) {
        super(errorMessage);
    }
    public OriginalUrlIsMissingException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}