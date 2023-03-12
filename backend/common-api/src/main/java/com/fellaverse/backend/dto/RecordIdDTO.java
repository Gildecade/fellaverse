package com.fellaverse.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fellaverse.backend.validator.ValidGroup;

import java.io.Serializable;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.RecordId} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordIdDTO implements Serializable {

    @Null(groups = ValidGroup.Crud.Create.class, message = "Record ID should be null when creating")
    @NotNull(groups = ValidGroup.Crud.Update.class, message = "Record ID cannot be null")
    private Long id;
    @NotNull
    private Long userId;
}