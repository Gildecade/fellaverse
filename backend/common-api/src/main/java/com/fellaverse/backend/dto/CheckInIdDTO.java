package com.fellaverse.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.CheckInId} entity
 */
@Data
public class CheckInIdDTO implements Serializable {
    @NotNull
    private final Long id;
    @NotNull
    private final Long userId;
}