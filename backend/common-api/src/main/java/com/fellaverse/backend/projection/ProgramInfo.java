package com.fellaverse.backend.projection;

import java.util.Set;

/**
 * A Projection for the {@link com.fellaverse.backend.bean.Program} entity
 */
public interface ProgramInfo {
    String getProgramName();

    Set<ExerciseInfo> getExercises();

    /**
     * A Projection for the {@link com.fellaverse.backend.bean.Exercise} entity
     */
    interface ExerciseInfo {
        Long getId();

        String getExerciseName();
    }
}