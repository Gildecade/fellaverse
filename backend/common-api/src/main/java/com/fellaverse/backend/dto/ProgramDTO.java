package com.fellaverse.backend.dto;

import com.fellaverse.backend.validator.ValidGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.Program} entity
 */
@Data
public class ProgramDTO implements Serializable {
    @Null(groups = ValidGroup.Crud.Create.class, message = "Program ID should be null when creating")
    @NotNull(groups = {ValidGroup.Crud.Update.class, ValidGroup.Crud.Delete.class}, message = "Program ID cannot be null")
    private Long id;
    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "Program name cannot be blank")
    private String programName;
    private Set<Long> exerciseIds;
}