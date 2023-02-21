package com.fellaverse.backend.dto;

import com.fellaverse.backend.bean.CheckInId;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.CheckIn} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckInDTO implements Serializable {
    private CheckInId id;
    private UserIdDTO user;
    @NotNull
    private Instant startDateTime;
    @NotNull
    private Instant endDateTime;
    private Float weight;
}