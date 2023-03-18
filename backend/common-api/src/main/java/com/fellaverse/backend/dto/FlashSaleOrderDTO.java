package com.fellaverse.backend.dto;

import com.fellaverse.backend.enumerator.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.FlashSaleOrder} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlashSaleOrderDTO implements Serializable {
    private Long id;
    private Integer quantity;
    private LocalDateTime purchaseDateTime;
    private Float amount;
    private OrderStatus orderStatus;
    private UserBasicInfoDTO user;
    private LimitedProductDTO limitedProduct;
}