//package com.fellaverse.backend;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fellaverse.backend.bean.Schedule;
//import com.fellaverse.backend.bean.User;
//import com.fellaverse.backend.controller.ScheduleController;
//import com.fellaverse.backend.service.ScheduleService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Collections;
//
//import java.time.LocalDateTime;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.hamcrest.Matchers.is;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(ScheduleController.class)
//@ContextConfiguration(classes = ScheduleControllerTest.class)
//public class ScheduleControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ScheduleService scheduleService;
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    private Schedule schedule;
//    private User user;
//
//    @BeforeEach
//    public void setup(){
//        schedule = new Schedule();
//        schedule.setId(1L);
//        schedule.setSchedule_name("p1");
//        schedule.setEnd_time(LocalDateTime.MAX);
//        schedule.setStart_time(LocalDateTime.now());
//        schedule.setWorkout_days(12345);
//
//        user = new User();
//        user.setId(1L);
//        user.setEmail("test@fellaverse.com");
//        user.setUsername("test1");
//        user.setPassword("hello");
//        user.setPhoneNumber("5965965966");
//
//        schedule.setUser(user);
//    }
//
//    @Test
//    public void testGetSchedulesList() throws Exception {
//        when(scheduleService.findAllSchedule(1L)).thenReturn(Collections.singletonList(schedule));
////        RequestBuilder requestBuilder = MockMvcRequestBuilders
////                .get("/api/shop/schedules")
////                .accept(MediaType.APPLICATION_JSON);
////
////        MvcResult result = mockMvc.perform(requestBuilder).andDo(print()).andReturn();
////
////        System.out.println(result.getResponse());
//        mockMvc.perform(get("/api/schedule"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$").isArray());
//
//    }
//
//    @Test
//    public void testAddSchedule() throws Exception {
//        when(scheduleService.setSchedule(schedule)).thenReturn(schedule);
//        mockMvc.perform(
//                        post("/api/schedule")
//                                .content(objectMapper.writeValueAsString(schedule))
//                                .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.product_name", is("test1")))
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$").isNotEmpty());
//    }
//
//    @Test
//    public void testDeleteSchedule() throws Exception {
//        Schedule schedule = new Schedule();
//        schedule.setId(1L);
//
//        when(scheduleService.deleteSchedule(schedule.getId())).thenReturn(true);
//        mockMvc.perform(delete("/api/schedule/" + schedule.getId()))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//}
