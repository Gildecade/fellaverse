package com.fellaverse.backend.code;

import org.springframework.http.HttpStatus;

public enum ReturnCode {
    OK(HttpStatus.OK.value(), "OK"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error");

    private  int code;
    private String message;

    ReturnCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
