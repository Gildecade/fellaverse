package com.fellaverse.backend.controller;

import com.fellaverse.backend.bean.LimitedProduct;
import com.fellaverse.backend.dto.LimitedProductDTO;
import com.fellaverse.backend.enumerator.ProductStatus;
import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.mapper.LimitedProductMapper;
import com.fellaverse.backend.service.LimitedProductManageService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/management/limitedProduct")
public class LimitedProductManageController {
    @Autowired
    private LimitedProductManageService limitedProductManageService;
    @Autowired
    private LimitedProductMapper limitedProductMapper;

    @JWTCheckToken(role = {"SuperAdmin", "ShopAdmin"})
    @GetMapping("")
    public List<LimitedProductDTO> findAll() {
        return limitedProductManageService.findAll().stream().map(limitedProductMapper::toDto).collect(Collectors.toList());
    }

    @JWTCheckToken(role = {"SuperAdmin", "ShopAdmin"})
    @GetMapping("/{id}")
    public LimitedProductDTO findById(@PathVariable("id") Long id) {
        return limitedProductMapper.toDto(limitedProductManageService.findById(id));
    }

    @JWTCheckToken(role = {"SuperAdmin", "ShopAdmin"})
    @PostMapping("")
    public String addLimitedProduct(@RequestBody @Validated({ValidGroup.Crud.Create.class}) LimitedProductDTO limitedProductDTO) {
        limitedProductManageService.addLimitedProduct(limitedProductMapper.toEntity(limitedProductDTO));
        return "Add limited product success!";
    }

    @JWTCheckToken(role = {"SuperAdmin", "ShopAdmin"})
    @PutMapping("")
    public String updateLimitedProduct(@RequestBody @Validated({ValidGroup.Crud.Update.class}) LimitedProductDTO limitedProductDTO) {
        LimitedProduct product = limitedProductManageService.findById(limitedProductDTO.getId());
        limitedProductManageService.updateLimitedProduct(limitedProductMapper.partialUpdate(limitedProductDTO, product));
        return "Update limited product success!";
    }

    @JWTCheckToken(role = {"SuperAdmin", "ShopAdmin"})
    @DeleteMapping("/{id}")
    public String deleteLimitedProduct(@PathVariable("id") Long id) {
        limitedProductManageService.deleteLimitedProduct(id);
        return "Delete limited product success!";
    }

    @JWTCheckToken(role = {"SuperAdmin", "ShopAdmin"})
    @GetMapping("/status")
    public ProductStatus[] findAllStatus() {
        return ProductStatus.class.getEnumConstants();
    }
}
