package com.fellaverse.backend.controller;

import com.fellaverse.backend.bean.Function;
import com.fellaverse.backend.dto.FunctionDTO;
import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.mapper.FunctionDTOMapper;
import com.fellaverse.backend.service.FunctionManageService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/management/function")
public class FunctionManageController {
    @Autowired
    private FunctionManageService functionManageService;
    @Autowired
    private FunctionDTOMapper functionDTOMapper;
    @JWTCheckToken(role = {"SuperAdmin", "WorkoutAdmin"})
    @PostMapping("")
    public Boolean addFunction(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) FunctionDTO functionDTO){
        return functionManageService.addFunction(functionDTOMapper.toEntity(functionDTO));
    }

    @JWTCheckToken(role = {"SuperAdmin", "WorkoutAdmin"})
    @PutMapping("")
    public Boolean editFunction(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) FunctionDTO functionDTO){
        Function function = functionManageService.findFunctionById(functionDTO.getId());
        return functionManageService.updateFunction(functionDTOMapper.partialUpdate(functionDTO, function));
    }

    @JWTCheckToken(role = {"SuperAdmin", "WorkoutAdmin"})
    @DeleteMapping("/{id}")
    public Boolean deleteFunction(@PathVariable Long id){
        return functionManageService.deleteFunction(id);
    }

    @JWTCheckToken(role = {"SuperAdmin", "WorkoutAdmin"})
    @GetMapping("")
    public Set<FunctionDTO> findAllExercise(){
        return functionManageService.findAllFunctions().stream().map(functionDTOMapper::toDto).collect(Collectors.toSet());
    }

    @JWTCheckToken(role = {"SuperAdmin", "WorkoutAdmin"})
    @GetMapping("/{keyword}")
    public Set<FunctionDTO> findFunctionByKeyword(@PathVariable String keyword){
        return functionManageService.findFunctionByKeyword(keyword).stream().map(functionDTOMapper::toDto).collect(Collectors.toSet());
    }
}
