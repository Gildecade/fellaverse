package com.fellaverse.backend.dto;

import com.fellaverse.backend.validator.ValidGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.Function} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FunctionDTO implements Serializable {
    @Null(groups = ValidGroup.Crud.Create.class, message = "ID should be null when creating")
    @NotNull(groups = ValidGroup.Crud.Update.class, message = "ID cannot be null")
    private Long id;
    @NotBlank(message = "functionName name cannot be blank")
    @Size(max = 60)
    private String functionName;
    @NotBlank(message = "function description cannot be blank")
    @Size(max = 256)
    private String description;
}