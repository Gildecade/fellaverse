package com.fellaverse.backend.jwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;

import javax.crypto.SecretKey;
import java.util.Map;

public interface JWTTokenService {
    // get encrypted key for current JWT data
    public SecretKey generateKey();

    // create Token and store user id and extra info
    public String createToken(String id, Map<String, Object> subject);

    public Jws<Claims> parseToken(String token) throws JwtException;

    public boolean verifyToken(String token);
    public String refreshToken(String token);
}
