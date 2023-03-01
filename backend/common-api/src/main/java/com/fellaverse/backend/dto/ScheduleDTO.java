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
    private String scheduleName;
    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "Workout days cannot be blank")
    private Integer workoutDays;
    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "Start time cannot be blank")
    private LocalDateTime startTime;
    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "End cannot be blank")
    private LocalDateTime endTime;
    private Long userId;
}