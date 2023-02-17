package com.fellaverse.backend.service;

import com.fellaverse.backend.config.ConsumerErrorDecoder;
import com.fellaverse.backend.config.FeignBasicAuthRequestInterceptor;
import com.fellaverse.backend.dto.AdminDTO;
import com.fellaverse.backend.dto.AdminFindAllDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(value = "provider-gateway", contextId = "adminManagement", path = "/api/management/admin",
        configuration = {FeignBasicAuthRequestInterceptor.class, ConsumerErrorDecoder.class})
public interface FeignAdminManageService {
    @GetMapping("")
    List<AdminFindAllDTO> findAllAdmin();

    @PostMapping("")
    String addAdmin(AdminDTO adminDTO);

    @PutMapping("")
    String updateAdmin(AdminDTO adminDTO);

    @DeleteMapping("/{id}")
    String deleteAdmin(@PathVariable("id") Long id);

    @PutMapping("/{id}")
    String updateRoles(@PathVariable("id") Long id, List<Long> roleIds);
}
