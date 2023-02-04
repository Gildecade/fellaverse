package com.fellaverse.backend.jwt.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fellaverse.backend.jwt.service.JWTTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class JWTMemberDataService {
    @Autowired
    private JWTTokenService jwtTokenService;
    @Autowired
    private ObjectMapper objectMapper;  // parse JSON to Map

    public Map<String, String> headers(String token) {  // parse headers by JWT
        Jws<Claims> claimsJws = this.jwtTokenService.parseToken(token);
        Map<String, String> headers = new HashMap<>();
        claimsJws.getHeader().forEach((key, value) -> {
            // transform JWT header to Map<String, String>
            headers.put(key.toString(), value.toString());
        });
        return headers;
    }

    public Set<String> roles(String token) {
        Jws<Claims> claimsJws = this.jwtTokenService.parseToken(token);
        try {
            Map<String, List<String>> map = this.objectMapper.readValue(claimsJws.getBody().getSubject(), Map.class);
            Set<String> roles = new HashSet<>();
            roles.addAll(map.get("roles"));
            return roles;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Set<String> functions(String token) {
        Jws<Claims> claimsJws = this.jwtTokenService.parseToken(token);
        try {
            Map<String, List<String>> map = this.objectMapper.readValue(claimsJws.getBody().getSubject(), Map.class);
            Set<String> functions = new HashSet<>();
            functions.addAll(map.get("functions"));
            return functions;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String id(String token) {
        Jws<Claims> claimsJws = this.jwtTokenService.parseToken(token);
        return claimsJws.getBody().getId();
    }

    public String getToken(HttpServletRequest request, String name) {
        String token = request.getParameter(name);
        // cannot get data from parameters
        if (token == null || "".equals(token)) {
            // get data from header
            token = request.getHeader(name);
        }
        return token;
    }
}
