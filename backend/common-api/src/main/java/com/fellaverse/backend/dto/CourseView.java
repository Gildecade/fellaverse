package com.fellaverse.backend.dto;

import com.fellaverse.backend.enumerator.ProductStatus;

import java.time.LocalDateTime;

public interface CourseView {
    String getProduct_name();

    String getDescription();

    String getImage_url();

    Float getPrice();

    LocalDateTime getCreate_date_time();

    ProductStatus getProductStatus();

    String getVideo_url();

    User getUser();
    interface User {
        String getUsername();

        String getEmail();
    }
}
