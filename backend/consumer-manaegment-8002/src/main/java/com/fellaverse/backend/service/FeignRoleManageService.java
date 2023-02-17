package com.fellaverse.backend.service;

import com.fellaverse.backend.config.FeignBasicAuthRequestInterceptor;
import com.fellaverse.backend.dto.RoleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(value = "provider-gateway", contextId = "roleManagement",
        configuration = FeignBasicAuthRequestInterceptor.class, path = "/api/management/role")
public interface FeignRoleManageService {
    @GetMapping("")
    List<RoleDTO> findAllRoles();

    @GetMapping("/admin/{id}")
    List<String> findRoleNameByAdminId(@PathVariable("id") Long adminId);
}
