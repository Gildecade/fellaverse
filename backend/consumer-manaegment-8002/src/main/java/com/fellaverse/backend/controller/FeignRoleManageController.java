package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.RoleDTO;
import com.fellaverse.backend.service.FeignRoleManageService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("")
    public String addRole(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) RoleDTO roleDTO) {
        String result = feignRoleManageService.addRole(roleDTO);
        Assert.isTrue("Add role success!".equals(result), "Add role failed!");
        return result;
    }

    @PutMapping("")
    public String updateRole(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) RoleDTO roleDTO) {
        String result = feignRoleManageService.updateRole(roleDTO);
        Assert.isTrue("Update role success!".equals(result), "Update role failed!");
        return result;
    }

    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable("id") Long id) {
        String result = feignRoleManageService.deleteRole(id);
        Assert.isTrue("Delete role success!".equals(result), "Delete role failed!");
        return result;
    }
}
