package com.fellaverse.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fellaverse.backend.bean.Course;
import com.fellaverse.backend.controller.CourseManageController;
import com.fellaverse.backend.enumerator.ProductStatus;
import com.fellaverse.backend.service.CourseManageService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CourseManageController.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseManageService courseService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Course course;

    @BeforeEach
    public void setup(){
        course = new Course();
        course.setId(1L);
        course.setProduct_name("p1");
        course.setDescription("d1");
        course.setImage_url("https://azure.storage.com");
        course.setPrice(4.2f);
        course.setCreated_date_time(LocalDateTime.now());
        course.setProductStatus(ProductStatus.ACTIVE);
        course.setVideo_url("https://azure.storage.com");
    }

    @Test
    public void testGetCoursesList() throws Exception {
        when(courseService.findAllCourse()).thenReturn(Collections.singletonList(course));
        mockMvc.perform(get("/api/shop/course"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testAddCourse() throws Exception {
        when(courseService.addCourse(course)).thenReturn(course);
        mockMvc.perform(
                        post("/api/shop/course")
                                .content(objectMapper.writeValueAsString(course))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.product_name", is("test1")))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void testDeleteCourse() throws Exception {
        Course course = new Course();
        course.setId(1L);

        when(courseService.deleteCourse(course.getId())).thenReturn(true);
        mockMvc.perform(delete("/api/orders/" + course.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
