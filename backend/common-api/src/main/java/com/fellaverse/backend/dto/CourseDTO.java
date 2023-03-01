package com.fellaverse.backend.dto;

import com.fellaverse.backend.enumerator.ProductStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.Course} entity
 */
@Data
public class CourseDTO implements Serializable {
    @Null(groups = com.fellaverse.backend.validator.ValidGroup.Crud.Create.class, message = "Course ID should be null when creating")
    @NotNull(groups = com.fellaverse.backend.validator.ValidGroup.Crud.Update.class, message = "Course ID cannot be null")
    private Long id;
    @NotBlank(groups = com.fellaverse.backend.validator.ValidGroup.Crud.Create.class, message = "Product name cannot be blank")
    private String productName;
    private String description;
    @Pattern(regexp = "^(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})$", message = "URL format is not right")
    private String imageUrl;
    @NotNull(groups = com.fellaverse.backend.validator.ValidGroup.Crud.Create.class, message = "Price cannot be blank")
    private Float price;
    private LocalDateTime createdDateTime;
    private ProductStatus productStatus;
    private Set<Long> userIds;
    @NotBlank(groups = com.fellaverse.backend.validator.ValidGroup.Crud.Create.class, message = "Video url cannot be blank")
    @Pattern(regexp = "^(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})$", message = "URL format is not right")
    private String videoUrl;

    private Long UserId;

}

