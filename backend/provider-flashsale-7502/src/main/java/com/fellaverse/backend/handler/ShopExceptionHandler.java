package com.fellaverse.backend.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ShopExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String exception(DataIntegrityViolationException e) {
        log.error("SQL exception: " + e.getMessage());
        log.error("Exception type: " + e.getClass().getName());
        return e.getMessage();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String exception(HttpMessageNotReadableException e) {
        log.error("Bad request exception: " + e.getMessage());
        log.error("Exception type: " + e.getClass().getName());
        return e.getMessage();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String exception(IllegalArgumentException e) {
        log.error("Illegal argumentException exception: " + e.getMessage());
        log.error("Exception type: " + e.getClass().getName());
        return e.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exception(Exception e) {
        log.error("Global exception: " + e.getMessage());
        log.error("Exception type: " + e.getClass().getName());
        return e.getMessage();
    }
}
