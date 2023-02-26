package com.fellaverse.backend.service;

import com.fellaverse.backend.config.ConsumerErrorDecoder;
import com.fellaverse.backend.config.FeignBasicAuthRequestInterceptor;
import com.fellaverse.backend.dto.RecordDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Component
@FeignClient(value = "provider-gateway", contextId = "record", path = "/api/record",
        configuration = {FeignBasicAuthRequestInterceptor.class, ConsumerErrorDecoder.class})
public interface FeignRecordService {

    @PostMapping("")
    public String addRecord(@RequestBody RecordDTO recordDTO);

    @DeleteMapping("/{id}/{userId}")
    public String deleteRecord(@PathVariable("id") Long id, @PathVariable("userId")Long userId);

    @GetMapping("/{userId}")
    public List<RecordDTO> findRecordByUserId(@PathVariable("userId") Long userId);
}
