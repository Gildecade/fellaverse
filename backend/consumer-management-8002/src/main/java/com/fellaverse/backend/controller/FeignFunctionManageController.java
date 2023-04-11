package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.FunctionDTO;
import com.fellaverse.backend.service.FeignFunctionManageService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/management/function")
public class FeignFunctionManageController {
    @Autowired
    private FeignFunctionManageService feignFunctionManageService;

    @PostMapping("")
    public Boolean addFunction(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) FunctionDTO functionDTO){
        return feignFunctionManageService.addFunction(functionDTO);
    }

    @PutMapping("")
    public Boolean editFunction(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) FunctionDTO functionDTO){
        return feignFunctionManageService.editFunction(functionDTO);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteFunction(@PathVariable("id") Long id){
        return feignFunctionManageService.deleteFunction(id);
    }

    @GetMapping("")
    public Set<FunctionDTO> findAllFunction(){
        return feignFunctionManageService.findAllFunction();
    }

    @GetMapping("/{keyword}")
    public Set<FunctionDTO> findFunctionByKeyword(@PathVariable("keyword") String keyword){
        return feignFunctionManageService.findFunctionByKeyword(keyword);
    }
}
