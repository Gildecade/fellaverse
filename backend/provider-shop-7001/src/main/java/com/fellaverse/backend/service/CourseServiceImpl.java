package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Course;
import com.fellaverse.backend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseManageService {
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public boolean deleteCourse(Long id) {
        try {
            courseRepository.deleteById(id);
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateCourse(Course course) {
        try {
            courseRepository.save(course);
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    public Course findCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

}
