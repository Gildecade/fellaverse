package com.fellaverse.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fellaverse.backend.validator.ValidGroup;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.Record} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordDTO implements Serializable {
    @NotNull(groups = ValidGroup.Crud.Update.class, message = "User ID cannot be null")
    private RecordIdDTO id;
    @NotNull(groups = ValidGroup.Crud.Update.class, message = "User ID cannot be null")
    private UserDTO user;
    @NotNull(groups = ValidGroup.Crud.Create.class, message = "Username cannot be blank")
    private LocalDateTime createDateTime;
    @NotNull(groups = ValidGroup.Crud.Create.class, message = "Username cannot be blank")
    private Float weights;
    @NotNull(groups = ValidGroup.Crud.Create.class, message = "Username cannot be blank")
    private Integer quantity;
    @NotNull(groups = ValidGroup.Crud.Create.class, message = "Username cannot be blank")
    private Integer numOfSets;
    @NotNull(groups = ValidGroup.Crud.Create.class, message = "Username cannot be blank")
    private ExerciseDTO exercise;
}