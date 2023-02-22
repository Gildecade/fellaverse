package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Program;

import java.util.List;
import java.util.Optional;

public interface ProgramService {
    Program addProgram(Program program);

    boolean deleteProgram(Long id);

    boolean updateProgram(Program program);

    Optional<Program> findProgramById(Long id);

}
