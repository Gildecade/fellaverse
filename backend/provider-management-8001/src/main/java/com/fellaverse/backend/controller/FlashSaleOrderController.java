package com.fellaverse.backend.controller;

import com.fellaverse.backend.bean.FlashSaleOrder;
import com.fellaverse.backend.bean.LimitedProduct;
import com.fellaverse.backend.bean.User;
import com.fellaverse.backend.dto.FlashSaleOrderAddDTO;
import com.fellaverse.backend.dto.FlashSaleOrderDTO;
import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.mapper.FlashSaleOrderMapper;
import com.fellaverse.backend.service.FlashSaleOrderService;
import com.fellaverse.backend.service.LimitedProductManageService;
import com.fellaverse.backend.service.UserManageService;
import com.fellaverse.backend.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
/**
 * Controller for flash sale order management, including add and find.
 */
@RestController
@RequestMapping("/api/management/flashSaleOrder")
public class FlashSaleOrderController {
    @Autowired
    private FlashSaleOrderService flashSaleOrderService;

    @Autowired
    private UserManageService userManageService;

    @Autowired
    private LimitedProductManageService limitedProductManageService;

    @Autowired
    private FlashSaleOrderMapper mapper;

    @JWTCheckToken(role = {"SuperAdmin", "ShopAdmin"})
    @GetMapping("")
    public List<FlashSaleOrderDTO> findAll() {
        return flashSaleOrderService.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @JWTCheckToken(role = {"SuperAdmin", "ShopAdmin"})
    @PostMapping("")
    public String addLimitedProduct(@RequestBody @Validated FlashSaleOrderAddDTO flashSaleOrderAddDTO) {
        User user = userManageService.findUserById(flashSaleOrderAddDTO.getUserId());
        LimitedProduct limitedProduct = limitedProductManageService.findById(flashSaleOrderAddDTO.getLimitedProductId());
        Long orderId = SnowflakeIdWorker.generateId();
        FlashSaleOrder order = new FlashSaleOrder();
        order.setId(orderId)
                .setQuantity(flashSaleOrderAddDTO.getQuantity())
                .setPurchaseDateTime(flashSaleOrderAddDTO.getPurchaseDateTime())
                .setAmount(flashSaleOrderAddDTO.getAmount())
                .setOrderStatus(flashSaleOrderAddDTO.getOrderStatus())
                .setLimitedProduct(limitedProduct)
                .setUser(user);
        flashSaleOrderService.addFlashSaleOrder(order);
        return "Add limited product success!";
    }
}
