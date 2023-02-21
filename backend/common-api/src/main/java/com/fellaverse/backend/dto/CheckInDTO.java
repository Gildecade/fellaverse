package com.fellaverse.backend.dto;

import com.fellaverse.backend.validator.ValidGroup;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.CheckIn} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckInDTO implements Serializable {
    @Null(groups = ValidGroup.Crud.Create.class, message = "ID should be null when creating")
    @NotNull(groups = ValidGroup.Crud.Update.class, message = "ID cannot be null")
    private Long id;
    @NotNull(groups = {ValidGroup.Crud.Update.class, ValidGroup.Crud.Create.class}, message = "date time cannot be null")
    private LocalDateTime startDateTime;
    @NotNull(groups = {ValidGroup.Crud.Update.class, ValidGroup.Crud.Create.class}, message = "date time cannot be null")
    private LocalDateTime endDateTime;
    private Float weight;
    @NotNull(groups = ValidGroup.Crud.Update.class, message = "ID cannot be null")
    private UserIdDTO user;
}