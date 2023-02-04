package com.fellaverse.backend.jwt.service;

import com.fellaverse.backend.jwt.config.EncryptConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordEncryptServiceImpl implements PasswordEncryptService{
    @Autowired
    private EncryptConfig encryptConfig;
    private static MessageDigest MD5_DIGEST;
    private static final Base64.Encoder BASE64_ENCODER = Base64.getEncoder();

    static {
        try {
            MD5_DIGEST = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getEncryptedPassword(String password) {
        String saltPassword = "{" + this.encryptConfig.getSalt() + "}" + password;
        for (int i = 0; i < this.encryptConfig.getRepeat(); i++) {
            saltPassword = BASE64_ENCODER.encodeToString(MD5_DIGEST.digest(saltPassword.getBytes()));
        }
        return saltPassword;
    }
}
