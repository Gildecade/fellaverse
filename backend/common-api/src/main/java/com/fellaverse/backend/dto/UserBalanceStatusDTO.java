package com.fellaverse.backend.dto;

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

    private Enum status;

}