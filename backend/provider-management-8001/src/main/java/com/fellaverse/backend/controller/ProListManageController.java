package com.fellaverse.backend.controller;

import com.fellaverse.backend.bean.ProList;
import com.fellaverse.backend.dto.ProListDTO;
import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.mapper.ProListMapper;
import com.fellaverse.backend.service.ProListManageService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/management/prolist")
public class ProListManageController {
    @Autowired
    private ProListManageService proListManageService;
    @Autowired
    private ProListMapper proListMapper;
    @JWTCheckToken(role = {"SuperAdmin", "WorkoutAdmin"})
    @PostMapping("")
    public Boolean addProList(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) ProListDTO proListDTO){
        return proListManageService.addEditProList(proListMapper.toEntity(proListDTO));
    }
    @JWTCheckToken(role = {"SuperAdmin", "WorkoutAdmin"})
    @PutMapping("")
    public Boolean editProList(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) ProListDTO proListDTO){
        return proListManageService.addEditProList(proListMapper.toEntity(proListDTO));
    }

    @JWTCheckToken(role = {"SuperAdmin", "WorkoutAdmin"})
    @DeleteMapping("/{id}")
    public Boolean deleteProList(@PathVariable Long id){
        return proListManageService.deleteProList(id);
    }

    @GetMapping("")
    public Set<ProListDTO> findAllProList(){
        return proListManageService.findAllProList().stream().map(proListMapper::toDto).collect(Collectors.toSet());
    }

}
