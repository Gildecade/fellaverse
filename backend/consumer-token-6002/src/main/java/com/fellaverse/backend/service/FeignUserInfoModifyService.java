package com.fellaverse.backend.service;

import com.fellaverse.backend.config.ConsumerErrorDecoder;
import com.fellaverse.backend.config.FeignBasicAuthRequestInterceptor;
import com.fellaverse.backend.dto.UserRegisterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(value = "provider-gateway", contextId = "userInfo",
        configuration = {FeignBasicAuthRequestInterceptor.class, ConsumerErrorDecoder.class})
public interface FeignUserInfoModifyService {
    @PostMapping("api/auth/register")
    String register(UserRegisterDTO userRegisterDTO);

    @PostMapping("api/auth/resetPassword")
    String resetPassword(UserRegisterDTO userRegisterDTO);
}
