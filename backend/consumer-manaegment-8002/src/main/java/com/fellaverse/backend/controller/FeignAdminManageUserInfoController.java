package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.UserBalanceStatusDTO;
import com.fellaverse.backend.dto.UserBasicInfoDTO;
import com.fellaverse.backend.service.FeignAdminManageUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/management/user")
public class FeignAdminManageUserInfoController {
    @Autowired
    private FeignAdminManageUserInfoService feignAdminManageUserInfoService;
    @PutMapping("")
    Boolean editUserBalanceStatus(UserBalanceStatusDTO userBalanceStatusDTO) {
        return feignAdminManageUserInfoService.editUserBalanceStatus(userBalanceStatusDTO);
    }
    @GetMapping("/{userNameOrEmail}")
    Set<UserBasicInfoDTO> findUser(@PathVariable("userNameOrEmail") String userNameOrEmail) {
        return feignAdminManageUserInfoService.findUser(userNameOrEmail);
    }
    @GetMapping("")
    Set<UserBasicInfoDTO> findAllUser() {
        return feignAdminManageUserInfoService.findAllUser();
    }
}
