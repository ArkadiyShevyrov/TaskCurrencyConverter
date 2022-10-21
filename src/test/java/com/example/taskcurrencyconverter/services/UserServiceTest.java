package com.example.taskcurrencyconverter.services;

import com.example.taskcurrencyconverter.models.User;
import com.example.taskcurrencyconverter.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.security.Principal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;
    @MockBean
    UserRepository userRepository;

    @Test
    @DisplayName("Test createUserIsTrue")
    void testCreateUserIsTrue() {
        String username = "test_name";
        User user = new User();
        user.setUsername(username);
        user.setPassword("123");

        doReturn(user).when(userRepository).save(any());

        boolean returnBoolean = userService.createUser(user);

        Assertions.assertTrue(returnBoolean);
    }

    @Test
    @DisplayName("Test createUserIsFalse")
    void testCreateUserIsFalse() {
        User user = null;

        doReturn(user).when(userRepository).save(any());

        boolean returnBoolean = userService.createUser(user);

        Assertions.assertFalse(returnBoolean);
    }

    @Test
    @DisplayName("Test createUserIsFalseDouble")
    void testCreateUserIsFalseDouble() {
        String username = "test_name";
        User user = new User();
        user.setUsername(username);
        user.setPassword("123");

        doReturn(user).when(userRepository).save(any());
        doReturn(user).when(userRepository).findByUsername(username);

        boolean returnBoolean = userService.createUser(user);

        Assertions.assertFalse(returnBoolean);
    }

    @Test
    @DisplayName("Test getUserByPrincipalIsTrue")
    void testGetUserByPrincipalIsTrue() {
        String username = "test_name";
        User user = new User();
        user.setUsername(username);
        user.setPassword("123");

        Principal principal = () -> username;

        doReturn(user).when(userRepository).findByUsername(username);

        User returnUser = userService.getUserByPrincipal(principal);

        Assertions.assertEquals(user, returnUser);
    }

    @Test
    @DisplayName("Test getUserByPrincipalIsFalse")
    void testGetUserByPrincipalIsFalse() {
        User user = new User();

        User returnUser = userService.getUserByPrincipal(null);

        Assertions.assertEquals(user, returnUser);
    }
}
