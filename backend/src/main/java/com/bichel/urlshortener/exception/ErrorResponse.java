package com.bichel.urlshortener.exception;

import lombok.Data;

import java.io.Serializable;

@Data
public class ErrorResponse implements Serializable {
    private ErrorCode error;
    private String message;
}
