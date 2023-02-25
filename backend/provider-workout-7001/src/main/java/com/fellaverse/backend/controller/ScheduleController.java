package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.RecordDTO;
import com.fellaverse.backend.dto.ScheduleDTO;
import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.mapper.ProgramMapper;
import com.fellaverse.backend.mapper.ScheduleMapper;
import com.fellaverse.backend.service.ProgramService;
import com.fellaverse.backend.service.ScheduleService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/schedule")  // any requests under token will be proceeded
public class ScheduleController {

    private final ScheduleService scheduleService;

    private final ScheduleMapper scheduleMapper;

    public ScheduleController(ScheduleService scheduleService, ScheduleMapper scheduleMapper) {
        this.scheduleService = scheduleService;
        this.scheduleMapper = scheduleMapper;
    }

    @JWTCheckToken(function = "add schedule")
    @PostMapping("")
    public String addSchedule(@RequestBody @Validated(value = ValidGroup.Crud.Create.class)ScheduleDTO scheduleDTO) {
        scheduleService.setSchedule(scheduleMapper.toEntity(scheduleDTO));
        return "Add schedule succeeded!";
    }


    @JWTCheckToken(function = "update schedule")
    @PutMapping("")
    public String updateSchedule(@RequestBody @Validated(value = ValidGroup.Crud.Create.class)ScheduleDTO scheduleDTO) {
        scheduleService.updateSchedule(scheduleMapper.toEntity(scheduleDTO));
        return "Add schedule succeeded!";
    }

    @JWTCheckToken(function = "delete schedule")
    @DeleteMapping("/{id}")
    public String deleteSchedule(@PathVariable("id") Long id) {
        scheduleService.deleteSchedule(id);
        return "Delete schedule succeeded!";
    }

    @JWTCheckToken(function = "select schedule")
    @GetMapping("/{userId}")
    public List<ScheduleDTO> findRecordByUserId(@PathVariable("userId") Long userId) {
        return scheduleService.findAllSchedule(userId).stream().map(scheduleMapper::toDto).collect(Collectors.toList());
    }

}