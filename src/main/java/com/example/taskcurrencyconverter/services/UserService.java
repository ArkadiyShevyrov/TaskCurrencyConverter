package com.example.taskcurrencyconverter.services;

import com.example.taskcurrencyconverter.models.User;
import com.example.taskcurrencyconverter.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        if (user == null) {
            return false;
        }
        String username = user.getUsername();
        if (userRepository.findByUsername(username) != null) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Saving new User with email: {}", username);
        userRepository.save(user);
        return true;
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByUsername(principal.getName());
    }

}
