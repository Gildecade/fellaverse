package com.fellaverse.backend.service;

import com.fellaverse.backend.config.ConsumerErrorDecoder;
import com.fellaverse.backend.config.FeignBasicAuthRequestInterceptor;
import com.fellaverse.backend.dto.OrderFindAllDTO;
import com.fellaverse.backend.dto.OrderRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(value = "provider-gateway", contextId = "orderManagement", path = "/api/management/order",
        configuration = {FeignBasicAuthRequestInterceptor.class, ConsumerErrorDecoder.class})
public interface FeignOrderManageService {

    @GetMapping("")
    List<OrderFindAllDTO> findAllOrder();

    @GetMapping("/{id}")
    OrderFindAllDTO findOrderById(@PathVariable("id") Long id);

    @GetMapping("/product/{id}")
    List<OrderFindAllDTO> findOrderByProductId(@PathVariable("id") Long productId);

    @GetMapping("/user/{id}")
    List<OrderFindAllDTO> findOrderByUserId(@PathVariable("id") Long userId);

    @PostMapping("")
    String addOrder(@RequestBody OrderRequestDTO orderRequestDTO);

    @DeleteMapping("/{id}")
    String deleteOrder(@PathVariable("id")Long id);

    @PutMapping("")
    String updateOrder(@RequestBody OrderRequestDTO orderRequestDTO);

}
