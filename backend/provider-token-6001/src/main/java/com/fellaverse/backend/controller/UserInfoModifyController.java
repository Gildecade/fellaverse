package com.fellaverse.backend.controller;

import com.fellaverse.backend.jwt.service.PasswordEncryptService;
import com.fellaverse.backend.service.UserInfoModifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/userInfo/*")
public class UserInfoModifyController {
    @Autowired
    private UserInfoModifyService userInfoModifyService;
    @Autowired
    private PasswordEncryptService passwordEncryptService;

}
