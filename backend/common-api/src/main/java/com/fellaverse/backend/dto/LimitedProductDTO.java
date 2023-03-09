package com.fellaverse.backend.dto;

import com.fellaverse.backend.enumerator.ProductStatus;
import com.fellaverse.backend.validator.ValidGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.LimitedProduct} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimitedProductDTO implements Serializable {
    @Null(groups = ValidGroup.Crud.Create.class, message = "Limited product ID should be null when creating")
    @NotNull(groups = ValidGroup.Crud.Update.class, message = "Limited product ID cannot be null")
    private Long id;
    @Size(max = 60)
    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "Product name cannot be blank")
    private String productName;
    @Size(max = 255)
    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "Description cannot be blank")
    private String description;
    @NotNull(groups = ValidGroup.Crud.Create.class, message = "Quantity cannot be null")
    private Integer quantity;
    @Size(max = 255)
    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "Image url cannot be blank")
    private String imageUrl;
    @NotNull(groups = ValidGroup.Crud.Create.class, message = "Price cannot be null")
    private Float price;
    @NotNull(groups = ValidGroup.Crud.Create.class, message = "Created date time cannot be null")
    private LocalDateTime createdDateTime;
    @NotNull(groups = ValidGroup.Crud.Create.class, message = "On sale date time cannot be null")
    private LocalDateTime saleDateTime;
    @NotNull(groups = ValidGroup.Crud.Create.class, message = "Product status cannot be null")
    private ProductStatus productStatus;
}