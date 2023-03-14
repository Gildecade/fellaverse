package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.Exercise;
import com.fellaverse.backend.bean.Program;
import com.fellaverse.backend.projection.ProgramInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    List<ProgramInfo> findByScheduleId(Long scheduleId);

    @Modifying
    @Transactional
    @Query(value = "insert into program_exercise (exercise_id, program_id) values (?1, ?2)", nativeQuery = true)
    void insertToProgramExercise(Long exerciseId, Long programId);

    @Modifying
    @Transactional
    @Query(value = "delete from program_exercise where exercise_id = ?1 and program_id = ?2", nativeQuery = true)
    void deleteFromProgramExercise(Long exerciseId, Long programId);

    @Modifying
    @Transactional
    @Query(value = "delete from program_exercise where program_id = ?1", nativeQuery = true)
    void deleteFromProgramExerciseByProgramId(Long programId);

}