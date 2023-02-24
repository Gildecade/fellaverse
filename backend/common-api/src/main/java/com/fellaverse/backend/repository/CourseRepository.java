package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.Course;
import com.fellaverse.backend.dto.CourseView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Override
    Optional<Course> findById(Long aLong);

    List<CourseView> findByProduct_nameNotNull();

}