package com.fellaverse.backend.repository;

import com.fellaverse.backend.bean.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}