package com.fellaverse.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.Order} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderFindAllDTO implements Serializable {
    private Long id;
    private Integer quantity;
    private LocalDateTime purchaseDateTime;
    private Float amount;
    private UserBasicInfoDTO user;
    private ProductDTO product;
}