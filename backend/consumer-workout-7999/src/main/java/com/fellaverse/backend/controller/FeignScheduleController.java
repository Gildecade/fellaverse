package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.ScheduleDTO;
import com.fellaverse.backend.projection.ScheduleInfo;
import com.fellaverse.backend.service.FeignScheduleService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class FeignScheduleController {
    @Autowired
    private FeignScheduleService feignScheduleService;

    @GetMapping("/{userId}")
    public List<ScheduleInfo> findScheduleByUserId(@PathVariable("userId") Long userId){
        return feignScheduleService.findScheduleByUserId(userId);
    }

    @PostMapping("")
    public String addSchedule(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) ScheduleDTO scheduleDTO){
        ResponseEntity<String> result = feignScheduleService.addSchedule(scheduleDTO);
        Assert.isTrue(result.getStatusCode() == HttpStatus.CREATED, "Add schedule failed!");
        return result.getBody();
    }

    @PutMapping("")
    public String updateSchedule(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) ScheduleDTO scheduleDTO){
        String result = feignScheduleService.updateSchedule(scheduleDTO);
        Assert.isTrue(result.equals("Update Schedule succeeded!"), "Update schedule failed!");
        return result;
    }

    @DeleteMapping("/{id}")
    public String deleteSchedule(@PathVariable("id") Long id){
        ResponseEntity<String> result = feignScheduleService.deleteSchedule(id);
        Assert.isTrue(result.getStatusCode() == HttpStatus.NO_CONTENT, "Delete schedule failed!");
        return result.getBody();
    }




}
