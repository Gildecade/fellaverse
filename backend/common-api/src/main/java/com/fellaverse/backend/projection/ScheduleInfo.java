package com.fellaverse.backend.projection;

import java.time.LocalDateTime;

/**
 * A Projection for the {@link com.fellaverse.backend.bean.Schedule} entity
 */
public interface ScheduleInfo {
    String getScheduleName();

    Integer getWorkoutDays();

    LocalDateTime getStartTime();

    LocalDateTime getEndTime();

    UserInfo getUser();

    /**
     * A Projection for the {@link com.fellaverse.backend.bean.User} entity
     */
    interface UserInfo {
        String getUsername();

        String getEmail();
    }
}