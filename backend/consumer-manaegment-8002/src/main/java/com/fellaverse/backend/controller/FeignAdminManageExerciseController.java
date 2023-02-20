package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.ExerciseDTO;
import com.fellaverse.backend.service.FeignAdminManageExerciseService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/management/exercise")
public class FeignAdminManageExerciseController {
    @Autowired
    private FeignAdminManageExerciseService feignAdminManageExerciseService;

    @PostMapping("")
    public Boolean addExercise(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) ExerciseDTO exerciseDTO){
        return feignAdminManageExerciseService.addExercise(exerciseDTO);
    }

    @PutMapping("")
    public Boolean editExercise(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) ExerciseDTO exerciseDTO){
        return feignAdminManageExerciseService.editExercise(exerciseDTO);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteExercise(@PathVariable("id") Long id){
        return feignAdminManageExerciseService.deleteExercise(id);
    }

    @GetMapping("")
    public Set<ExerciseDTO> findAllExercise(){
        return feignAdminManageExerciseService.findAllExercise();
    }

    @GetMapping("/{keyword}")
    public Set<ExerciseDTO> findExercise(@PathVariable("keyword") String keyword){
        return feignAdminManageExerciseService.findExercise(keyword);
    }
}
