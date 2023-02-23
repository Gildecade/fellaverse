package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Program;
import com.fellaverse.backend.repository.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;

    public ProgramServiceImpl(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public List<Program> findAllPrograms(Long user_id) {
        return programRepository.findByUser_Id(user_id);
    }

    @Override
    public Program addProgram (Program program) {
        return programRepository.save(program);
    }

    @Override
    public boolean deleteProgram(Long id) {
        try {
            programRepository.deleteById(id);
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateProgram(Program program) {

        try {
            programRepository.save(program);
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    public Optional<Program> findProgramById(Long id) {

        return programRepository.findById(id);
    }
}
