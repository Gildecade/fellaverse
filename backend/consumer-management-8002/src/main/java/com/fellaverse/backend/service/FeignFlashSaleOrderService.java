package com.fellaverse.backend.service;

import com.fellaverse.backend.config.ConsumerErrorDecoder;
import com.fellaverse.backend.config.FeignBasicAuthRequestInterceptor;
import com.fellaverse.backend.dto.FlashSaleOrderAddDTO;
import com.fellaverse.backend.dto.FlashSaleOrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Component
@FeignClient(value = "provider-gateway", contextId = "flashSaleOrderManagement", path = "/api/management/flashSaleOrder",
        configuration = {FeignBasicAuthRequestInterceptor.class, ConsumerErrorDecoder.class})
public interface FeignFlashSaleOrderService {
    @GetMapping("")
    List<FlashSaleOrderDTO> findAll();

    @PostMapping("")
    String addLimitedProduct(FlashSaleOrderAddDTO flashSaleOrderAddDTO);
}
