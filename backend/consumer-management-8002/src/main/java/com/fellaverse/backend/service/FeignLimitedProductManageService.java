package com.fellaverse.backend.service;

import com.fellaverse.backend.config.ConsumerErrorDecoder;
import com.fellaverse.backend.config.FeignBasicAuthRequestInterceptor;
import com.fellaverse.backend.dto.LimitedProductDTO;
import com.fellaverse.backend.enumerator.ProductStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(value = "provider-gateway", contextId = "limitedProductManagement", path = "/api/management/limitedProduct",
        configuration = {FeignBasicAuthRequestInterceptor.class, ConsumerErrorDecoder.class})
public interface FeignLimitedProductManageService {
    @GetMapping("")
    List<LimitedProductDTO> findAll();

    @GetMapping("/{id}")
    LimitedProductDTO findById(@PathVariable("id") Long id);

    @PostMapping("")
    String addLimitedProduct(LimitedProductDTO limitedProductDTO);

    @DeleteMapping("/{id}")
    String deleteLimitedProduct(@PathVariable("id") Long id);

    @PutMapping("")
    String updateLimitedProduct(LimitedProductDTO limitedProductDTO);

    @GetMapping("/status")
    ProductStatus[] findAllStatus();
}
