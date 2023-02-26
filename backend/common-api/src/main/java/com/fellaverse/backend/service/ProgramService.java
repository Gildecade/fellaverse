package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Program;
import com.fellaverse.backend.bean.Schedule;
import com.fellaverse.backend.projection.ProgramInfo;

import java.util.List;
import java.util.Optional;

public interface ProgramService {

    List<ProgramInfo> findAllPrograms(Long user_id);

    Program addProgram(Program program);

    boolean deleteProgram(Long id);

    boolean updateProgram(Program program);

    Optional<Program> findProgramById(Long id);

}
