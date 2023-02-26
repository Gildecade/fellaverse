package com.fellaverse.backend.service;

import com.fellaverse.backend.config.ConsumerErrorDecoder;
import com.fellaverse.backend.config.FeignBasicAuthRequestInterceptor;
import com.fellaverse.backend.dto.CheckInDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Component
@FeignClient(value = "provider-gateway", contextId = "checkIn", path = "/api/checkin",
        configuration = {FeignBasicAuthRequestInterceptor.class, ConsumerErrorDecoder.class})
public interface FeignCheckInService {
    @PostMapping("")
    Boolean addCheckIn(CheckInDTO checkInDTO);

    @PutMapping("")
    Boolean editCheckIn(CheckInDTO checkInDTO);

    @DeleteMapping("")
    Boolean deleteCheckIn(CheckInDTO checkInDTO);

    @GetMapping("{id}")
    Set<CheckInDTO> findAllCheckIn(@PathVariable("id") Long id);
}
