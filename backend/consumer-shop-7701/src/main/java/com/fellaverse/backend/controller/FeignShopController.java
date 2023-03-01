package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.CourseBuyDTO;
import com.fellaverse.backend.service.FeignShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class FeignShopController {

    @Autowired
    private FeignShopService feignShopService;

    @PostMapping("/course")
    public String purchase(@RequestBody CourseBuyDTO courseBuyDTO){
        return feignShopService.purchase(courseBuyDTO);
    }

}
