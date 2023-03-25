package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.ExerciseDTO;
import com.fellaverse.backend.dto.RecordAddDTO;
import com.fellaverse.backend.dto.RecordDTO;
import com.fellaverse.backend.service.FeignRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.fellaverse.backend.validator.ValidGroup;

import java.util.List;

@RestController
@RequestMapping("/record")
public class FeignRecordController {
    @Autowired
    private FeignRecordService feignRecordService;


    @GetMapping("/exercise")
    public List<ExerciseDTO> findAllExercise(){
        return feignRecordService.findAllExercise();
    }

    @PostMapping("")
    public String addRecord(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) RecordAddDTO recordAddDTO){
        String result = feignRecordService.addRecord(recordAddDTO);
        Assert.isTrue("Add record success!".equals(result), "Add record failed!");
        return result;
    }

    @DeleteMapping("/{id}/{userId}")
    public String deleteRecord(@PathVariable("id") Long id, @PathVariable("userId")Long userId){
        String result = feignRecordService.deleteRecord(id, userId);
        Assert.isTrue("Delete record success!".equals(result), "Delete record failed!");
        return result;
    }

    @GetMapping("/{userId}")
    public List<RecordDTO> findRecordByUserId(@PathVariable("userId") Long userId){
        return feignRecordService.findRecordByUserId(userId);
    }


}
