package com.ibar.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibar.demo.controllers.UserController;
import com.ibar.demo.model.Status;
import com.ibar.demo.model.User;
import com.ibar.demo.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;


@WebMvcTest(UserController.class)
public class UserControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserService userService;

//    @Test
//    public void getUserTest() throws Exception {
//        when(userService.getUserbyId(1)).thenReturn(new User(1,"test87", "trst3rf", 0, null, "45784rh4ut", "95748574", null,"49595475p", Status.CREATED,null,null));
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/1").accept(MediaType.APPLICATION_JSON);
//        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk())
//                .andExpect(content().string("{\"id\":1,\"name\":\"test87\",\"surname\":\"trst3rf\",\"age\":0,\"birthday\":null,\"pin\":\"45784rh4ut\",\"cardNumber\":\"95748574\",\"gender\":null,\"phone\":\"49595475p\",\"status\":\"CREATED\",\"createdTime\":null,\"updatedTime\":null}"))
//                .andReturn();
//
//    }

    @Test
    public void addUserTest() throws Exception {
        User user = new User(UUID.randomUUID(),"test2", "test3", 20, null, "3445", "4646", null, "050",null,null, null);
        when(userService.create(any(User.class)))
                .thenReturn(user);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user))
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":13,\"name\":\"test2\",\"surname\":\"test3\",\"age\":20,\"birthday\":null,\"pin\":\"3445\",\"cardNumber\":\"4646\",\"gender\":null,\"phone\":\"050\",\"status\":null,\"createdTime\":null,\"updatedTime\":null}"))
                .andReturn();

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
