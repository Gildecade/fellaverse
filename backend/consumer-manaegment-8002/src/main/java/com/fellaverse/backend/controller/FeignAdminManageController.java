package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.AdminDTO;
import com.fellaverse.backend.service.FeignAdminManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/management/admin")
public class FeignAdminManageController {
    @Autowired
    private FeignAdminManageService feignAdminManageService;

    @GetMapping("")
    public List<AdminDTO> findAllAdmin() {
        return feignAdminManageService.findAllAdmin();
    }

    @PostMapping("")
    public String addAdmin(AdminDTO adminDTO) {
        Object result = feignAdminManageService.addAdmin(adminDTO);
        Assert.isTrue("Add admin success!".equals(result), "Add admin failed!");
        return (String) result;
    }

    @PutMapping("")
    public String updateAdmin(AdminDTO adminDTO) {
        Object result = feignAdminManageService.updateAdmin(adminDTO);
        Assert.isTrue("Update admin success!".equals(result), "Update admin failed!");
        return (String) result;
    }

    @DeleteMapping("/{id}")
    public String deleteAdmin(@PathVariable("id") Long id) {
        Object result = feignAdminManageService.deleteAdmin(id);
        Assert.isTrue("Delete admin success!".equals(result), "Delete admin failed!");
        return (String) result;
    }

    @PutMapping("/{id}")
    public String updateRoles(@PathVariable("id") Long id, List<Long> roleIds) {
        Object result = feignAdminManageService.updateRoles(id, roleIds);
        Assert.isTrue("Update roles success!".equals(result), "Update roles failed!");
        return (String) result;
    }
}
