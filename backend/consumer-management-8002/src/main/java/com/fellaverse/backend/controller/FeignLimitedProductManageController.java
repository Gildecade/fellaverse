package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.LimitedProductDTO;
import com.fellaverse.backend.service.FeignLimitedProductManageService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/management/limitedProduct")
public class FeignLimitedProductManageController {
    @Autowired
    private FeignLimitedProductManageService feignLimitedProductManageService;

    @GetMapping("")
    public List<LimitedProductDTO> findAll() {
        return feignLimitedProductManageService.findAll();
    }

    @GetMapping("/{id}")
    public LimitedProductDTO findById(@PathVariable("id") Long id) {
        return feignLimitedProductManageService.findById(id);
    }

    @PostMapping("")
    public String addLimitedProduct(@RequestBody @Validated({ValidGroup.Crud.Create.class}) LimitedProductDTO limitedProductDTO) {
        return feignLimitedProductManageService.addLimitedProduct(limitedProductDTO);
    }

    @PutMapping("")
    public String updateLimitedProduct(@RequestBody @Validated({ValidGroup.Crud.Update.class}) LimitedProductDTO limitedProductDTO) {
        return feignLimitedProductManageService.updateLimitedProduct(limitedProductDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteLimitedProduct(@PathVariable("id") Long id) {
        return feignLimitedProductManageService.deleteLimitedProduct(id);
    }
}
