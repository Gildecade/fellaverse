package com.fellaverse.backend.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.User} entity
 */
@Data
public class UserIdDTO implements Serializable {
    private final Long id;
}