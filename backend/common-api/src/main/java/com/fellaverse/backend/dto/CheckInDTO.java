package com.fellaverse.backend.dto;

import com.fellaverse.backend.validator.ValidGroup;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CheckInDTO implements Serializable {
    @Null(groups = ValidGroup.Crud.Create.class, message = "CheckIn ID should be null when creating")
    @NotNull(groups = {ValidGroup.Crud.Update.class, ValidGroup.Crud.Delete.class}, message = "CheckIn ID cannot be null")
    private Long id;

    @NotNull(groups = {ValidGroup.Crud.Create.class, ValidGroup.Crud.Update.class, ValidGroup.Crud.Delete.class}, message = "User ID cannot be null")
    private Long userId;

    @NotNull(groups = {ValidGroup.Crud.Create.class, ValidGroup.Crud.Update.class}, message = "Start date time cannot be null")
    private LocalDateTime startDateTime;

    @NotNull(groups = {ValidGroup.Crud.Create.class, ValidGroup.Crud.Update.class}, message = "End date time cannot be null")
    private LocalDateTime endDateTime;

    private Float weight;

}

