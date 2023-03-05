package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ExerciseRepository  extends JpaRepository<Exercise, Long> {
    Exercise findByExerciseName(String exerciseName);
    Set<Exercise> findByExerciseNameContains(String exerciseName);

    List<Exercise> findByPrograms_Id(Long id);
}