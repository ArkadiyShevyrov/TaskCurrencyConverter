package com.example.taskcurrencyconverter.controllers;


import com.example.taskcurrencyconverter.models.Conversion;
import com.example.taskcurrencyconverter.models.Currency;
import com.example.taskcurrencyconverter.models.User;
import com.example.taskcurrencyconverter.services.ConversionService;
import com.example.taskcurrencyconverter.services.CurrencyService;
import com.example.taskcurrencyconverter.services.UserService;
import com.example.taskcurrencyconverter.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CurrencyController {
    private final CurrencyService currencyService;
    private final ConversionService conversionService;
    private final UserService userService;

    @GetMapping("/")
    public String currencies() {
        return "currencies";
    }

    @GetMapping("/convertor")
    public String convention(Model model) {
        model.addAttribute("currencies", currencyService.listCurrency());
        return "convertor";
    }

    @PostMapping("/convertor")
    public String createConversion(Long idFrom, Long idTo, Double valueFrom, Principal principal, Model model) {
        User user;
        if (principal != null) {
            user = userService.getUserByPrincipal(principal);
            if (user == null) {
                return "error";
            }
        } else {
            return "error";
        }
        Conversion conversion = conversionService.saveConversion(user, idFrom, idTo, valueFrom);
        if (conversion == null) {
            model.addAttribute("message", "При конвертации произошла ошибка");
        } else {
            model.addAttribute("message", "Успешно конвертированно");
            model.addAttribute("conversion", conversion);
        }
        model.addAttribute("currencies", currencyService.listCurrency());
        return "convertor";
    }

    @GetMapping("/convertor/history")
    public String table(Principal principal, Model model) {
        User user;
        if (principal != null) {
            user = userService.getUserByPrincipal(principal);
            if (user == null) {
                return "error";
            }
        } else {
            return "error";
        }

        model.addAttribute("convertors", conversionService.getByUser(user));
        model.addAttribute("currencyList", currencyService.listCurrency());
        return "convertor-history";
    }

    @PostMapping("/convertor/history")
    public String tableFilter(Principal principal, String date, Long idFrom, Long idTo, Model model) {
        User user;
        LocalDate localDate = null;
        Currency currencyFrom = null;
        Currency currencyTo = null;

        if (principal != null) {
            user = userService.getUserByPrincipal(principal);
            if (user == null) {
                return "error";
            }
        } else {
            return "error";
        }

        if (date != null && !date.equals("")) {
            localDate = Utils.stringToLocalDate(date);
        }

        if (idFrom != null) {
            currencyFrom = currencyService.getCurrencyById(idFrom);
        }
        if (idTo != null) {
            currencyTo = currencyService.getCurrencyById(idTo);
        }

        model.addAttribute("convertors",
                conversionService.getByUserAndDateAndCurrencyFromAndCurrencyTo(
                        user, localDate, currencyFrom, currencyTo));
        model.addAttribute("currencyList", currencyService.listCurrency());
        return "convertor-history";
    }
}
