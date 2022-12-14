package com.example.taskcurrencyconverter.controllers;

import com.example.taskcurrencyconverter.models.User;
import com.example.taskcurrencyconverter.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (model == null) {
            return "error";
        }
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage",
                    "Пользователь с именем " + user.getUsername() + " уже существует!");
            return "registration";
        } else {
            return "redirect:/login";
        }
    }

}
