package com.fellaverse.backend.controller;

import com.fellaverse.backend.bean.Exercise;
import com.fellaverse.backend.dto.ExerciseDTO;
import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.mapper.ExerciseMapper;
import com.fellaverse.backend.repository.ExerciseRepository;
import com.fellaverse.backend.service.AdminManageExerciseService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/management/exercise")
public class AdminManageExerciseController {
    @Autowired
    private AdminManageExerciseService adminManageExerciseService;
    @Autowired
    private ExerciseMapper exerciseMapper;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @JWTCheckToken(role = {"SuperAdmin", "WorkoutAdmin"})
    @PostMapping("")
    public Boolean addExercise(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) ExerciseDTO exerciseDTO){
        return adminManageExerciseService.addExercise(exerciseMapper.toEntity(exerciseDTO));
    }

    @JWTCheckToken(role = {"SuperAdmin", "WorkoutAdmin"})
    @PutMapping("")
    public Boolean editExercise(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) ExerciseDTO exerciseDTO){
        Exercise exercise = adminManageExerciseService.findExerciseByName(exerciseDTO.getExerciseName());
        return adminManageExerciseService.editExercise(exerciseMapper.partialUpdate(exerciseDTO, exercise));
    }

    @JWTCheckToken(role = {"SuperAdmin", "WorkoutAdmin"})
    @DeleteMapping("")
    public Boolean deleteExercise(@RequestBody ExerciseDTO exerciseDTO){
        Exercise exercise = adminManageExerciseService.findExerciseByName(exerciseDTO.getExerciseName());
        return adminManageExerciseService.deleteExercise(exercise);
    }

    @JWTCheckToken(role = {"SuperAdmin", "WorkoutAdmin"})
    @GetMapping("")
    public Set<ExerciseDTO> findAllExercise(){
        return adminManageExerciseService.findAllExercise().stream().map(exerciseMapper::toDto).collect(Collectors.toSet());
    }

    @JWTCheckToken(role = {"SuperAdmin", "WorkoutAdmin"})
    @GetMapping("/{keyword}")
    public Set<ExerciseDTO> findExercise(@PathVariable String keyword){
        return adminManageExerciseService.findExerciseByKeyword(keyword).stream().map(exerciseMapper::toDto).collect(Collectors.toSet());
    }


}
