package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.LimitedProductDTO;
import com.fellaverse.backend.dto.LimitedProductPurchaseDTO;
import com.fellaverse.backend.service.FeignLimitedProductShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/limitedProduct")
public class FeignLimitedProductController {
    @Autowired
    private FeignLimitedProductShopService feignLimitedProductShopService;

    @GetMapping("")
    public List<LimitedProductDTO> findAll() {
        return feignLimitedProductShopService.findAll();
    }

    @GetMapping("/{id}")
    public void detail(@PathVariable("id") Long id) {
        feignLimitedProductShopService.detail(id);
    }

    @PostMapping("/purchase")
    public String purchase(@RequestBody LimitedProductPurchaseDTO purchaseDTO) {
        return feignLimitedProductShopService.purchase(purchaseDTO);
    }
}
