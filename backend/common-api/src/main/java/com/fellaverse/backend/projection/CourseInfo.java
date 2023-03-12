package com.fellaverse.backend.projection;

import com.fellaverse.backend.enumerator.ProductStatus;
import java.time.LocalDateTime;

/**
 * A Projection for the {@link com.fellaverse.backend.bean.Course} entity
 */
public interface CourseInfo {
    String getProductName();

    String getDescription();

    String getImageUrl();

    Float getPrice();

    LocalDateTime getCreatedDateTime();

    ProductStatus getProductStatus();

    String getVideoUrl();

    UserInfo getUser();

    /**
     * A Projection for the {@link com.fellaverse.backend.bean.User} entity
     */
    interface UserInfo {
        String getUsername();

        String getEmail();
    }
}