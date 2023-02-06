package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.UserDTO;
import com.fellaverse.backend.jwt.service.JWTTokenService;
import com.fellaverse.backend.jwt.service.PasswordEncryptService;
import com.fellaverse.backend.service.AuthenticationService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/token/*")  // any requests under token will be proceeded
public class LoginController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private PasswordEncryptService passwordEncryptService;
    @Autowired
    private JWTTokenService jwtTokenService;

    @PostMapping("/create")  // whole url is localhost:port/api/token/create, only allow post method
    // @Validated to enable parameters validation for login, @RequestBody to acquire json object from request body
    public Object login(@Validated(value = ValidGroup.Crud.Query.class) @RequestBody UserDTO userDTO) {
        // encrypt password from cleartext to ciphertext
        userDTO.setPassword(this.passwordEncryptService.getEncryptedPassword(userDTO.getPassword()));
        Map<String, Object> result = this.authenticationService.login(userDTO);
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
