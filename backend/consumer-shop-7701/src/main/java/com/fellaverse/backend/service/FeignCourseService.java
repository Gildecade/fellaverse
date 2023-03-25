package com.fellaverse.backend.service;
import com.fellaverse.backend.config.ConsumerErrorDecoder;
import com.fellaverse.backend.config.FeignBasicAuthRequestInterceptor;
import com.fellaverse.backend.dto.CourseDTO;
import com.fellaverse.backend.dto.CourseRecord;
import com.fellaverse.backend.projection.CourseInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(value = "provider-gateway", contextId = "coachCourse", path = "/api/coach/course",
        configuration = {FeignBasicAuthRequestInterceptor.class, ConsumerErrorDecoder.class})
public interface FeignCourseService {

    @GetMapping("")
    List<CourseRecord> findAllCourse();

    @PostMapping("")
    ResponseEntity<String> addCourse(CourseDTO courseDTO);

    @PutMapping("")
    String updateCourse(CourseDTO courseDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteCourse(@PathVariable("id") Long id);

}
