package com.fellaverse.backend.controller;

import com.fellaverse.backend.bean.User;
import com.fellaverse.backend.dto.UserBalanceStatusDTO;
import com.fellaverse.backend.dto.UserBasicInfoDTO;
import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.mapper.UserBasicInfoMapper;
import com.fellaverse.backend.service.AdminManageUserInfoService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/management/user")
public class AdminManageUserInfoController {
    @Autowired
    private AdminManageUserInfoService adminManageUserInfoService;
    @Autowired
    private UserBasicInfoMapper userBasicInfoMapper;
    @JWTCheckToken(role = {"SuperAdmin", "UserAdmin"})
    @PutMapping("")
    public Boolean editUserBalanceStatus(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) UserBalanceStatusDTO userBalanceStatusDTO){
        return adminManageUserInfoService.updateUserBalanceStatus(userBalanceStatusDTO);
    }
    @JWTCheckToken(role = {"SuperAdmin", "UserAdmin"})
    @GetMapping("/{userNameOrEmail}")
    public Set<UserBasicInfoDTO> findUser(@PathVariable String userNameOrEmail) {
        Set<User> usersFromEmail = new HashSet<>(adminManageUserInfoService.findUserByEmail(userNameOrEmail));
        Set<User> usersFromName = new HashSet<>(adminManageUserInfoService.findUserByUsername(userNameOrEmail));
        Set<User> usersFound = new HashSet<>(usersFromEmail);
        usersFound.addAll(usersFromName);

        return usersFound.stream().map(userBasicInfoMapper::toDto).collect(Collectors.toSet());
    }

    @JWTCheckToken(role = {"SuperAdmin", "UserAdmin"})
    @GetMapping("")
    public Set<UserBasicInfoDTO> findAllUser() {
        return adminManageUserInfoService.findAllUser().stream().map(userBasicInfoMapper::toDto).collect(Collectors.toSet());
    }
}
