package com.fellaverse.backend.controller;

import com.fellaverse.backend.bean.Role;
import com.fellaverse.backend.dto.RoleDTO;
import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.mapper.AdminMapper;
import com.fellaverse.backend.mapper.RoleMapper;
import com.fellaverse.backend.service.RoleManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/management/role")
public class RoleManageController {
    @Autowired
    private RoleManageService roleManageService;
    @Autowired
    private RoleMapper roleMapper;

    @JWTCheckToken(role = "SuperAdmin")
    @GetMapping("")
    public List<RoleDTO> findAllRoles() {
        return roleManageService.findAllRoles().stream().map(roleMapper::toDto).collect(Collectors.toList());
    }

    @JWTCheckToken(role = "SuperAdmin")
    @GetMapping("/admin/{id}")
    public List<String> findRoleNameByAdminId(@PathVariable("id") Long adminId) {
        return roleManageService.findRoleNameByAdminId(adminId);
    }
}
