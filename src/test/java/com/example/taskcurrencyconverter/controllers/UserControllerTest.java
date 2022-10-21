//package com.example.taskcurrencyconverter.controllers;
//
//import com.example.taskcurrencyconverter.models.User;
//import com.example.taskcurrencyconverter.repositories.UserRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@TestPropertySource(
//        locations = "classpath:application-integrationtest.properties")
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    void registrationWorksThroughAllLayers() throws Exception {
//        String username = "test_name";
//        User userExpected = new User();
//        userExpected.setUsername(username);
//
//        mockMvc.perform(post("/registration")
//                        .contentType("application/json")
//                        .param("sendWelcomeMail", "true")
//                        .content(objectMapper.writeValueAsString(userExpected)))
//                .andExpect(status().isOk());
//
//        User userActual = userRepository.findByUsername(username);
//        Assertions.assertEquals(userExpected.getUsername(), userActual.getUsername());
//    }
//}
