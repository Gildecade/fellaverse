package com.fellaverse.backend.service;

import com.fellaverse.backend.config.FeignBasicAuthRequestInterceptor;
import com.fellaverse.backend.dto.AdminDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(value = "provider-gateway", contextId = "adminManagement", configuration = FeignBasicAuthRequestInterceptor.class)
public interface FeignAdminManageService {
    @GetMapping("")
    List<AdminDTO> findAllAdmin();

    @PostMapping("")
    String addAdmin(AdminDTO adminDTO);

    @PutMapping("")
    String updateAdmin(AdminDTO adminDTO);

    @DeleteMapping("/{id}")
    String deleteAdmin(@PathVariable("id") Long id);

    @PutMapping("/{id}")
    String updateRoles(@PathVariable("id") Long id, List<Long> roleIds);
}
