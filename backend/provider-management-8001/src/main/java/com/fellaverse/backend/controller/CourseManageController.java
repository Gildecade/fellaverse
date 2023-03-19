package com.fellaverse.backend.controller;

import com.fellaverse.backend.bean.Course;
import com.fellaverse.backend.dto.CourseDTO;
import com.fellaverse.backend.jwt.annotation.JWTCheckToken;
import com.fellaverse.backend.mapper.CourseMapper;
import com.fellaverse.backend.projection.CourseInfo;
import com.fellaverse.backend.service.CourseManageService;
import com.fellaverse.backend.validator.ValidGroup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/management/shop/course")
public class CourseManageController {
    private final CourseManageService courseManageService;
    private final CourseMapper courseMapper;

    public CourseManageController(CourseManageService courseManageService, CourseMapper courseMapper) {
        this.courseManageService = courseManageService;
        this.courseMapper = courseMapper;
    }

    @GetMapping("")
    public List<CourseInfo> findAllCourse() {
        return courseManageService.findAllCourse();
    }

    @JWTCheckToken(role = {"SuperAdmin", "ShopAdmin"})
    @PostMapping("")
    public ResponseEntity<String> addCourse(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) CourseDTO courseDTO) {
        courseManageService.addCourse(courseMapper.toEntity(courseDTO));
        return new ResponseEntity<>("Add course succeeded!", HttpStatus.CREATED);
    }

    @JWTCheckToken(role = {"SuperAdmin", "ShopAdmin"})
    @PutMapping("")
    public String updateCourse(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) CourseDTO courseDTO) {
        Course course = courseManageService.findCourseById(courseDTO.getId());
        courseManageService.updateCourse(courseMapper.partialUpdate(courseDTO, course));
        return "Update course succeeded!";
    }

    @JWTCheckToken(role = {"SuperAdmin", "ShopAdmin"})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable("id") Long id) {
        courseManageService.deleteCourse(id);
        return new ResponseEntity<>("Delete course succeeded!", HttpStatus.OK);
    }
}
