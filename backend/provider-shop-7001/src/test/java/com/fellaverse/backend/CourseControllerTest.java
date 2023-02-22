package com.fellaverse.backend;

import com.fellaverse.backend.controller.CourseManageController;
import com.fellaverse.backend.service.CourseManageService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CourseManageController.class)
public class CourseControllerTest {
/*
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseManageService courseService;




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

 */
}
