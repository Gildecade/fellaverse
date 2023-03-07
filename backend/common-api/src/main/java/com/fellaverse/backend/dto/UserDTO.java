package com.fellaverse.backend.dto;

import com.fellaverse.backend.validator.ValidGroup;
import com.fellaverse.backend.annotation.EnumString;
import com.fellaverse.backend.enumerator.UserStatus;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class UserDTO implements Serializable {
    @Null(groups = ValidGroup.Crud.Create.class, message = "User ID should be null when creating")
    @NotNull(groups = {ValidGroup.Crud.Update.class, ValidGroup.Crud.Delete.class}, message = "User ID cannot be null")
    private Long id;

    @NotBlank(groups = {ValidGroup.Crud.Create.class, ValidGroup.Crud.Update.class}, message = "Username cannot be blank")
    @Size(max = 60)
    private String username;

    @NotBlank(groups = {ValidGroup.Crud.Create.class, ValidGroup.Crud.Update.class, ValidGroup.Crud.Query.class}, message = "Password cannot be blank")
    @Size(max = 40)
    private String password;

    @NotBlank(groups = {ValidGroup.Crud.Create.class, ValidGroup.Crud.Update.class, ValidGroup.Crud.Query.class}, message = "Email cannot be blank")
    @Email(message = "Please enter correct Email address")
    @Size(max = 255)
    private String email;

    @NotBlank(groups = {ValidGroup.Crud.Create.class, ValidGroup.Crud.Update.class}, message = "Phone number cannot be blank")
    @Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$", message = "Phone number format is wrong")
    @Size(max = 60)
    private String phoneNumber;

    @Null(groups = ValidGroup.Crud.Create.class, message = "Wallet should be null when registering")
    @NotNull(groups = ValidGroup.Crud.Update.class, message = "Wallet cannot be null")
    private Long wallet;

    @Null(groups = ValidGroup.Crud.Create.class, message = "Status should be null when registering")
    @NotNull(groups = ValidGroup.Crud.Update.class, message = "Status cannot be null")
    @EnumString(value = {"normal","locked","unknown"}, groups = ValidGroup.Crud.Update.class, message="invalid status")
    private UserStatus status;
}
