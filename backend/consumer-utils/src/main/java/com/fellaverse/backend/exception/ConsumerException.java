package com.fellaverse.backend.exception;

public class ConsumerException extends RuntimeException{
    private final Integer code;

    public ConsumerException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
