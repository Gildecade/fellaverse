package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.CheckInDTO;
import com.fellaverse.backend.service.FeignCheckInService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/checkin")
public class FeignCheckInController {
    @Autowired
    private FeignCheckInService feignCheckInService;

    @PostMapping("")
    Boolean addCheckIn(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) CheckInDTO checkInDTO){
        return feignCheckInService.addCheckIn(checkInDTO);
    }

    @PutMapping("")
    Boolean editCheckIn(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) CheckInDTO checkInDTO){
        return feignCheckInService.editCheckIn(checkInDTO);
    }

    @DeleteMapping("")
    Boolean deleteCheckIn(@RequestBody @Validated(value = ValidGroup.Crud.Delete.class) CheckInDTO checkInDTO){
        return feignCheckInService.deleteCheckIn(checkInDTO);
    }

    @GetMapping("{id}")
    Set<CheckInDTO> findAllCheckIn(@PathVariable("id") Long id){
        return feignCheckInService.findAllCheckIn(id);
    }
}
