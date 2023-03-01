package com.fellaverse.backend.dto;

import com.fellaverse.backend.bean.Course;
import com.fellaverse.backend.dto.CoachDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Course} entity
 */
@Data
public class CourseOrderDTO implements Serializable {
    private final String videoUrl;
    private final CoachDTO user;
}