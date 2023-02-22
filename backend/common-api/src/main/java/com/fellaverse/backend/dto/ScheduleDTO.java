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
    private Long id;
    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "Schedule name cannot be blank")
    private String schedule_name;
    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "Workout days cannot be blank")
    private Integer workout_days;
    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "Start time cannot be blank")
    private LocalDateTime start_time;
    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "End cannot be blank")
    private LocalDateTime end_time;
    private Long userId;
}