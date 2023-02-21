package com.fellaverse.backend.dto;

import com.fellaverse.backend.annotation.EnumString;
import com.fellaverse.backend.enumerator.UserStatus;
import com.fellaverse.backend.validator.ValidGroup;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.User} entity
 */
@Data
public class UserBalanceStatusDTO implements Serializable {
    @NotNull(message = "User ID cannot be null")
    private Long id;

    private Long wallet;
    @EnumString(value = {"normal","locked","unknown"}, message="invalid status")
    @NotNull(message = "User status cannot be null")
    private UserStatus status;

}