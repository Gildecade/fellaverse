package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.AdminDTO;
import com.fellaverse.backend.dto.AdminFindAllDTO;
import com.fellaverse.backend.service.FeignAdminManageService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
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
        return feignAdminManageService.addAdmin(adminDTO);
    }

    @PutMapping("")
    public String updateAdmin(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) AdminDTO adminDTO) {
        return feignAdminManageService.updateAdmin(adminDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteAdmin(@PathVariable("id") Long id) {
        return feignAdminManageService.deleteAdmin(id);
    }

    @PutMapping("/{id}")
    public String updateRoles(@PathVariable("id") Long id, @RequestBody List<Long> roleIds) {
        return feignAdminManageService.updateRoles(id, roleIds);
    }
}
