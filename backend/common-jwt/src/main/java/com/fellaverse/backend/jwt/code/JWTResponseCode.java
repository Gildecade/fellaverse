package com.fellaverse.backend.jwt.code;

import org.springframework.http.HttpStatus;

public enum JWTResponseCode {
    TOKEN_EXPIRED(HttpStatus.FORBIDDEN.value(), "Token expired, access denied"),
    TOKEN_UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "Unauthorized token, access denied");
    private  int code;
    private String message;

    JWTResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String toString() {
        return "{\"code\":" + this.code + ",\"message\":" + this.message+"}";
    }
}
