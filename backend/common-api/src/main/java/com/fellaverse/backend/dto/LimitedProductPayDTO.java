package com.fellaverse.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LimitedProductPayDTO implements Serializable {
    @NotNull
    private Long id;
    @NotNull
    private Long userId;
    @NotNull
    private Long orderId;
    @NotNull
    private Integer quantity;
    @NotNull
    private LocalDateTime purchaseDateTime;
}
