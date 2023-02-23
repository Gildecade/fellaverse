package com.fellaverse.backend.controller;

import com.fellaverse.backend.bean.Program;
import com.fellaverse.backend.dto.ProgramDTO;
import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.mapper.ProgramMapper;
import com.fellaverse.backend.service.ProgramService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/program")  // any requests under token will be proceeded
public class ProgramController {

    private final ProgramService programService;

    private final ProgramMapper programMapper;

    public ProgramController(ProgramService programService, ProgramMapper programMapper) {
        this.programService = programService;
        this.programMapper = programMapper;
    }

    @JWTCheckToken(function = "add program")
    @PostMapping("")
    public String addProgram(@RequestBody @Validated(value = ValidGroup.Crud.Create.class)ProgramDTO programDTO) {
        programService.addProgram(programMapper.toEntity(programDTO));
        return "Add program succeeded!";
    }


    @JWTCheckToken(function = "update program")
    @PutMapping("")
    public String updateProgram(@RequestBody @Validated(value = ValidGroup.Crud.Create.class)ProgramDTO programDTO) {
        programService.updateProgram(programMapper.toEntity(programDTO));
        return "Add program succeeded!";
    }

    @JWTCheckToken(function = "delete program")
    @DeleteMapping("/{id}")
    public String deleteProgram(@PathVariable("id") Long id) {
        programService.deleteProgram(id);
        return "Delete program succeeded!";
    }
    @JWTCheckToken(function = "delete program")
    @DeleteMapping("/{id}")
    public List<Program> findAllProgram(@PathVariable("id") Long user_id) {
        return programService.findAllPrograms(user_id);
    }

}
