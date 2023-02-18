package com.fellaverse.backend.controller;

import com.fellaverse.backend.bean.User;
import com.fellaverse.backend.dto.UserBalanceStatusDTO;
import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.service.AdminManageUserInfoService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/management/user")
public class AdminManageUserInfoController {
    @Autowired
    private AdminManageUserInfoService adminManageUserInfoService;
    @JWTCheckToken(role = "UserAdmin")
    @PutMapping("/editUser")
    public Boolean editUserBalanceStatus(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) UserBalanceStatusDTO userBalanceStatusDTO){
        return adminManageUserInfoService.updateUserBalanceStatus(userBalanceStatusDTO);
    }
    @JWTCheckToken(role = "UserAdmin")
    @GetMapping("/findUser/{userNameOrEmail}")
    public Set<User> findUser(@RequestBody @PathVariable String userNameOrEmail) {
        Set<User> usersFromEmail = new HashSet<>(adminManageUserInfoService.findUserByEmail(userNameOrEmail));
        Set<User> usersFromName = new HashSet<>(adminManageUserInfoService.findUserByUsername(userNameOrEmail));

        Set<User> users = new HashSet<>(usersFromEmail);
        users.addAll(usersFromName);
        return users;
    }



}
