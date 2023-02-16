package com.fellaverse.backend.controller;

import com.fellaverse.backend.bean.Admin;
import com.fellaverse.backend.bean.Role;
import com.fellaverse.backend.dto.AdminDTO;
import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.mapper.AdminMapper;
import com.fellaverse.backend.service.AdminManageService;
import com.fellaverse.backend.service.RoleManageService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/management/admin")
public class AdminManageController {
    @Autowired
    private AdminManageService adminManageService;
    @Autowired
    private RoleManageService roleManageService;
    @Autowired
    private AdminMapper adminMapper;

    @JWTCheckToken(role = "SuperAdmin")
    @GetMapping("")
    public List<AdminDTO> findAllAdmin() {
        return adminManageService.findAllAdmin().stream().map(adminMapper::toDto).collect(Collectors.toList());
    }

    @JWTCheckToken(role = "SuperAdmin")
    @GetMapping("/conditions")
    public List<AdminDTO> findAdminByCondition(@RequestBody AdminDTO adminDTO) {
        return adminManageService.findAdminByCondition(adminMapper.toEntity(adminDTO)).stream().map(adminMapper::toDto).collect(Collectors.toList());
    }

    @JWTCheckToken(role = "SuperAdmin")
    @PostMapping("")
    public String addAdmin(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) AdminDTO adminDTO) {
        adminManageService.addAdmin(adminMapper.toEntity(adminDTO));
        return "Add admin success!";
    }

    @JWTCheckToken(role = "SuperAdmin")
    @PutMapping("")
    public String updateAdmin(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) AdminDTO adminDTO) {
        Admin admin = adminManageService.findAdminById(adminDTO.getId());
        adminManageService.updateAdmin(adminMapper.partialUpdate(adminDTO, admin));
        return "Update admin success!";
    }

    @JWTCheckToken(role = "SuperAdmin")
    @DeleteMapping("/{id}")
    public String deleteAdmin(@PathVariable("id") Long id) {
        adminManageService.deleteAdmin(id);
        return "Delete admin success!";
    }

    @JWTCheckToken(role = "SuperAdmin")
    @PutMapping("/{id}")
    public String updateRoles(@PathVariable("id") Long id, @RequestBody List<Long> roleIds) {
        List<Role> roles = roleManageService.findRoleByIds(roleIds);
        Admin admin = adminManageService.findAdminById(id);
        admin.getRoles().clear();
        admin.getRoles().addAll(roles);
        adminManageService.updateAdmin(admin);
        return "Update roles success!";
    }
}
