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
    private final Long id;
    private final String productName;
    private final String description;
    private final String imageUrl;
    private final Float price;
    private final LocalDateTime createdDateTime;
    private final ProductStatus productStatus;
    private final String videoUrl;
    private final CoachDTO user;
}