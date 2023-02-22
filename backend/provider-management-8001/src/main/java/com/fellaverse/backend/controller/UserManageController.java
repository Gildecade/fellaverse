package com.fellaverse.backend.controller;

import com.fellaverse.backend.bean.User;
import com.fellaverse.backend.dto.UserBalanceStatusDTO;
import com.fellaverse.backend.dto.UserBasicInfoDTO;
import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.mapper.UserBasicInfoMapper;
import com.fellaverse.backend.service.UserManageService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/management/user")
public class UserManageController {
    @Autowired
    private UserManageService userManageService;
    @Autowired
    private UserBasicInfoMapper userBasicInfoMapper;
    @JWTCheckToken(role = {"SuperAdmin", "UserAdmin"})
    @PutMapping("")
    public Boolean editUserBalanceStatus(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) UserBalanceStatusDTO userBalanceStatusDTO){
        return userManageService.updateUserBalanceStatus(userBalanceStatusDTO);
    }
    @JWTCheckToken(role = {"SuperAdmin", "UserAdmin"})
    @GetMapping("/{userNameOrEmail}")
    public Set<UserBasicInfoDTO> findUser(@PathVariable("userNameOrEmail") String userNameOrEmail) {
        Set<User> usersFromEmail = new HashSet<>(userManageService.findUserByEmail(userNameOrEmail));
        Set<User> usersFromName = new HashSet<>(userManageService.findUserByUsername(userNameOrEmail));
        Set<User> usersFound = new HashSet<>(usersFromEmail);
        usersFound.addAll(usersFromName);

        return usersFound.stream().map(userBasicInfoMapper::toDto).collect(Collectors.toSet());
    }

    @JWTCheckToken(role = {"SuperAdmin", "UserAdmin"})
    @GetMapping("")
    public Set<UserBasicInfoDTO> findAllUser() {
        return userManageService.findAllUser().stream().map(userBasicInfoMapper::toDto).collect(Collectors.toSet());
    }
}
