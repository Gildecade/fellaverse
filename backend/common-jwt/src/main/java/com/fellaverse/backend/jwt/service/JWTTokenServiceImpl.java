package com.fellaverse.backend.jwt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fellaverse.backend.jwt.config.JWTConfig;
import io.jsonwebtoken.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class JWTTokenServiceImpl implements JWTTokenService{

    @Autowired
    private ObjectMapper objectMapper;  // data processing object of Jackson
    @Autowired
    private JWTConfig jwtConfig;
    @Value("${spring.application.name}")
    private String applicationName;
    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    @Override
    public SecretKey generateKey() {
        byte [] encodeKey = Base64.decodeBase64(Base64.encodeBase64String(this.jwtConfig.getSecret().getBytes()));
        SecretKey key = new SecretKeySpec(encodeKey, 0, encodeKey.length, "HmacSHA256");
        return key;
    }

    @Override
    public String createToken(String id, Map<String, Object> subject) {
        // Every key has an expiration
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + this.jwtConfig.getExpire() * 1000);
        Map<String, Object> claims = new HashMap<>();  // extra info
        claims.put("site", "www.fellaverse.com");
        HashMap<String , Object> headers = new HashMap<>();
        // Many modules will use this token, so we need to record who they are
        headers.put("module", this.applicationName);
        JwtBuilder builder = null;
        try {
            builder = Jwts.builder()  // init builder
                    .setClaims(claims)
                    .setHeader(headers)
                    .setId(id)
                    .setIssuedAt(nowDate)
                    .setIssuer(this.jwtConfig.getIssuer())
                    .setSubject(this.objectMapper.writeValueAsString(subject))  // transform data to json
                    .setExpiration(expireDate)
                    .signWith(this.generateKey(), signatureAlgorithm);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return builder.compact();
    }

    @Override
    public Jws<Claims> parseToken(String token) throws JwtException {
        if (this.verifyToken(token)) {
            return Jwts.parserBuilder().setSigningKey(this.generateKey()).build().parseClaimsJws(token);
        }
        return null;
    }

    @Override
    public boolean verifyToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(this.generateKey()).build().parseClaimsJws(token).getBody();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String refreshToken(String token) {
        if (this.verifyToken(token)) {
            Jws<Claims> claimsJws = this.parseToken(token);
            try {
                return this.createToken(claimsJws.getBody().getId(), this.objectMapper.readValue(claimsJws.getBody().getSubject(), Map.class));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
