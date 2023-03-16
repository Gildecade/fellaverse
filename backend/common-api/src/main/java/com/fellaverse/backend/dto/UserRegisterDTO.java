package com.fellaverse.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.User} entity
 */
@Data
@Accessors(chain = true)
public class UserRegisterDTO implements Serializable {
    @NotBlank(message = "Username cannot be blank")
    @Size(max = 60)
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(max = 40)
    private String password;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please enter correct Email address")
    @Size(max = 255)
    private String email;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$", message = "Phone number format is wrong")
    @Size(max = 60)
    private String phoneNumber;
}