package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Override
    Optional<Course> findById(Long aLong);
}