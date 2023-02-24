package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Course;
import com.fellaverse.backend.dto.CourseView;

import java.util.List;

public interface CourseManageService {
    List<CourseView> findAllCourse();

    Course addCourse(Course course);

    boolean deleteCourse(Long id);

    boolean updateCourse(Course course);

    Course findCourseById(Long id);

}
