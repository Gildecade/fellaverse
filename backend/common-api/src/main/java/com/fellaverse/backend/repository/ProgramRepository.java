package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {
}