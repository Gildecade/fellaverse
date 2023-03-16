package com.fellaverse.backend.dto;

import com.fellaverse.backend.validator.ValidGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class RoleDTO implements Serializable {
    @Null(groups = ValidGroup.Crud.Create.class, message = "Role ID should be null when creating")
    @NotNull(groups = {ValidGroup.Crud.Update.class, ValidGroup.Crud.Delete.class}, message = "Role ID cannot be null")
    private Long id;

    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "Role name cannot be blank")
    @Size(max = 60)
    private String roleName;

    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "Role description cannot be blank")
    @Size(max = 255)
    private String description;
}
