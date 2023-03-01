package com.fellaverse.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.Course} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseBuyDTO implements Serializable {
    @NotNull
    private Long id;
    @NotNull
    private Long userId;
    @NotNull
    private Integer quantity;
    @NotNull
    private Float price;
    @NotNull
    private Float amount;
    @NotNull
    private Instant purchaseDateTime;
}