package com.fellaverse.backend;

import com.fellaverse.backend.dto.CourseView;
import com.fellaverse.backend.enumerator.ProductStatus;

import java.time.LocalDateTime;

public class CourseViewImpl implements CourseView {


    private String description;
    private String product_name;
    private String image_url;
    private String video_url;
    private Float price;
    private LocalDateTime create_date_time;
    private ProductStatus productStatus;

    @Override
    public String getProduct_name() {
        return product_name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getImage_url() {
        return image_url;
    }

    @Override
    public Float getPrice() {
        return price;
    }

    @Override
    public LocalDateTime getCreate_date_time() {
        return create_date_time;
    }

    @Override
    public ProductStatus getProductStatus() {
        return productStatus;
    }

    @Override
    public String getVideo_url() {
        return video_url;
    }
}
