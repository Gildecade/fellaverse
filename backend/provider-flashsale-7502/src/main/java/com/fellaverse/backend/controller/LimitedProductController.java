package com.fellaverse.backend.controller;

import com.fellaverse.backend.annotation.ExistingProduct;
import com.fellaverse.backend.dto.LimitedProductDTO;
import com.fellaverse.backend.dto.LimitedProductPayDTO;
import com.fellaverse.backend.dto.LimitedProductPurchaseDTO;
import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.mapper.LimitedProductMapper;
import com.fellaverse.backend.service.LimitedProductShopService;
import com.fellaverse.backend.util.SnowflakeIdWorker;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/limitedProduct")
@Slf4j
public class LimitedProductController {
    @Autowired
    private LimitedProductShopService limitedProductShopService;

    @Autowired
    private LimitedProductMapper mapper;

    @GetMapping("")
    public List<LimitedProductDTO> findAll() {
        return limitedProductShopService.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public void detail(@PathVariable("id") @ExistingProduct Long id) {
        limitedProductShopService.cacheQuantityById(id);
    }

    @JWTCheckToken(function = "buy")
    @PostMapping("/purchase")
    public String purchase(@RequestBody @Validated LimitedProductPurchaseDTO purchaseDTO) {
        // reduce stock quantity in Redis, reduce MySQL only after paid
        Assert.isTrue(limitedProductShopService.purchase(purchaseDTO.getId(), purchaseDTO.getQuantity()), "Insufficient stock!");
        Long orderId = SnowflakeIdWorker.generateId();
        limitedProductShopService.placeOrder(purchaseDTO, orderId);
        return orderId.toString();
    }

    @JWTCheckToken(function = "buy")
    @PostMapping("/pay")
    public String pay(@RequestBody @Validated LimitedProductPayDTO limitedProductPayDTO) {
        limitedProductShopService.pay(limitedProductPayDTO.getOrderId(), limitedProductPayDTO.getUserId());
        return "Pay successfully!";
    }
}
