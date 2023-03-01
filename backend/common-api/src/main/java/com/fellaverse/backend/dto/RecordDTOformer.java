package com.fellaverse.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.experimental.Accessors;
import com.fellaverse.backend.validator.ValidGroup;


import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.Record} entity
 */
@Data
@Accessors(chain = true)
//@IdClass(RecordDTOformer.class)
public class RecordDTOformer implements Serializable {
    
    @Null(groups = ValidGroup.Crud.Create.class, message = "Record ID should be null when creating")
    @NotNull(groups = {ValidGroup.Crud.Update.class, ValidGroup.Crud.Delete.class}, message = "Role ID cannot be null")
    private final Long id;

    @NotNull(groups = {ValidGroup.Crud.Create.class, ValidGroup.Crud.Update.class, ValidGroup.Crud.Delete.class}, message = "user cannot be null")
    private final UserDTO user;

    @NotNull(groups = {ValidGroup.Crud.Create.class}, message = "exercise cannot be null")
    private final ExerciseDTO exercise;

    @NotNull(groups = {ValidGroup.Crud.Create.class}, message = "createDateTime cannot be null")
    private final LocalDateTime createDateTime;

    @NotNull(groups = {ValidGroup.Crud.Create.class}, message = "weights cannot be null")
    private final Float weights;

    @NotNull(groups = {ValidGroup.Crud.Create.class}, message = "quantity cannot be null")
    private final Integer quantity;

    @NotNull(groups = {ValidGroup.Crud.Create.class}, message = "numOfSets cannot be null")
    private final Integer numOfSets;
}