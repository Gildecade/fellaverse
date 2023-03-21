package com.fellaverse.backend.service;

import com.fellaverse.backend.dto.FlashSaleOrderUserDTO;
import com.fellaverse.backend.config.ConsumerErrorDecoder;
import com.fellaverse.backend.config.FeignBasicAuthRequestInterceptor;
import com.fellaverse.backend.dto.UserProfileDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
@FeignClient(value = "provider-gateway", contextId = "profile", path = "/api/user",
        configuration = {FeignBasicAuthRequestInterceptor.class, ConsumerErrorDecoder.class})
public interface FeignUserService {

    @GetMapping("/{id}")
    UserProfileDTO findAllInfo(@PathVariable("id") Long id);

    @GetMapping("/{id}/flashSaleOrder")
    List<FlashSaleOrderUserDTO> findAllFlashSaleOrder(@PathVariable("id") Long id);
}
