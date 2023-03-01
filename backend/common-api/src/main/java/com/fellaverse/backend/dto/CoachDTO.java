package com.fellaverse.backend.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.fellaverse.backend.bean.User} entity
 */
@Data
public class CoachDTO implements Serializable {
    private final Long id;
    private final String username;
    private final String email;
    private final String phoneNumber;
}