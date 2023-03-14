package com.fellaverse.backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LimitedProductPurchaseDTO implements Serializable {
    @NotNull(message = "Limited product ID cannot be null!")
    private Long id;
    @NotNull(message = "User ID cannot be null!")
    private Long userId;
    @NotNull(message = "Quantity cannot be null!")
    @Min(value = 0, message = "Quantity cannot be negative")
    @Max(value = 10, message = "Quantity cannot be greater than 10")
    private Integer quantity;
    @NotNull(message = "Purchase date time cannot be null!")
    private LocalDateTime purchaseDateTime;
}
