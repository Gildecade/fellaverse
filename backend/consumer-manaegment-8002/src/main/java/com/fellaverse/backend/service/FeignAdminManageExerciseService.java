package com.fellaverse.backend.service;

import com.fellaverse.backend.config.ConsumerErrorDecoder;
import com.fellaverse.backend.config.FeignBasicAuthRequestInterceptor;
import com.fellaverse.backend.dto.ExerciseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Component
@FeignClient(value = "provider-gateway", contextId = "adminManageExercise", path = "/api/management/exercise",
        configuration = {FeignBasicAuthRequestInterceptor.class, ConsumerErrorDecoder.class})
public interface FeignAdminManageExerciseService {
    @PostMapping("")
    Boolean addExercise(ExerciseDTO exerciseDTO);

    @PutMapping("")
    Boolean editExercise(ExerciseDTO exerciseDTO);

    @DeleteMapping("/{id}")
    Boolean deleteExercise(@PathVariable("id") Long id);

    @GetMapping("")
    Set<ExerciseDTO> findAllExercise();
    @GetMapping("/{keyword}")
    Set<ExerciseDTO> findExercise(@PathVariable("keyword") String keyword);
}
