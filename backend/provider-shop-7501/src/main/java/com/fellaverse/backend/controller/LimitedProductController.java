package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.LimitedProductDTO;
import com.fellaverse.backend.dto.LimitedProductPurchaseDTO;
import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.mapper.LimitedProductMapper;
import com.fellaverse.backend.service.BalanceService;
import com.fellaverse.backend.service.LimitedProductShopService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
    @RequestMapping("/api/shop/limitedProduct")
public class LimitedProductController {
    @Autowired
    private LimitedProductShopService limitedProductShopService;

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private LimitedProductMapper mapper;

    @GetMapping("")
    public List<LimitedProductDTO> findAll() {
        return limitedProductShopService.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public LimitedProductDTO detail(@PathVariable("id") Long id) {
        return mapper.toDto(limitedProductShopService.findById(id).setQuantity(limitedProductShopService.cacheQuantityById(id)));
    }

    @JWTCheckToken(function = "buy")
    @PostMapping("/purchase")
    public String purchase(@RequestBody LimitedProductPurchaseDTO purchaseDTO) {
        float amount = limitedProductShopService.findById(purchaseDTO.getId()).getPrice() * purchaseDTO.getQuantity();
        Assert.isTrue(balanceService.checkBalance(purchaseDTO.getUserId(), amount), "Insufficient balance!");
        // update user account
        balanceService.updateBalance(purchaseDTO.getUserId(), -1 * amount); // note that price should be negative
        // reduce stock quantity
        Assert.isTrue(limitedProductShopService.purchase(purchaseDTO.getId(), purchaseDTO.getQuantity()), "Insufficient stock!");
        return "Purchase successfully!";
    }
}
