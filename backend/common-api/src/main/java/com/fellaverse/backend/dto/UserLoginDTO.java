package com.fellaverse.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.User} entity
 */
@Data
@Accessors(chain = true)
public class UserLoginDTO implements Serializable {
    @NotBlank(message = "Password cannot be blank")
    @Size(max = 40)
    private String password;
    @NotBlank(message = "Email cannot be blank")
    @Size(max = 255)
    private String email;
}