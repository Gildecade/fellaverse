package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.FlashSaleOrderAddDTO;
import com.fellaverse.backend.dto.FlashSaleOrderDTO;
import com.fellaverse.backend.service.FeignFlashSaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/management/flashSaleOrder")
public class FeignFlashSaleOrderController {
    @Autowired
    private FeignFlashSaleOrderService feignFlashSaleOrderService;

    @GetMapping("")
    public List<FlashSaleOrderDTO> findAll() {
        return feignFlashSaleOrderService.findAll();
    }

    @PostMapping("")
    public String addLimitedProduct(@RequestBody @Validated FlashSaleOrderAddDTO flashSaleOrderAddDTO) {
        return feignFlashSaleOrderService.addLimitedProduct(flashSaleOrderAddDTO);
    }
}
