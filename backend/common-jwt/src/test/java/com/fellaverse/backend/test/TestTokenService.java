package com.fellaverse.backend.test;

import com.fellaverse.backend.jwt.StartJWTConfiguration;
import com.fellaverse.backend.jwt.service.JWTTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = StartJWTConfiguration.class)
public class TestTokenService {
    @Autowired
    private JWTTokenService jwtTokenService;
    private String jwt = "eyJtb2R1bGUiOiJKV1QtVEVTVCIsImFsZyI6IkhTMjU2In0.eyJzaXRlIjoid3d3LmZlbGxhdmVyc2UuY29tIiwianRpIjoibGVhcm4tMTExIiwiaWF0IjoxNjc0NDQwODkwLCJpc3MiOiJnY2giLCJzdWIiOiJ7XCJyaWRzXCI6XCJVU0VSO0FETUlOO0RFUFQ7RU1QO1JPTEVcIixcIm5hbWVcIjpcImdjaFwiLFwibWlkXCI6XCIxMTFcIn0iLCJleHAiOjE2NzQ0NDEwMTB9.W9dVwxa1keW6u4TfQzEMyR3qlwXwJNKG8P9WGe3V_xw";

    @Test
    public void createToken() {
        HashMap<String , Object> map = new HashMap<>();
        map.put("mid", "111");
        map.put("name", "gch");
        map.put("rids", "USER;ADMIN;DEPT;EMP;ROLE");
        String id = "learn-111";
        System.out.println(this.jwtTokenService.createToken(id, map));
    }

    @Test
    public void parseToken() {
        Jws<Claims> claimsJws = this.jwtTokenService.parseToken(jwt);
        System.out.println("JWT signature: " + claimsJws.getSignature());
        JwsHeader headers = claimsJws.getHeader();
        headers.forEach((headerName, headerValue) -> {
            System.out.println("JWT header: " + headerName + " = " + headerValue);
        });
        Claims contents = claimsJws.getBody();
        contents.forEach((contentName, contentValue) -> {
            System.out.println("JWT content: " + contentName + " = " + contentValue);
        });
    }

    @Test
    public void verifyJWT() {
        System.out.println("JWT verification: " + this.jwtTokenService.verifyToken(jwt));
    }

    @Test
    public void refreshJWT() {
        System.out.println("JWT refresh: " + this.jwtTokenService.refreshToken(jwt));
    }
}
