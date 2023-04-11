package com.fellaverse.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.Record} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordAddDTO implements Serializable {
    @NotNull(message = "User ID cannot be null!")
    private Long userId;
    @NotNull(message = "Create date time cannot be null!")
    private LocalDateTime createDateTime;
    @NotNull(message = "Weights cannot be null!")
    private Float weights;
    @NotNull(message = "Quantity cannot be null!")
    private Integer quantity;
    @NotNull(message = "Number of sets cannot be null!")
    private Integer numOfSets;
    @NotNull(message = "Exercise ID cannot be null!")
    private Long exerciseId;
}