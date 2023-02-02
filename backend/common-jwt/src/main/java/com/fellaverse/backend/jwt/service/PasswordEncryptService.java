package com.fellaverse.backend.jwt.service;

public interface PasswordEncryptService {
    public String getEncryptedPassword(String password);
}
