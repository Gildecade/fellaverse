package com.fellaverse.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.fellaverse.backend.validator.ValidGroup;

import java.io.Serializable;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.Exercise} entity
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDTO implements Serializable {

    @Null(groups = ValidGroup.Crud.Create.class, message = "Exercise ID should be null when creating")
    @NotNull(groups = {ValidGroup.Crud.Update.class, ValidGroup.Crud.Delete.class}, message = "Exercise ID cannot be null")
    private Long id;

    @NotBlank(groups = {ValidGroup.Crud.Create.class}, message = "Exercise name cannot be null")
    private String exercise_name;

}