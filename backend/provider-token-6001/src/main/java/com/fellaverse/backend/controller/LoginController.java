package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.UserLoginDTO;
import com.fellaverse.backend.jwt.service.JWTTokenService;
import com.fellaverse.backend.jwt.service.PasswordEncryptService;
import com.fellaverse.backend.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth/*")  // any requests under token will be proceeded
public class LoginController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private PasswordEncryptService passwordEncryptService;
    @Autowired
    private JWTTokenService jwtTokenService;

    @PostMapping("/login")  // whole url is localhost:port/api/auth/login, only allow post method
    // @Validated to enable parameters validation for login, @RequestBody to acquire json object from request body
    public Object login(@Validated @RequestBody UserLoginDTO userLoginDTO) {
        // encrypt password from cleartext to ciphertext
        userLoginDTO.setPassword(this.passwordEncryptService.getEncryptedPassword(userLoginDTO.getPassword()));
        Map<String, Object> result = this.authenticationService.login(userLoginDTO);
        if ((Boolean)result.get("status")) {
            return this.jwtTokenService.createToken(result.get("id").toString(), (Map<String, Object>) result.get("resource"));
        }
        return null;
    }

    @RequestMapping("parse")  // only for test to present token content
    public Object parseToken(String token) {
        return this.jwtTokenService.parseToken(token);
    }
}
