package com.fellaverse.backend.service;

import com.fellaverse.backend.config.ConsumerErrorDecoder;
import com.fellaverse.backend.config.FeignBasicAuthRequestInterceptor;
import com.fellaverse.backend.dto.CourseBuyDTO;
import com.fellaverse.backend.dto.CourseFindAllDTO;
import com.fellaverse.backend.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
@FeignClient(value = "provider-gateway", contextId = "shop", path = "/api/shop",
        configuration = {FeignBasicAuthRequestInterceptor.class, ConsumerErrorDecoder.class})
public interface FeignShopService {

    @PostMapping("/course")
    String purchase(@RequestBody CourseBuyDTO courseBuyDTO);

    @GetMapping("")
    List<CourseFindAllDTO> findAll();

    @GetMapping("/{userId}/order")
    List<OrderDTO> findOrderByUserId(@PathVariable("userId") Long userId);

}
