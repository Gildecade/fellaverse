package com.fellaverse.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fellaverse.backend.bean.Course;
import com.fellaverse.backend.bean.User;
import com.fellaverse.backend.controller.CourseController;
import com.fellaverse.backend.dto.CourseDTO;
import com.fellaverse.backend.enumerator.ProductStatus;
import com.fellaverse.backend.enumerator.UserStatus;
import com.fellaverse.backend.jwt.service.JWTTokenService;
import com.fellaverse.backend.jwt.util.JWTMemberDataService;
import com.fellaverse.backend.mapper.CourseMapper;
import com.fellaverse.backend.projection.CourseInfo;
import com.fellaverse.backend.service.CourseManageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CourseController.class)
@ContextConfiguration(classes = ShopServer_7501.class)
@ActiveProfiles({ "test" })
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JWTTokenService jwtMockTokenService;
    @MockBean
    private JWTMemberDataService jwtMemberDataService;
    @MockBean
    private CourseMapper courseMapper;
    @MockBean
    private CourseManageService courseService;

    private ObjectMapper objectMapper;

    private Course course;
    private CourseDTO courseDTO;
    private CourseInfo courseView;

    private String token;

    @BeforeEach
    public void setup(){
        course = new Course();
        course.setId(1L);
        course.setProductName("p1");
        course.setDescription("d1");
        course.setImageUrl("https://azure.storage.com");
        course.setPrice(4.2f);
        course.setCreatedDateTime(LocalDateTime.now());
        course.setProductStatus(ProductStatus.ACTIVE);
        course.setVideoUrl("https://azure.storage.com");

        User user = new User();
        user.setId(1L);
        user.setPhoneNumber("5195195199");
        user.setUsername("test1");
        user.setEmail("test@fellaverse.com");
        user.setWallet(100F);
        user.setStatus(UserStatus.NORMAL);

        course.setUser(user);

        courseDTO = new CourseDTO();
        courseDTO.setProductName("p1");
        courseDTO.setDescription("d1");
        courseDTO.setImageUrl("https://azure.storage.com");
        courseDTO.setPrice(4.2f);
        courseDTO.setCreatedDateTime(LocalDateTime.now());
        courseDTO.setProductStatus(ProductStatus.ACTIVE);
        courseDTO.setVideoUrl("https://azure.storage.com");

        //courseView = new CourseViewImpl();


        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    public void testGetCoursesList() throws Exception {
//        when(courseService.findAllCourse()).thenReturn(Collections.singletonList(courseView));
//
//        mockMvc.perform(get("/api/coach/course"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$").isArray())
//                .andExpect(jsonPath("$[0].id", is(1)))
//                .andExpect(jsonPath("$[0].product_name", is("p1")));

    }

    @Test
    public void testAddCourse() throws Exception {
        when(courseService.addCourse(course)).thenReturn(course);
        mockMvc.perform(
                        post("/api/coach/course")
                                .content(objectMapper.writeValueAsString(courseDTO))
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("Add course succeeded!"));
    }

    @Test
    public void testUpdateCourse() throws Exception {
        courseDTO.setId(1L);
        courseDTO.setProductName("p2");
        when(courseService.updateCourse(course)).thenReturn(true);
        //when(courseService.findCourseById(courseDTO.getId())).thenReturn(course);
        //when(courseService.updateCourse(courseMapper.partialUpdate(courseDTO, course))).thenReturn(true);


        mockMvc.perform(
                        put("/api/coach/course")
                                .content(objectMapper.writeValueAsString(courseDTO))
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Update course succeeded!"));
    }

    @Test
    public void testDeleteCourse() throws Exception {
        Course course = new Course();
        course.setId(1L);

        when(courseService.deleteCourse(course.getId())).thenReturn(true);
        mockMvc.perform(delete("/api/coach/course/" + course.getId()))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(content().string("Delete course succeeded!"));
    }
}
