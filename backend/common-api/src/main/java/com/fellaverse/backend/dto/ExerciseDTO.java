package com.fellaverse.backend.dto;

import com.fellaverse.backend.validator.ValidGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.Exercise} entity
 */
@Data
public class ExerciseDTO implements Serializable {
    @Null(groups = ValidGroup.Crud.Create.class, message = "ID should be null when creating")
    @NotNull(groups = ValidGroup.Crud.Update.class, message = "ID cannot be null")
    private final Long id;
    @NotBlank(message = "exercise name cannot be blank")
    private final String exercise_name;
}