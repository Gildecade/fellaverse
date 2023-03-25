package com.fellaverse.backend.dto;

import com.fellaverse.backend.validator.ValidGroup;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO implements Serializable {
    @NotNull(groups = ValidGroup.Crud.Update.class, message = "ID cannot be null when updating")
    @Null(groups = ValidGroup.Crud.Create.class, message = "ID should be null when creating")
    private Long id;
    @NotNull(groups = ValidGroup.Crud.Create.class, message = "Quantity should be null when creating")
    private Integer quantity;
    @NotNull(groups = ValidGroup.Crud.Create.class, message = "Purchase Date Time should be null when creating")
    private LocalDateTime purchaseDateTime;
    @NotNull(groups = ValidGroup.Crud.Create.class, message = "Amount should be null when creating")
    private Float amount;
    @NotNull(groups = ValidGroup.Crud.Create.class, message = "UserId should be null when creating")
    private Long userId;
    @NotNull(groups = ValidGroup.Crud.Create.class, message = "ProductId should be null when creating")
    private Long productId;
}