package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Exercise;

import java.util.Set;

public interface AdminManageExerciseService {
    /**
     * return true for successfully adding a new exercise
     */
    public Boolean addExercise(Exercise exercise);

    /**
     * return true for successfully updating a new exercise
     */
    public Boolean editExercise(Exercise exercise);

    /**
     * return true for successfully deleting a new exercise
     */
    public Boolean deleteExercise(Exercise exercise);

    /**
     * return a set of all exercises
     */
    public Set<Exercise> findAllExercise();

    /**
     * return a set of exercise contains the keyword
     */
    public Set<Exercise> findExerciseByKeyword(String keyword);

}
