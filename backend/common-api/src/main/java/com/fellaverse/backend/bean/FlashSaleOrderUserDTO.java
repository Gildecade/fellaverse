package com.fellaverse.backend.bean;

import com.fellaverse.backend.dto.LimitedProductDTO;
import com.fellaverse.backend.enumerator.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link FlashSaleOrder} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlashSaleOrderUserDTO implements Serializable {
    private Long id;
    private Integer quantity;
    private LocalDateTime purchaseDateTime;
    private Float amount;
    private OrderStatus orderStatus;
    private LimitedProductDTO limitedProduct;
}