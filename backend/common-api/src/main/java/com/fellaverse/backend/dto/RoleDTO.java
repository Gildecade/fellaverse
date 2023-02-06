package com.fellaverse.backend.dto;

import com.fellaverse.backend.validator.ValidGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RoleDTO {
    @Null(groups = ValidGroup.Crud.Create.class, message = "Role ID should be null when creating")
    @NotNull(groups = {ValidGroup.Crud.Update.class, ValidGroup.Crud.Delete.class}, message = "Role ID cannot be null")
    private Long id;

    @NotBlank(groups = {ValidGroup.Crud.Create.class, ValidGroup.Crud.Update.class}, message = "Role name cannot be blank")
    private String roleName;

    @NotBlank(groups = {ValidGroup.Crud.Create.class, ValidGroup.Crud.Update.class}, message = "Role description cannot be blank")
    private String description;
}
