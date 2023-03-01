package com.fellaverse.backend.controller;

import com.fellaverse.backend.bean.Program;
import com.fellaverse.backend.dto.ProgramDTO;
import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.mapper.ProgramMapper;
import com.fellaverse.backend.projection.ProgramInfo;
import com.fellaverse.backend.service.ProgramService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> addProgram(@RequestBody @Validated(value = ValidGroup.Crud.Create.class)ProgramDTO programDTO) {
        programService.addProgram(programMapper.toEntity(programDTO));
        return new ResponseEntity<>("Add program succeeded!", HttpStatus.CREATED);
    }


    @JWTCheckToken(function = "update program")
    @PutMapping("")
    public String updateProgram(@RequestBody @Validated(value = ValidGroup.Crud.Update.class)ProgramDTO programDTO) {
        programService.updateProgram(programMapper.toEntity(programDTO));
        return "Update program succeeded!";
    }

    @JWTCheckToken(function = "delete program")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProgram(@PathVariable("id") Long id) {
        programService.deleteProgram(id);
        return new ResponseEntity<>("Delete program succeeded!", HttpStatus.NO_CONTENT);
    }
    @JWTCheckToken(function = "select program")
    @DeleteMapping("/{userId}")
    public List<ProgramInfo> findAllProgram(@PathVariable("userId") Long user_id) {
        return programService.findAllPrograms(user_id);
    }

}
