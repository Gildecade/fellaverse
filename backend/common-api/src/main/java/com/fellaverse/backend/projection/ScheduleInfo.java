package com.fellaverse.backend.projection;

import java.time.LocalDateTime;

/**
 * A Projection for the {@link com.fellaverse.backend.bean.Schedule} entity
 */
public interface ScheduleInfo {
    String getSchedule_name();

    Integer getWorkout_days();

    LocalDateTime getStart_time();

    LocalDateTime getEnd_time();

    UserInfo getUser();

    /**
     * A Projection for the {@link com.fellaverse.backend.bean.User} entity
     */
    interface UserInfo {
        String getUsername();

        String getEmail();
    }
}