package com.example.chatop.exception;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

/**
 * Create class with @ControllerAdvice
 * Execute exception processing
 * Use @ExceptionHandler
 */

@ControllerAdvice
public class ResponseStatusExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<Object> handleBadRequestException(RequestException exception){

        ErrorResponse error = new ErrorResponse();
        error.setStatus(exception.getErrorRequest().getCode());
        error.setMessage(exception.getErrorRequest().getMessage());
        error.setDetails(List.of(exception.getMessage()));

        return ResponseEntity
                .status(exception.getErrorRequest().getCode())
                .body(error);
    }

}
