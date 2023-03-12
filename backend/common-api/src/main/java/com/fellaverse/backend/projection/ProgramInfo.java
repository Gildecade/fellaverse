package com.fellaverse.backend.projection;

import java.util.Set;

/**
 * A Projection for the {@link com.fellaverse.backend.bean.Program} entity
 */
public interface ProgramInfo {
    String getProgramName();

    Set<ExerciseInfo> getExercises();

    UserInfo getUser();

    /**
     * A Projection for the {@link com.fellaverse.backend.bean.Exercise} entity
     */
    interface ExerciseInfo {
        String getExerciseName();
    }

    /**
     * A Projection for the {@link com.fellaverse.backend.bean.User} entity
     */
    interface UserInfo {
        String getUsername();

        String getEmail();
    }
}