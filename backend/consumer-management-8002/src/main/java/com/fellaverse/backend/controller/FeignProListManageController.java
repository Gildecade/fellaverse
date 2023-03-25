package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.ProListDTO;
import com.fellaverse.backend.service.FeignProListManageService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/management/prolist")
public class FeignProListManageController {
    @Autowired
    private FeignProListManageService feignProListManageService;

    @PostMapping("")
    public Boolean addProList(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) ProListDTO proListDTO){
        return feignProListManageService.addProList(proListDTO);
    }

    @PutMapping("")
    public Boolean editProList(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) ProListDTO proListDTO){
        return feignProListManageService.editProList(proListDTO);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteProList(@PathVariable("id") Long id){
        return feignProListManageService.deleteProList(id);
    }

    @GetMapping("")
    public Set<ProListDTO> findAllProList(){
        return feignProListManageService.findAllProList();
    }
}
