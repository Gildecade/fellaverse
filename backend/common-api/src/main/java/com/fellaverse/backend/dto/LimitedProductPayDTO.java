package com.fellaverse.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LimitedProductPayDTO implements Serializable {
    @NotNull(message = "User ID cannot be null!")
    private Long userId;
    @NotNull(message = "Order ID cannot be null!")
    private Long orderId;
}
