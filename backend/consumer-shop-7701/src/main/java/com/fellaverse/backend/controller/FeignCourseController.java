package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.CourseBuyDTO;
import com.fellaverse.backend.dto.CourseDTO;
import com.fellaverse.backend.dto.CourseRecord;
import com.fellaverse.backend.service.FeignCourseService;
import com.fellaverse.backend.service.FeignShopService;
import com.fellaverse.backend.validator.ValidGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coach/course")
public class FeignCourseController {

    @Autowired
    private FeignCourseService feignCourseService;

    @GetMapping("")
    public List<CourseRecord> findAllCourse() {
        return feignCourseService.findAllCourse();
    }

    //private static Logger logger = LoggerFactory.getLogger(FeignCourseController.class);
    @PostMapping("")
    public String addCourse(@RequestBody @Validated(value = ValidGroup.Crud.Create.class) CourseDTO courseDTO) {
        ResponseEntity<String> result = feignCourseService.addCourse(courseDTO);
        Assert.isTrue(HttpStatus.CREATED == result.getStatusCode(), "Add course failed!");
        return result.getBody();
    }

    @PutMapping("")
    public String updateCourse(@RequestBody @Validated(value = ValidGroup.Crud.Update.class) CourseDTO courseDTO) {
        String result = feignCourseService.updateCourse(courseDTO);
        Assert.isTrue("Update course succeeded!".equals(result), "Update course failed!");
        return result;
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        ResponseEntity<String> result = feignCourseService.deleteCourse(id);
        Assert.isTrue(HttpStatus.OK == result.getStatusCode(), "Delete course failed!");
        return result.getBody();
    }
}
