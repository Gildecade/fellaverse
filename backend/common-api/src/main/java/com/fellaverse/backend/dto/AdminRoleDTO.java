package com.fellaverse.backend.dto;

import com.fellaverse.backend.validator.ValidGroup;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdminRoleDTO {
    @NotNull(groups = {ValidGroup.Crud.Create.class, ValidGroup.Crud.Update.class, ValidGroup.Crud.Delete.class}, message = "Admin Id cannot be null")
    private Long adminId;

    @NotNull(groups = {ValidGroup.Crud.Create.class, ValidGroup.Crud.Update.class, ValidGroup.Crud.Delete.class}, message = "Role Id cannot be null")
    private Long roleId;
}
