package com.fellaverse.backend.dto;

import com.fellaverse.backend.bean.OrderId;
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
public class OrderDTO implements Serializable {

    // neglect product
    private OrderId id;
    private UserDTO user;
    private Integer quantity;
    private LocalDateTime purchaseDateTime;
    private Float amount;


}