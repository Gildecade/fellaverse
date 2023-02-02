package com.fellaverse.backend.dto;

import com.fellaverse.backend.Validator.ValidGroup;
import com.fellaverse.backend.enumerator.UserStatus;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserDTO {
    @Null(groups = ValidGroup.Crud.Create.class, message = "User ID should be null when creating")
    @NotNull(groups = {ValidGroup.Crud.Update.class, ValidGroup.Crud.Delete.class}, message = "User ID cannot be null")
    private Long id;

    @NotBlank(groups = {ValidGroup.Crud.Create.class, ValidGroup.Crud.Update.class}, message = "Username cannot be blank")
    private String username;

    @NotBlank(groups = {ValidGroup.Crud.Create.class, ValidGroup.Crud.Update.class}, message = "Password cannot be blank")
    private String password;

    @NotBlank(groups = {ValidGroup.Crud.Create.class, ValidGroup.Crud.Update.class}, message = "Email cannot be blank")
    @Email(message = "Please enter correct Email address")
    private String email;

    @NotBlank(groups = {ValidGroup.Crud.Create.class, ValidGroup.Crud.Update.class}, message = "Phone number cannot be blank")
    @Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$", message = "Phone number format is wrong")
    private String phoneNumber;

    @Null(groups = ValidGroup.Crud.Create.class, message = "Wallet should be null when registering")
    @NotNull(groups = ValidGroup.Crud.Update.class, message = "Wallet cannot be null")
    private Long wallet;

    @Null(groups = ValidGroup.Crud.Create.class, message = "Status should be null when registering")
    @NotNull(groups = ValidGroup.Crud.Update.class, message = "Status cannot be null")
    private UserStatus status;
}
