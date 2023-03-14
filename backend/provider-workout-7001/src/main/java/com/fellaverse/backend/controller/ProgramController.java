package com.fellaverse.backend.controller;

import com.fellaverse.backend.bean.Exercise;
import com.fellaverse.backend.bean.Program;
import com.fellaverse.backend.dto.ExerciseDTO;
import com.fellaverse.backend.dto.ProgramDTO;
import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.mapper.ExerciseMapper;
import com.fellaverse.backend.mapper.ProgramMapper;
import com.fellaverse.backend.projection.ProgramInfo;
import com.fellaverse.backend.repository.ExerciseRepository;
import com.fellaverse.backend.repository.OrderRepository;
import com.fellaverse.backend.repository.ProgramRepository;
import com.fellaverse.backend.service.ProgramService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/program")  // any requests under token will be proceeded
public class ProgramController {

    private final ProgramService programService;

    private final ExerciseRepository exerciseRepository;
    private final ProgramMapper programMapper;
    private final ProgramRepository programRepository;

    private final ExerciseMapper exerciseMapper;
    private final OrderRepository orderRepository;

    public ProgramController(ProgramService programService, ExerciseRepository exerciseRepository, ProgramMapper programMapper,
                             ProgramRepository programRepository, ExerciseMapper exerciseMapper,
                             OrderRepository orderRepository) {
        this.programService = programService;
        this.exerciseRepository = exerciseRepository;
        this.programMapper = programMapper;
        this.programRepository = programRepository;
        this.exerciseMapper = exerciseMapper;
        this.orderRepository = orderRepository;
    }

    @JWTCheckToken(function = "add program")
    @PostMapping("")
    @Transactional
    public ResponseEntity<String> addProgram(@RequestBody @Validated(value = ValidGroup.Crud.Create.class)ProgramDTO programDTO) {
        Program program = programMapper.toEntity(programDTO);
        program.getExercises().clear();
        for (ExerciseDTO exercise : programDTO.getExercises()) {
            Exercise temp = exerciseRepository.findById(exercise.getId()).orElse(null);
            program.getExercises().add(temp);
        }
        //exerciseRepository.findById(programDTO.getExercises())
        programService.addProgram(program);
        return new ResponseEntity<>("Add program succeeded!", HttpStatus.CREATED);
    }


    @JWTCheckToken(function = "update program")
    @PutMapping("")
    public String updateProgram(@RequestBody @Validated(value = ValidGroup.Crud.Update.class)ProgramDTO programDTO) {
        Program original = programRepository.findById((programDTO.getId())).orElse(null);
        programService.updateProgram(programMapper.partialUpdate(programDTO, original));

        List<Long> existingExercises = exerciseRepository.findByPrograms_Id(programDTO.getId()).stream().map(Exercise::getId).toList();
        List<Long> exerciseIds = programDTO.getExercises().stream().map(ExerciseDTO::getId).toList();

        for (Long exerciseId : exerciseIds) {
            if (!existingExercises.contains(exerciseId)) {
                System.out.println(exerciseId);
                System.out.println(programDTO.getId());
                programRepository.insertToProgramExercise(exerciseId, programDTO.getId());
            }
        }

        for (Long existingExercise : existingExercises) {
            if (!exerciseIds.contains(existingExercise)) {
                programRepository.deleteFromProgramExercise(existingExercise, programDTO.getId());
            }
        }

        return "Update program succeeded!";
    }

    @JWTCheckToken(function = "delete program")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProgram(@PathVariable("id") Long id) {
        programRepository.deleteFromProgramExerciseByProgramId(id);
        programService.deleteProgram(id);
        return new ResponseEntity<>("Delete program succeeded!", HttpStatus.OK);
    }
    @JWTCheckToken(function = "select program")
    @GetMapping("/{scheduleId}")
    public List<ProgramInfo> findAllProgram(@PathVariable("scheduleId") Long scheduleId) {
        return programService.findAllPrograms(scheduleId);
    }

}
