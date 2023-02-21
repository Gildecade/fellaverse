package com.fellaverse.backend.controller;

import com.fellaverse.backend.bean.Admin;
import com.fellaverse.backend.dto.AdminDTO;
import com.fellaverse.backend.dto.AdminFindAllDTO;
import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.jwt.service.PasswordEncryptService;
import com.fellaverse.backend.mapper.AdminFindAllMapper;
import com.fellaverse.backend.mapper.AdminMapper;
import com.fellaverse.backend.repository.AdminRoleRepository;
import com.fellaverse.backend.service.AdminManageService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    private AdminRoleRepository adminRoleRepository;
    @Autowired
    private PasswordEncryptService passwordEncryptService;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminFindAllMapper adminFindAllMapper;

    @JWTCheckToken(role = "SuperAdmin")
    @GetMapping("")
    public List<AdminFindAllDTO> findAllAdmin() {
        return adminManageService.findAllAdmin().stream().map(adminFindAllMapper::toAdminFindAllDTO).collect(Collectors.toList());
    }

    @JWTCheckToken(role = "SuperAdmin")
    @GetMapping("/conditions")
    public List<AdminDTO> findAdminByCondition(@RequestBody AdminDTO adminDTO) {
        return adminManageService.findAdminByCondition(adminMapper.toEntity(adminDTO)).stream().map(adminMapper::toDto).collect(Collectors.toList());
    }

    @JWTCheckToken(role = "SuperAdmin")
    @PostMapping("")
    public String addAdmin(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) AdminDTO adminDTO) {
        adminDTO.setPassword(passwordEncryptService.getEncryptedPassword(adminDTO.getPassword()));
        adminManageService.addAdmin(adminMapper.toEntity(adminDTO));
        return "Add admin success!";
    }

    @JWTCheckToken(role = "SuperAdmin")
    @PutMapping("")
    public String updateAdmin(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) AdminDTO adminDTO) {
        if (adminDTO.getPassword()!=null) {
            adminDTO.setPassword(passwordEncryptService.getEncryptedPassword(adminDTO.getPassword()));
        }
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
    @Transactional
    public String updateRoles(@PathVariable("id") Long id, @RequestBody List<Long> roleIds) {
//        List<Role> roles = roleManageService.findRoleByIds(roleIds);
//        Admin admin = adminManageService.findAdminById(id);
//        admin.getRoles().clear();
//        admin.getRoles().addAll(roles);
//        adminManageService.updateAdmin(admin);
        List<Long> existingRoles = adminRoleRepository.findById_AdminId(id)
                .stream().map((adminRoleInfo -> adminRoleInfo.getRole().getId())).toList();
        for (Long roleId : roleIds) {
            if (!existingRoles.contains(roleId)) {
                adminRoleRepository.insert(id, roleId);
            }
        }
        for (Long existingRole : existingRoles) {
            if (!roleIds.contains(existingRole)) {
                adminRoleRepository.delete(id, existingRole);
            }
        }
        return "Update roles success!";
    }
}
