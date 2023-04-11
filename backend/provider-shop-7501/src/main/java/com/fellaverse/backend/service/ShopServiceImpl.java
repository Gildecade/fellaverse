package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Course;
import com.fellaverse.backend.dto.CourseFindAllDTO;
import com.fellaverse.backend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public String purchase(Course course) {
        return course.getVideoUrl();
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }


}
