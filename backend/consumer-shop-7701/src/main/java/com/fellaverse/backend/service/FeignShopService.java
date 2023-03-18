package com.fellaverse.backend.service;
import com.fellaverse.backend.config.ConsumerErrorDecoder;
import com.fellaverse.backend.config.FeignBasicAuthRequestInterceptor;
import com.fellaverse.backend.dto.CourseBuyDTO;
import com.fellaverse.backend.dto.CourseFindAllDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
@FeignClient(value = "provider-gateway", contextId = "shop", path = "/api/shop",
        configuration = {FeignBasicAuthRequestInterceptor.class, ConsumerErrorDecoder.class})
public interface FeignShopService {

    @PostMapping("/course")
    public String purchase(@RequestBody CourseBuyDTO courseBuyDTO);

    @GetMapping("")
    public List<CourseFindAllDTO> findAll();

}
