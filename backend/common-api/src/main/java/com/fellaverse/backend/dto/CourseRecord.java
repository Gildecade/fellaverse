package com.fellaverse.backend.dto;

import com.fellaverse.backend.enumerator.ProductStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.Course} entity
 */
public record CourseRecord(Long id, String productName, String description, String imageUrl, Float price,
                           LocalDateTime createdDateTime, ProductStatus productStatus,
                           UserRecord user) implements Serializable {
    /**
     * A DTO for the {@link com.fellaverse.backend.bean.User} entity
     */
    public record UserRecord(String username, String email) implements Serializable {
    }
}