package com.fellaverse.backend.dto;

import com.fellaverse.backend.bean.Product;
import com.fellaverse.backend.enumerator.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link Product} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {
    private Long id;
    private String productName;
    private String description;
    private String imageUrl;
    private Float price;
    private LocalDateTime createdDateTime;
    private ProductStatus productStatus;
    private CourseOrderDTO course;
}