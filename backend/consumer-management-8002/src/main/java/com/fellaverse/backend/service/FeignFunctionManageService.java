package com.fellaverse.backend.service;

import com.fellaverse.backend.config.ConsumerErrorDecoder;
import com.fellaverse.backend.config.FeignBasicAuthRequestInterceptor;
import com.fellaverse.backend.dto.FunctionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Component
@FeignClient(value = "provider-gateway", contextId = "adminManageFunction", path = "/api/management/function",
        configuration = {FeignBasicAuthRequestInterceptor.class, ConsumerErrorDecoder.class})
public interface FeignFunctionManageService {
    @PostMapping("")
    Boolean addFunction(FunctionDTO functionDTO);

    @PutMapping("")
    Boolean editFunction(FunctionDTO functionDTO);

    @DeleteMapping("/{id}")
    Boolean deleteFunction(@PathVariable("id") Long id);

    @GetMapping("")
    Set<FunctionDTO> findAllFunction();
    @GetMapping("/{keyword}")
    Set<FunctionDTO> findFunctionByKeyword(@PathVariable("keyword") String keyword);
}
