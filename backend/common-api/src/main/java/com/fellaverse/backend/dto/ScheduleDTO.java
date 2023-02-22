package com.fellaverse.backend.dto;

import com.fellaverse.backend.validator.ValidGroup;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.Schedule} entity
 */
@Data
public class ScheduleDTO implements Serializable {
    private final Long id;
    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "Schedule name cannot be blank")
    private final String schedule_name;
    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "Workout days cannot be blank")
    private final Integer workout_days;
    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "Start time cannot be blank")
    private final LocalDateTime start_time;
    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "End cannot be blank")
    private final LocalDateTime end_time;
    private final Long userId;
}