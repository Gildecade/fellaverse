package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.FlashSaleOrderDTO;
import com.fellaverse.backend.dto.UserProfileDTO;
import com.fellaverse.backend.service.FeignUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/{id}")
public class FeignUserCenterController {
    @Autowired
    private FeignUserService feignUserService;

    @GetMapping("/")
    public UserProfileDTO findAllInfo(@PathVariable("id") Long id) {
        return feignUserService.findAllInfo(id);
    }

    @GetMapping("/flashSaleOrder")
    public List<FlashSaleOrderDTO> findAllFlashSaleOrder(@PathVariable("id") Long id) {
        return feignUserService.findAllFlashSaleOrder(id);
    }
}
