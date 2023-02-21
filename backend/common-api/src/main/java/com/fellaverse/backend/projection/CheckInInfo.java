package com.fellaverse.backend.projection;

import java.time.LocalDateTime;

/**
 * A Projection for the {@link com.fellaverse.backend.bean.CheckIn} entity
 */
public interface CheckInInfo {
    LocalDateTime getStartDateTime();

    LocalDateTime getEndDateTime();
}