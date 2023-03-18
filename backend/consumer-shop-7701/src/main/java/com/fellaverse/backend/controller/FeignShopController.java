package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.CourseBuyDTO;
import com.fellaverse.backend.dto.CourseFindAllDTO;
import com.fellaverse.backend.service.FeignShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class FeignShopController {

    @Autowired
    private FeignShopService feignShopService;

    @PostMapping("/course")
    public String purchase(@RequestBody CourseBuyDTO courseBuyDTO){
        return feignShopService.purchase(courseBuyDTO);
    }

    @GetMapping("")
    public List<CourseFindAllDTO> findAll(){
        return feignShopService.findAll();
    };
}
