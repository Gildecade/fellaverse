package com.fellaverse.backend.dto;

import com.fellaverse.backend.bean.Product;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fellaverse.backend.validator.ValidGroup;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.Order} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO implements Serializable {

    // neglect product
    @Null(groups = ValidGroup.Crud.Create.class, message = "should be null when creating")
    @NotNull(groups = ValidGroup.Crud.Update.class, message = " cannot be null")
    private Long id;
    @NotNull(groups = ValidGroup.Crud.Update.class, message = " cannot be null")
    private UserDTO user;
    @NotNull(groups = ValidGroup.Crud.Update.class, message = " cannot be null")
    private CourseDTO product;

    @NotNull(groups = ValidGroup.Crud.Update.class, message = " cannot be null")
    private Integer quantity;
    @NotNull(groups = ValidGroup.Crud.Update.class, message = " cannot be null")
    private Instant purchaseDateTime;
    @NotNull(groups = ValidGroup.Crud.Update.class, message = " cannot be null")
    private Float amount;


}