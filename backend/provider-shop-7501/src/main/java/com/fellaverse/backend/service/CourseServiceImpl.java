package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Course;
import com.fellaverse.backend.projection.CourseInfo;
import com.fellaverse.backend.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseManageService {
    private final CourseRepository courseRepository;
    private static Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseInfo> findAllCourse() {
        return courseRepository.findByProductNameNotNull();
    }

    @Override
    public Course addCourse(Course course) {
        logger.info("Course user id: " + course.getUserId());
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
