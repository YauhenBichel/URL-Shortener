package com.bichel.urlshortener.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalControllerAdviceExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = OriginalUrlIsMissingException.class)
    public final ResponseEntity<Object> handleException(OriginalUrlIsMissingException ex, WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ErrorCode.ORIGINAL_URL_IS_MISSING);
        errorResponse.setMessage(ex.getMessage());

        logger.error(ex);

        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
