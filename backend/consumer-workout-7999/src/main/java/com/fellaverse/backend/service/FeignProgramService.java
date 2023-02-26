package com.fellaverse.backend.service;

import com.fellaverse.backend.config.ConsumerErrorDecoder;
import com.fellaverse.backend.config.FeignBasicAuthRequestInterceptor;
import com.fellaverse.backend.dto.ProgramDTO;
import com.fellaverse.backend.projection.ProgramInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(value = "provider-gateway", contextId = "program", path = "/api/program",
        configuration = {FeignBasicAuthRequestInterceptor.class, ConsumerErrorDecoder.class})
public interface FeignProgramService {

    @PostMapping("")
    public ResponseEntity<String> addProgram(@RequestBody ProgramDTO programDTO);

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProgram(@PathVariable("id") Long id);

    @PutMapping("")
    public String updateProgram(@RequestBody ProgramDTO programDTO);


    @GetMapping("/{userId}")
    public List<ProgramInfo> findProgramByUserId(@PathVariable("userId") Long userId);
}
