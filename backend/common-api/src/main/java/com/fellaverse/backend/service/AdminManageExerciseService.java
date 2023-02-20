package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Exercise;

import java.util.Set;

public interface AdminManageExerciseService {
    /**
     * return true for successfully adding a new exercise
     */
    Boolean addExercise(Exercise exercise);

    /**
     * return true for successfully updating a new exercise
     */
    Boolean editExercise(Exercise exercise);

    /**
     * return true for successfully deleting a new exercise
     */
    Boolean deleteExercise(Long id);

    /**
     * return a set of all exercises
     */
    Set<Exercise> findAllExercise();

    /**
     * return a set of exercise contains the keyword
     */
    Set<Exercise> findExerciseByKeyword(String keyword);

    Exercise findExerciseByName(String name);

}
