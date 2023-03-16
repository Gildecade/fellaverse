package com.fellaverse.backend.dto;

import com.fellaverse.backend.enumerator.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlashSaleOrderAddDTO {
    private Integer quantity;
    private LocalDateTime purchaseDateTime;
    private Float amount;
    private OrderStatus orderStatus;
    private Long userId;
    private Long limitedProductId;
}
