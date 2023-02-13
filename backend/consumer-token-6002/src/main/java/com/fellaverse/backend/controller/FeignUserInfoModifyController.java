package com.fellaverse.backend.controller;

import com.fellaverse.backend.annotation.UniqueUser;
import com.fellaverse.backend.dto.UserRegisterDTO;
import com.fellaverse.backend.service.FeignUserInfoModifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class FeignUserInfoModifyController {
    @Autowired
    private FeignUserInfoModifyService feignUserInfoModifyService;

    @PostMapping("/register")
    public String register(@Validated @UniqueUser @RequestBody UserRegisterDTO userRegisterDTO) {
        return feignUserInfoModifyService.register(userRegisterDTO);
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@Validated @RequestBody UserRegisterDTO userRegisterDTO) {
        String message = feignUserInfoModifyService.resetPassword(userRegisterDTO);
        Assert.isTrue("Password reset successfully!".equals(message), message);
        return message;
    }
}
