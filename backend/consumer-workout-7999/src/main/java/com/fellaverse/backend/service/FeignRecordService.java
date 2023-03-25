package com.fellaverse.backend.service;

import com.fellaverse.backend.config.ConsumerErrorDecoder;
import com.fellaverse.backend.config.FeignBasicAuthRequestInterceptor;
import com.fellaverse.backend.dto.ExerciseDTO;
import com.fellaverse.backend.dto.RecordAddDTO;
import com.fellaverse.backend.dto.RecordDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Component
@FeignClient(value = "provider-gateway", contextId = "record", path = "/api/record",
        configuration = {FeignBasicAuthRequestInterceptor.class, ConsumerErrorDecoder.class})
public interface FeignRecordService {


    @GetMapping("/exercise")
    List<ExerciseDTO> findAllExercise();

    @PostMapping("")
    String addRecord(@RequestBody RecordAddDTO recordAddDTO);

    @DeleteMapping("/{id}/{userId}")
    String deleteRecord(@PathVariable("id") Long id, @PathVariable("userId")Long userId);

    @GetMapping("/{userId}")
    List<RecordDTO> findRecordByUserId(@PathVariable("userId") Long userId);
}
