package com.fellaverse.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.Function} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FunctionIdNameDTO implements Serializable {
    private Long id;
    private String functionName;
}