package com.example.taskcurrencyconverter.controllers;

import com.example.taskcurrencyconverter.models.Conversion;
import com.example.taskcurrencyconverter.models.User;
import com.example.taskcurrencyconverter.services.ConversionService;
import com.example.taskcurrencyconverter.services.CurrencyService;
import com.example.taskcurrencyconverter.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.Collection;
import java.util.Map;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class CurrencyControllerTest {
    @Autowired
    CurrencyController currencyController;

    @MockBean
    private CurrencyService currencyService;
    @MockBean
    private ConversionService conversionService;
    @MockBean
    private UserService userService;

    @Test
    @DisplayName("Test createConversion")
    void testCreateConversion() {
        Conversion conversion = new Conversion();
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
        Principal principal = () -> "null";
        User user = new User();

        doReturn(user).when(userService).getUserByPrincipal(principal);
        doReturn(conversion).when(conversionService).saveConversion(user, 1L, 2L, 3.);

        String returnValue =  currencyController.createConversion(1L, 2L, 3., principal, model);

        Assertions.assertEquals("convertor", returnValue);
    }

    @Test
    @DisplayName("Test createConversionError")
    void testCreateConversionError() {
        Conversion conversion = new Conversion();
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
        Principal principal = () -> "null";
        User user = new User();

        doReturn(null).when(userService).getUserByPrincipal(principal);
        doReturn(conversion).when(conversionService).saveConversion(user, 1L, 2L, 3.);

        String returnValue =  currencyController.createConversion(1L, 2L, 3., principal, model);

        Assertions.assertEquals("error", returnValue);
    }

    @Test
    @DisplayName("Test table")
    void testTable() {
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
        Principal principal = () -> "null";
        User user = new User();

        doReturn(user).when(userService).getUserByPrincipal(principal);

        String returnValue =  currencyController.table(principal, model);

        Assertions.assertEquals("convertor-history", returnValue);
    }

    @Test
    @DisplayName("Test tableError")
    void testTableError() {
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
        Principal principal = () -> "null";
        User user = new User();

        doReturn(null).when(userService).getUserByPrincipal(principal);

        String returnValue =  currencyController.table(principal, model);

        Assertions.assertEquals("error", returnValue);
    }

    @Test
    @DisplayName("Test tableFilter")
    void testTableFilter() {
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
        Principal principal = () -> "null";
        User user = new User();

        doReturn(user).when(userService).getUserByPrincipal(principal);

        String returnValue =  currencyController.tableFilter(principal, "2022-10.21", null, 2L,  model);

        Assertions.assertEquals("convertor-history", returnValue);
    }

    @Test
    @DisplayName("Test tableFilterError")
    void testTableFilterError() {
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
        Principal principal = () -> "null";

        doReturn(null).when(userService).getUserByPrincipal(principal);

        String returnValue =  currencyController.tableFilter(principal, "2022-10.21", 1L, 2L,  model);

        Assertions.assertEquals("error", returnValue);
    }

}
