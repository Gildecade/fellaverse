package com.fellaverse.backend.controller;

import com.fellaverse.backend.annotation.ExistingProduct;
import com.fellaverse.backend.dto.LimitedProductDTO;
import com.fellaverse.backend.dto.LimitedProductPayDTO;
import com.fellaverse.backend.dto.LimitedProductPurchaseDTO;
import com.fellaverse.backend.service.FeignLimitedProductShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
    public void detail(@PathVariable("id") @ExistingProduct Long id) {
        feignLimitedProductShopService.detail(id);
    }

    @PostMapping("/purchase")
    public String purchase(@RequestBody @Validated LimitedProductPurchaseDTO purchaseDTO) {
        return feignLimitedProductShopService.purchase(purchaseDTO);
    }

    @PostMapping("/pay")
    public String pay(@RequestBody @Validated LimitedProductPayDTO limitedProductPayDTO) {
        return feignLimitedProductShopService.pay(limitedProductPayDTO);
    }
}
