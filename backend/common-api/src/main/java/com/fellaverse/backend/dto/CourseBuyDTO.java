package com.fellaverse.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.Course} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseBuyDTO implements Serializable {
    private Long id;
    private Long userId;
    private Integer quantity;
    private Float price;
    private Float amount;
    private LocalDateTime purchaseDateTime;
}