package com.fellaverse.backend.service;

import com.fellaverse.backend.config.ConsumerErrorDecoder;
import com.fellaverse.backend.config.FeignBasicAuthRequestInterceptor;
import com.fellaverse.backend.dto.ProListDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Component
@FeignClient(value = "provider-gateway", contextId = "adminManageProList", path = "/api/management/prolist",
        configuration = {FeignBasicAuthRequestInterceptor.class, ConsumerErrorDecoder.class})
public interface FeignProListManageService {
    @PostMapping("")
    Boolean addProList(ProListDTO proListDTO);

    @PutMapping("")
    Boolean editProList(ProListDTO proListDTO);

    @DeleteMapping("/{id}")
    Boolean deleteProList(@PathVariable("id") Long id);

    @GetMapping("")
    Set<ProListDTO> findAllProList();
}
