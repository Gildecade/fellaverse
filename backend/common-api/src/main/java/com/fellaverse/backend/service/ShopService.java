package com.fellaverse.backend.service;

import com.fellaverse.backend.bean.Course;
import com.fellaverse.backend.dto.CourseFindAllDTO;

import java.util.List;

public interface ShopService {

    String purchase(Course course);

    List<Course> findAll();

}
