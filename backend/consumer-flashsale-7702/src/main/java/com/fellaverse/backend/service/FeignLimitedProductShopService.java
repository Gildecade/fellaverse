package com.fellaverse.backend.service;

import com.fellaverse.backend.config.ConsumerErrorDecoder;
import com.fellaverse.backend.config.FeignBasicAuthRequestInterceptor;
import com.fellaverse.backend.dto.LimitedProductDTO;
import com.fellaverse.backend.dto.LimitedProductPayDTO;
import com.fellaverse.backend.dto.LimitedProductPurchaseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
@FeignClient(value = "provider-gateway", contextId = "shop-limitedProduct", path = "/api/limitedProduct",
        configuration = {FeignBasicAuthRequestInterceptor.class, ConsumerErrorDecoder.class})
public interface FeignLimitedProductShopService {
    @GetMapping("")
    List<LimitedProductDTO> findAll();

    @GetMapping("/{id}")
    void detail(@PathVariable("id") Long id);

    @PostMapping("/purchase")
    String purchase(@RequestBody LimitedProductPurchaseDTO purchaseDTO);

    @PostMapping("/pay")
    String pay(@RequestBody LimitedProductPayDTO limitedProductPayDTO);
}
