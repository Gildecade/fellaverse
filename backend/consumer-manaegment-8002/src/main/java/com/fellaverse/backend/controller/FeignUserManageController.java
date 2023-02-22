package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.UserBalanceStatusDTO;
import com.fellaverse.backend.dto.UserBasicInfoDTO;
import com.fellaverse.backend.service.FeignUserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/management/user")
public class FeignUserManageController {
    @Autowired
    private FeignUserManageService feignUserManageService;
    @PutMapping("")
    Boolean editUserBalanceStatus(UserBalanceStatusDTO userBalanceStatusDTO) {
        return feignUserManageService.editUserBalanceStatus(userBalanceStatusDTO);
    }
    @GetMapping("/{userNameOrEmail}")
    Set<UserBasicInfoDTO> findUser(@PathVariable("userNameOrEmail") String userNameOrEmail) {
        return feignUserManageService.findUser(userNameOrEmail);
    }
    @GetMapping("")
    Set<UserBasicInfoDTO> findAllUser() {
        return feignUserManageService.findAllUser();
    }
}
