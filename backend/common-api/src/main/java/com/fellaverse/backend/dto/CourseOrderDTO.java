package com.fellaverse.backend.dto;

import com.fellaverse.backend.bean.Course;
import com.fellaverse.backend.dto.CoachDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link Course} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseOrderDTO implements Serializable {
    private String videoUrl;
    private CoachDTO user;
}