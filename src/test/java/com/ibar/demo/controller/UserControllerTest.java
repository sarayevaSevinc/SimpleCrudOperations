package com.ibar.demo.controller;

//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ibar.demo.controllers.UserController;
//import com.ibar.demo.model.User;
//import com.ibar.demo.services.impl.UserServiceImpl;
//import java.util.UUID;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

//
//@WebMvcTest(UserController.class)
//
//public class UserControllerTest {
//
//
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    private UserServiceImpl userService;
//
//    @Mock
//    MongoTemplate mongoTemplate;

//    @Test
//    public void getUserTest() throws Exception {
//        // set up
//        User user = User.build()
//                .name("test3")
//                .surname("test4")
//                .cardNumber("cardnumber")
//                .pin("45845h")
//                .phone("phone")
//                .build();
//
//        when(userService.getUserById(5)).thenReturn(user);
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/1").accept(MediaType.APPLICATION_JSON);
//        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk())
//                .andExpect(content().string("{\"id\":1,\"name\":\"test3\",\"surname\""
//                        + ":\"test4\",\"age\":20,\"birthday\":"
//                        + "null,\"pin\":\"45845h\",\"cardNumber\":\"cardnumber\",\"gender\":null,\"phone\""
//                        + ":\"phone\",\"status\":"
//                        + "null,\"createdTime\":null,\"updatedTime\":null}"))
//                .andReturn();
//
//    }

//    @Test
//    public void addUserTest() throws Exception {
//        // set up
//        UUID uuid = UUID.randomUUID();
//        User user = User.build()
//                .name("test3")
//                .surname("test4")
//                .cardNumber("cardnumber")
//                .pin("45845h")
//                .phone("phone")
//                .build();
//
//        when(userService.create(any(User.class)))
//                .thenReturn(user);
//
//        // create request
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/add")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(user))
//                .accept(MediaType.APPLICATION_JSON);
//
//        //test
//        MvcResult result = mockMvc.perform(requestBuilder)
//                .andExpect(status().isOk())
//                .andExpect(content().string("{\"id\":5,\"name\":\"test3\",\"surname\":\"test4\",\"age\""
//                        + ":20,\"birthday\":null,\"pin\":\"pin number\",\"cardNumber\":\"cardnumber\",\"gender\":"
//                        + "null,\"phone\":\"phone\",\"status\":null,\"createdTime\":null,\"updatedTime\":null}"))
//                .andReturn();
//
//    }

//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Test
//    public void updateUserTest() throws Exception {
//        // set up
//        User user = User.build()
//                .name("test3")
//                .surname("test4")
//                .cardNumber("cardnumber")
//                .pin("45845h")
//                .phone("phone")
//                .build();
//
//        when(userService.updateUser(any(User.class)))
//                .thenReturn(user);
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/update")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(user))
//                .accept(MediaType.APPLICATION_JSON);
//        MvcResult result = mockMvc.perform(requestBuilder)
//                .andExpect(status().isOk())
//                .andExpect(content().string("{\"id\":5,\"name\":\"test3\",\"surname\":\"test4\",\"age\":"
//                        + "20,\"birthday\":null,\"pin\":\"pin number\",\"cardNumber\""
//                        + ":\"cardnumber\",\"gender\":null,\"phone\":\"phone\",\"status\""
//                        + ":null,\"createdTime\":null,\"updatedTime\":null}"))
//                .andReturn();
//
//    }
//}

