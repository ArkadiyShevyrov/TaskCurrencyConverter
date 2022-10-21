package com.example.taskcurrencyconverter.controllers;

import com.example.taskcurrencyconverter.models.User;
import com.example.taskcurrencyconverter.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.Map;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    UserController userController;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("Test createUser")
    void testCreateUser() {
        User user = new User();
        Model model = new Model() {
            @Override
            public Model addAttribute(String attributeName, Object attributeValue) {
                return null;
            }

            @Override
            public Model addAttribute(Object attributeValue) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> attributeValues) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public boolean containsAttribute(String attributeName) {
                return false;
            }

            @Override
            public Object getAttribute(String attributeName) {
                return null;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        };

        doReturn(true).when(userService).createUser(user);

        String returnValue = userController.createUser(user, model);

        Assertions.assertEquals("redirect:/login", returnValue);
    }

    @Test
    @DisplayName("Test createUserError")
    void testCreateUserError() {
        User user = new User();
        Model model = new Model() {
            @Override
            public Model addAttribute(String attributeName, Object attributeValue) {
                return null;
            }

            @Override
            public Model addAttribute(Object attributeValue) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> attributeValues) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public boolean containsAttribute(String attributeName) {
                return false;
            }

            @Override
            public Object getAttribute(String attributeName) {
                return null;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        };

        doReturn(false).when(userService).createUser(user);

        String returnValue = userController.createUser(user, model);

        Assertions.assertEquals("registration", returnValue);
    }
}
