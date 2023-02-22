package com.fellaverse.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.OrderId} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderIdDTO implements Serializable {
    @NotNull
    private Long userId;
    @NotNull
    private Long productId;
}