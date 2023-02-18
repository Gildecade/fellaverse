package com.fellaverse.backend.controller;

import com.fellaverse.backend.annotation.UniqueUser;
import com.fellaverse.backend.bean.User;
import com.fellaverse.backend.dto.UserRegisterDTO;
import com.fellaverse.backend.enumerator.UserStatus;
import com.fellaverse.backend.jwt.service.PasswordEncryptService;
import com.fellaverse.backend.mapper.UserRegisterMapper;
import com.fellaverse.backend.service.UserInfoModifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth/*")
public class UserInfoModifyController {
    @Autowired
    private UserInfoModifyService userInfoModifyService;
    @Autowired
    private PasswordEncryptService passwordEncryptService;
    @Autowired
    private UserRegisterMapper userRegisterMapper;

    @PostMapping("/register")
    public String register(@Validated @UniqueUser @RequestBody UserRegisterDTO userRegisterDTO) {
        userRegisterDTO.setPassword(passwordEncryptService.getEncryptedPassword(userRegisterDTO.getPassword()));
        User user = userRegisterMapper.toEntity(userRegisterDTO);
        user.setStatus(UserStatus.valueOf("NORMAL")).setWallet(1000L);
        User register = this.userInfoModifyService.register(user);
        this.userInfoModifyService.addFunctions(register);
        return "Register success!";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@Validated @RequestBody UserRegisterDTO userRegisterDTO) {
        userRegisterDTO.setPassword(passwordEncryptService.getEncryptedPassword(userRegisterDTO.getPassword()));
        User user = userRegisterMapper.toEntity(userRegisterDTO);
        return this.userInfoModifyService.forgetPassword(user);
    }
}
