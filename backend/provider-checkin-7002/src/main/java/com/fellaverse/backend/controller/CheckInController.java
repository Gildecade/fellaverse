package com.fellaverse.backend.controller;

import com.fellaverse.backend.bean.CheckIn;
import com.fellaverse.backend.dto.CheckInDTO;
import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.mapper.CheckInMapper;
import com.fellaverse.backend.service.CheckInService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/checkin")
public class CheckInController {
    @Autowired
    private CheckInService checkInService;
    @Autowired
    private CheckInMapper checkInMapper;
    @JWTCheckToken(function = {"update checkIn", "add checkIn", "delete checkIn"})
    @PostMapping("")
    public Boolean addCheckIn(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) CheckInDTO checkInDTO){
        return checkInService.addCheckIn(checkInMapper.toEntity(checkInDTO));
    }

    @JWTCheckToken(function = {"update checkIn", "add checkIn", "delete checkIn"})
    @PutMapping("")
    public Boolean editCheckIn(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) CheckInDTO checkInDTO){
         CheckIn checkIn = checkInService.findById(checkInDTO.getId(), checkInDTO.getUser().getId());
         if (checkIn == null)
             return false;
         else{
             return checkInService.editCheckIn(checkIn);
         }
    }

    @JWTCheckToken(function = {"update checkIn", "add checkIn", "delete checkIn"})
    @DeleteMapping("")
    public Boolean deleteCheckIn(@RequestBody @Validated(value = ValidGroup.Crud.Delete.class) CheckInDTO checkInDTO){
        return checkInService.removeCheckIn(checkInDTO.getUser().getId(), checkInDTO.getId());
    }

    @JWTCheckToken(function = {"update checkIn", "add checkIn", "delete checkIn"})
    @GetMapping("")
    public Set<CheckInDTO> findAllCheckIn(){
        return checkInService.findAllCheckIn().stream().map(checkInMapper::toDto).collect(Collectors.toSet());
    }
}
