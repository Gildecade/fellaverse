package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.ProgramDTO;
import com.fellaverse.backend.projection.ProgramInfo;
import com.fellaverse.backend.service.FeignProgramService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/program")
public class FeignProgramController {
    @Autowired
    private FeignProgramService feignProgramService;

    @GetMapping("/{userId}")
    public List<ProgramInfo> findProgramByUserId(@PathVariable("userId") Long userId){
        return feignProgramService.findProgramByUserId(userId);
    }

    @PostMapping("")
    public String addProgram(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) ProgramDTO programDTO){
        ResponseEntity<String> result = feignProgramService.addProgram(programDTO);
        Assert.isTrue(result.getStatusCode() == HttpStatus.CREATED, "Add program failed!");
        return result.getBody();
    }

    @PutMapping("")
    public String updateProgram(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) ProgramDTO programDTO){
        String result = feignProgramService.updateProgram(programDTO);
        Assert.isTrue(result.equals("Update Program succeeded!"), "Update program failed!");
        return result;
    }

    @DeleteMapping("/{id}")
    public String deleteProgram(@PathVariable("id") Long id){
        ResponseEntity<String> result = feignProgramService.deleteProgram(id);
        Assert.isTrue(result.getStatusCode() == HttpStatus.NO_CONTENT, "Delete program failed!");
        return result.getBody();
    }




}
