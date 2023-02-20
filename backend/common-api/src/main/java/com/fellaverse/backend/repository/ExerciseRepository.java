package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ExerciseRepository  extends JpaRepository<Exercise, Long> {
    Exercise findByExerciseName(String exerciseName);
    Set<Exercise> findByExerciseNameContains(@NonNull String exerciseName);
    long deleteByExerciseName(@NonNull String exerciseName);
}