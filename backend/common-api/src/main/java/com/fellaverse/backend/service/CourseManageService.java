package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Course;

import java.util.List;

public interface CourseManageService {
    List<Course> findAllCourse();

    void addCourse(Course course);

    void deleteCourse(Long id);

    void updateCourse(Course course);

    Course findCourseById(Long id);

    List<Course> findAdminByCondition(Course course);
}
