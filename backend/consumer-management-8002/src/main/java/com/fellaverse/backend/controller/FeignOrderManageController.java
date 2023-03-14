package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.OrderFindAllDTO;
import com.fellaverse.backend.dto.OrderRequestDTO;
import com.fellaverse.backend.service.FeignOrderManageService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/management/order")
public class FeignOrderManageController {

    @Autowired
    private FeignOrderManageService feignOrderManageService;

    @GetMapping("")
    public List<OrderFindAllDTO> findAllOrder(){
        return feignOrderManageService.findAllOrder();
    }

    @GetMapping("/{id}")
    public OrderFindAllDTO findOrderById(@PathVariable("id") Long id) {
        return feignOrderManageService.findOrderById(id);
    }

    @GetMapping("/product/{id}")
    public List<OrderFindAllDTO> findOrderByProductId(@PathVariable("id") Long productId) {
        return feignOrderManageService.findOrderByProductId(productId);
    }

    @GetMapping("/user/{id}")
    public List<OrderFindAllDTO> findOrderByUserId(@PathVariable("id") Long userId) {
        return feignOrderManageService.findOrderByUserId(userId);
    }

    @PostMapping("")
    public String addOrder(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) OrderRequestDTO orderRequestDTO) {
        return feignOrderManageService.addOrder(orderRequestDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable("id")Long id) {
        return feignOrderManageService.deleteOrder(id);
    }

    @PutMapping("")
    public String updateOrder(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) OrderRequestDTO orderRequestDTO) {
        return feignOrderManageService.updateOrder(orderRequestDTO);
    }
}
