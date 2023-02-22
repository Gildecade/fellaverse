package com.fellaverse.backend.service;

import com.fellaverse.backend.config.ConsumerErrorDecoder;
import com.fellaverse.backend.config.FeignBasicAuthRequestInterceptor;
import com.fellaverse.backend.dto.UserBalanceStatusDTO;
import com.fellaverse.backend.dto.UserBasicInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Component
@FeignClient(value = "provider-gateway", contextId = "adminManageUserInfo", path = "/api/management/user",
        configuration = {FeignBasicAuthRequestInterceptor.class, ConsumerErrorDecoder.class})
public interface FeignUserManageService {
    @PutMapping("")
    Boolean editUserBalanceStatus(UserBalanceStatusDTO userBalanceStatusDTO);
    @GetMapping("/{userNameOrEmail}")
    Set<UserBasicInfoDTO> findUser(@PathVariable("userNameOrEmail") String userNameOrEmail);
    @GetMapping("")
    Set<UserBasicInfoDTO> findAllUser();
}
