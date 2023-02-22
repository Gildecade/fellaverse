package com.fellaverse.backend.dto;

import com.fellaverse.backend.annotation.EnumString;
import com.fellaverse.backend.enumerator.UserStatus;
import com.fellaverse.backend.validator.ValidGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.User} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicInfoDTO implements Serializable {
    @Null(groups = ValidGroup.Crud.Create.class, message = "Admin ID should be null when creating")
    @NotNull(groups = ValidGroup.Crud.Update.class, message = "Admin ID cannot be null")
    private Long id;
    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "Username cannot be blank")
    private String username;
    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "Email cannot be blank")
    private String email;
    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "Phone number cannot be blank")
    private String phoneNumber;
    @NotNull(message = "ID cannot be null")
    private Long wallet;
    @NotNull(groups = ValidGroup.Crud.Create.class, message = "status annot be null")
    @EnumString(value = {"normal","locked","unknown"}, groups = ValidGroup.Crud.Update.class, message="invalid status")
    private UserStatus status;
}