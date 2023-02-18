package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.AdminDTO;
import com.fellaverse.backend.dto.AdminFindAllDTO;
import com.fellaverse.backend.service.FeignAdminManageService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/management/admin")
public class FeignAdminManageController {
    @Autowired
    private FeignAdminManageService feignAdminManageService;

    @GetMapping("")
    public List<AdminFindAllDTO> findAllAdmin() {
        return feignAdminManageService.findAllAdmin();
    }

    @PostMapping("")
    public String addAdmin(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) AdminDTO adminDTO) {
        String result = feignAdminManageService.addAdmin(adminDTO);
        Assert.isTrue("Add admin success!".equals(result), "Add admin failed!");
        return result;
    }

    @PutMapping("")
    public String updateAdmin(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) AdminDTO adminDTO) {
        String result = feignAdminManageService.updateAdmin(adminDTO);
        Assert.isTrue("Update admin success!".equals(result), "Update admin failed!");
        return result;
    }

    @DeleteMapping("/{id}")
    public String deleteAdmin(@PathVariable("id") Long id) {
        String result = feignAdminManageService.deleteAdmin(id);
        Assert.isTrue("Delete admin success!".equals(result), "Delete admin failed!");
        return result;
    }

    @PutMapping("/{id}")
    public String updateRoles(@PathVariable("id") Long id, @RequestBody List<Long> roleIds) {
        String result = feignAdminManageService.updateRoles(id, roleIds);
        Assert.isTrue("Update roles success!".equals(result), "Update roles failed!");
        return result;
    }
}
