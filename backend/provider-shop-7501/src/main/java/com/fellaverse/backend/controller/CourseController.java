package com.fellaverse.backend.controller;

import com.fellaverse.backend.bean.Course;
import com.fellaverse.backend.dto.CourseDTO;
import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.mapper.CourseMapper;
import com.fellaverse.backend.service.CourseManageService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop/course")
public class CourseController {
    private final CourseManageService courseManageService;
    private final CourseMapper courseMapper;

    public CourseController(CourseManageService courseManageService, CourseMapper courseMapper) {
        this.courseManageService = courseManageService;
        this.courseMapper = courseMapper;
    }

    @GetMapping("")
    public List<Course> findAllCourse() {
        return courseManageService.findAllCourse();
    }

    @JWTCheckToken(function = "add course")
    @PostMapping("")
    public String addCourse(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) CourseDTO courseDTO) {
        courseManageService.addCourse(courseMapper.toEntity(courseDTO));
        return "Add course succeeded!";
    }

    @JWTCheckToken(function = "update course")
    @PutMapping("")
    public String updateCourse(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) CourseDTO courseDTO) {
        Course course = courseManageService.findCourseById(courseDTO.getId());
        courseManageService.updateCourse(courseMapper.partialUpdate(courseDTO, course));
        return "Update course succeeded!";
    }

    @JWTCheckToken(function = "delete course")
    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        courseManageService.deleteCourse(id);
        return "Delete course succeeded!";
    }
}
