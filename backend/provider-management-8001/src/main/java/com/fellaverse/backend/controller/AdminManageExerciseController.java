package com.fellaverse.backend.controller;

import com.fellaverse.backend.bean.Exercise;
import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.service.AdminManageExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/management/exercise")
public class AdminManageExerciseController {
    @Autowired
    private AdminManageExerciseService adminManageExerciseService;
    @JWTCheckToken(role = "SuperAdmin")
    @PutMapping("/addExercise")
    public Boolean addExercise(@RequestBody Exercise exercise){
        return adminManageExerciseService.addExercise(exercise);
    }

    @JWTCheckToken(role = "SuperAdmin")
    @PutMapping("/editExercise")
    public Boolean editExercise(@RequestBody Exercise exercise){
        return adminManageExerciseService.editExercise(exercise);
    }

    @JWTCheckToken(role = "SuperAdmin")
    @PutMapping("/deleteExercise")
    public Boolean deleteExercise(@RequestBody Exercise exercise){
        return adminManageExerciseService.deleteExercise(exercise);
    }

    @JWTCheckToken(role = "SuperAdmin")
    @PutMapping("/findAllExercise")
    public Set<Exercise> findAllExercise(){
        return adminManageExerciseService.findAllExercise();
    }

    @JWTCheckToken(role = "SuperAdmin")
    @PutMapping("/findExercise")
    public Set<Exercise> findExercise(String keyword){
        return adminManageExerciseService.findExerciseByKeyword(keyword);
    }


}
