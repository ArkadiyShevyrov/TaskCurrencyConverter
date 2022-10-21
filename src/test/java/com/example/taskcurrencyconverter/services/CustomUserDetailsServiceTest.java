package com.example.taskcurrencyconverter.services;

import com.example.taskcurrencyconverter.models.User;
import com.example.taskcurrencyconverter.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class CustomUserDetailsServiceTest {
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @MockBean
    UserRepository userRepository;



    @Test
    @DisplayName("Test findByUsername")
    void testFindByUserName() {
        String username = "test_name";
        User user = new User();
        user.setUsername(username);
        user.setPassword("123");

        doReturn(user).when(userRepository).findByUsername(username);

        UserDetails returnUser = customUserDetailsService.loadUserByUsername(username);

        Assertions.assertEquals(user.getUsername(), returnUser.getUsername());
    }

    @Test
    @DisplayName("Test findByUsernameNotFound")
    void testFindByUserNameNotFound() {
        String username = "test_name";

        doReturn(null).when(userRepository).findByUsername(username);

        UserDetails returnUser = customUserDetailsService.loadUserByUsername(username);

        Assertions.assertNull(returnUser);
    }

}

