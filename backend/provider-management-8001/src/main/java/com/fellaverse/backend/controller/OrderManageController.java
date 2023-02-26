package com.fellaverse.backend.controller;

import com.fellaverse.backend.service.OrderManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/management/order")
public class OrderManageController {
    @Autowired
    private OrderManageService orderManageService;

//    @GetMapping
//    public List<>



}
