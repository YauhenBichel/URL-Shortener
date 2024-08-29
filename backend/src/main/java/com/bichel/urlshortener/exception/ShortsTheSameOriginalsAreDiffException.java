package com.bichel.urlshortener.exception;

public class ShortsTheSameOriginalsAreDiffException extends RuntimeException {
    public ShortsTheSameOriginalsAreDiffException(String errorMessage) {
        super(errorMessage);
    }

    public ShortsTheSameOriginalsAreDiffException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
