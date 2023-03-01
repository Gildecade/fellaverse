package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.Program;
import com.fellaverse.backend.projection.ProgramInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    List<ProgramInfo> findByScheduleId(Long scheduleId);
}