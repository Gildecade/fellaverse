package com.fellaverse.backend.service;

import com.fellaverse.backend.config.ConsumerErrorDecoder;
import com.fellaverse.backend.config.FeignBasicAuthRequestInterceptor;
import com.fellaverse.backend.dto.ScheduleDTO;
import com.fellaverse.backend.projection.ScheduleInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(value = "provider-gateway", contextId = "schedule", path = "/api/schedule",
        configuration = {FeignBasicAuthRequestInterceptor.class, ConsumerErrorDecoder.class})
public interface FeignScheduleService {

    @PostMapping("")
    public ResponseEntity<String> addSchedule(@RequestBody ScheduleDTO scheduleDTO);

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable("id") Long id);

    @PutMapping("")
    public String updateSchedule(@RequestBody ScheduleDTO scheduleDTO);


    @GetMapping("/{userId}")
    public List<ScheduleInfo> findScheduleByUserId(@PathVariable("userId") Long userId);
}
