package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.UserLoginDTO;
import com.fellaverse.backend.service.FeignAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class FeignLoginController {
    @Autowired
    private FeignAuthenticationService feignAuthenticationService;

    @PostMapping("/login")
    public Object login(@Validated @RequestBody UserLoginDTO userLoginDTO) {
        Object result = feignAuthenticationService.login(userLoginDTO);
        Assert.notNull(result, "Login Failed!");
        return result;
    }
}
