package com.fellaverse.backend.dto;

import com.fellaverse.backend.validator.ValidGroup;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class AdminDTO implements Serializable {
    @Null(groups = ValidGroup.Crud.Create.class, message = "Admin ID should be null when creating")
    @NotNull(groups = ValidGroup.Crud.Update.class, message = "Admin ID cannot be null")
    private Long id;

    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "Username cannot be blank")
    @Size(max = 60)
    private String username;

    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "Password cannot be blank")
    @Size(max = 60)
    private String password;

    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "Email cannot be blank")
    @Email(message = "Please enter correct Email address")
    @Size(max = 255)
    private String email;

    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "Phone number cannot be blank")
    @Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$", message = "Phone number format is wrong")
    @Size(max = 60)
    private String phoneNumber;

    private List<Long> roleIds;
}
