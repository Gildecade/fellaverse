package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.FunctionIdNameDTO;
import com.fellaverse.backend.dto.UserBalanceStatusDTO;
import com.fellaverse.backend.dto.UserBasicInfoDTO;
import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.service.FeignUserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/management/user")
public class FeignUserManageController {
    @Autowired
    private FeignUserManageService feignUserManageService;
    @JWTCheckToken(role = {"SuperAdmin", "UserAdmin"})
    @PutMapping("")
    Boolean editUserBalanceStatus(@RequestBody UserBalanceStatusDTO userBalanceStatusDTO) {
        return feignUserManageService.editUserBalanceStatus(userBalanceStatusDTO);
    }
    @JWTCheckToken(role = {"SuperAdmin", "UserAdmin"})
    @GetMapping("/{userNameOrEmail}")
    Set<UserBasicInfoDTO> findUser(@PathVariable("userNameOrEmail") String userNameOrEmail) {
        return feignUserManageService.findUser(userNameOrEmail);
    }
    @JWTCheckToken(role = {"SuperAdmin", "UserAdmin"})
    @GetMapping("")
    Set<UserBasicInfoDTO> findAllUser() {
        return feignUserManageService.findAllUser();
    }

    @JWTCheckToken(role = {"SuperAdmin", "UserAdmin"})
    @GetMapping("func/{id}")
    public List<FunctionIdNameDTO> findFunctions(@PathVariable("id") Long id) {
        return feignUserManageService.findFunctions(id);
    }
    @JWTCheckToken(role = {"SuperAdmin", "UserAdmin"})
    @PutMapping("func/{id}")
    public boolean updateFunctions(@PathVariable("id") Long id, @RequestBody List<Long> functionIds) {
        return feignUserManageService.updateFunctions(id, functionIds);
    }
}
