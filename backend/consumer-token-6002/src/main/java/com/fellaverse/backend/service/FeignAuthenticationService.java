package com.fellaverse.backend.service;

import com.fellaverse.backend.config.FeignBasicAuthRequestInterceptor;
import com.fellaverse.backend.dto.UserLoginDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(value = "provider-gateway", contextId = "token", configuration = FeignBasicAuthRequestInterceptor.class)
public interface FeignAuthenticationService {
    @PostMapping("/api/auth/login")
    Object login(UserLoginDTO userLoginDTO);
}
