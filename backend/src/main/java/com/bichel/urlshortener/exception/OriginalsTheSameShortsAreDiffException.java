package com.bichel.urlshortener.exception;

public class OriginalsTheSameShortsAreDiffException extends RuntimeException {
    public OriginalsTheSameShortsAreDiffException(String errorMessage) {
        super(errorMessage);
    }

    public OriginalsTheSameShortsAreDiffException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
