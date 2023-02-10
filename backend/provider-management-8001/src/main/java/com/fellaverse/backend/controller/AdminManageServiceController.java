package com.fellaverse.backend.controller;

import com.fellaverse.backend.bean.Admin;
import com.fellaverse.backend.dto.AdminDTO;
import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.mapper.AdminMapper;
import com.fellaverse.backend.service.AdminManageService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/adminManagement")
public class AdminManageServiceController {
    @Autowired
    private AdminManageService adminManageService;
    @Autowired
    private AdminMapper adminMapper;

    @JWTCheckToken(role = "SuperAdmin")
    @GetMapping("")
    public List<Admin> findAdminByCondition(@RequestBody AdminDTO adminDTO) {
        return adminManageService.findAdminByCondition(adminMapper.toEntity(adminDTO));
    }

    @JWTCheckToken(role = "SuperAdmin")
    @PostMapping("")
    public String addAdmin(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) AdminDTO adminDTO) {
        adminManageService.addAdmin(adminMapper.toEntity(adminDTO));
        return "Add admin success";
    }

    @JWTCheckToken(role = "SuperAdmin")
    @PutMapping("")
    public String updateAdmin(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) AdminDTO adminDTO) {
        Admin admin = adminManageService.findAdminById(adminDTO.getId());
        adminManageService.updateAdmin(adminMapper.partialUpdate(adminDTO, admin));
        return "Update admin success";
    }

    @JWTCheckToken(role = "SuperAdmin")
    @DeleteMapping("/{id}")
    public String deleteAdmin(@PathVariable("id") Long id) {
        adminManageService.deleteAdmin(id);
        return "Delete admin success";
    }
}
