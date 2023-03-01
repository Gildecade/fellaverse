package com.fellaverse.backend.controller;

import com.fellaverse.backend.bean.Function;
import com.fellaverse.backend.bean.User;
import com.fellaverse.backend.bean.UserFunction;
import com.fellaverse.backend.dto.FunctionIdNameDTO;
import com.fellaverse.backend.dto.UserBalanceStatusDTO;
import com.fellaverse.backend.dto.UserBasicInfoDTO;
import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.mapper.FunctionMapper;
import com.fellaverse.backend.mapper.UserBasicInfoMapper;
import com.fellaverse.backend.projection.FunctionInfo;
import com.fellaverse.backend.repository.FunctionRepository;
import com.fellaverse.backend.repository.UserFunctionRepository;
import com.fellaverse.backend.service.UserManageService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/management/user")
public class UserManageController {
    @Autowired
    private UserManageService userManageService;

    @Autowired
    private UserFunctionRepository userFunctionRepository;

    @Autowired
    private FunctionRepository functionRepository;
    @Autowired
    private UserBasicInfoMapper userBasicInfoMapper;

    @Autowired
    private FunctionMapper functionMapper;
    @JWTCheckToken(role = {"SuperAdmin", "UserAdmin"})
    @PutMapping("")
    //public Boolean editUserBalanceStatus(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) UserBalanceStatusDTO userBalanceStatusDTO){
    public Boolean editUserBalanceStatus(@RequestBody UserBalanceStatusDTO userBalanceStatusDTO){
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

    @JWTCheckToken(role = {"SuperAdmin", "UserAdmin"})
    @GetMapping("func/{id}")
    public List<FunctionIdNameDTO> findFunctions(@PathVariable("id") Long id) {
        return functionRepository.findByUserId(id).stream().map(functionMapper::toDto).collect(Collectors.toList());
    }
    @JWTCheckToken(role = {"SuperAdmin", "UserAdmin"})
    @PutMapping("func/{id}")
    @Transactional
    public boolean updateFunctions(@PathVariable("id") Long id, @RequestBody List<Long> functionIds) {
        List<Long> existingFunctions = userFunctionRepository.findById_UserId(id).stream().map(userFunction -> userFunction.getFunction().getId()).toList();

        for (Long functionId : functionIds) {
            if (!existingFunctions.contains(functionId)) {
                userFunctionRepository.insert(id, functionId);
            }
        }
        for (Long existingFunction : existingFunctions) {
            if (!functionIds.contains(existingFunction)) {
                userFunctionRepository.delete(id, existingFunction);
            }
        }
        return true;
    }
}
