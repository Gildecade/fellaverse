package com.fellaverse.backend.dto;

import com.fellaverse.backend.validator.ValidGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.Schedule} entity
 */
@Data
public class ScheduleDTO implements Serializable {
    @Null(groups = ValidGroup.Crud.Create.class, message = "ID should be null when creating")
    @NotNull(groups = ValidGroup.Crud.Update.class, message = "ID cannot be null")
    private Long id;
    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "Schedule name cannot be blank")
    private String scheduleName;
    @NotNull(groups = ValidGroup.Crud.Create.class, message = "Workout days cannot be blank")
    private Integer workoutDays;
    @NotNull(groups = ValidGroup.Crud.Create.class, message = "Start time cannot be null")
    private LocalDateTime startTime;
    @NotNull(groups = ValidGroup.Crud.Create.class, message = "End time cannot be null")
    private LocalDateTime endTime;
    private Long userId;
}