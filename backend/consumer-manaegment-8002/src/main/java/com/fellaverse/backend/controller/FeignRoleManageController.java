package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.RoleDTO;
import com.fellaverse.backend.service.FeignRoleManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/management/role")
public class FeignRoleManageController {
    @Autowired
    private FeignRoleManageService feignRoleManageService;

    @GetMapping("")
    public List<RoleDTO> findAllRoles() {
        return feignRoleManageService.findAllRoles();
    }

    @GetMapping("/admin/{id}")
    public List<String> findRoleNameByAdminId(@PathVariable("id") Long adminId) {
        return feignRoleManageService.findRoleNameByAdminId(adminId);
    }
}
