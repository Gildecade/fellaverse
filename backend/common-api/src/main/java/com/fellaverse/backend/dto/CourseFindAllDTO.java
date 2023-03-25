package com.fellaverse.backend.dto;

import com.fellaverse.backend.enumerator.ProductStatus;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.Course} entity
 */
@Data
public class CourseFindAllDTO implements Serializable {
    private Long id;
    private String productName;
    private String description;
    private String imageUrl;
    private Float price;
    private LocalDateTime createdDateTime;
    private ProductStatus productStatus;
    private String videoUrl;
    private CoachDTO user;
}