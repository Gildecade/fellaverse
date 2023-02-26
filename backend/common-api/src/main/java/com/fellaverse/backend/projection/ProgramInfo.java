package com.fellaverse.backend.projection;

import java.util.Set;

/**
 * A Projection for the {@link com.fellaverse.backend.bean.Program} entity
 */
public interface ProgramInfo {
    String getProgram_name();

    Set<ExerciseInfo> getExercises();

    UserInfo getUser();

    /**
     * A Projection for the {@link com.fellaverse.backend.bean.Exercise} entity
     */
    interface ExerciseInfo {
        String getExercise_name();
    }

    /**
     * A Projection for the {@link com.fellaverse.backend.bean.User} entity
     */
    interface UserInfo {
        String getUsername();

        String getEmail();
    }
}