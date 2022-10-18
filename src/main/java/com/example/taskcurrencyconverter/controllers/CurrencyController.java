package com.example.taskcurrencyconverter.controllers;


import com.example.taskcurrencyconverter.models.Conversion;
import com.example.taskcurrencyconverter.services.ConversionService;
import com.example.taskcurrencyconverter.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CurrencyController {
    private final CurrencyService currencyService;
    private final ConversionService conversionService;

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
        Conversion conversion = conversionService.saveConversion(principal, idFrom, idTo, valueFrom);
        model.addAttribute("currencies", currencyService.listCurrency());
        model.addAttribute("message", "Успешно конвертированно");
        model.addAttribute("convertion", conversion);
        return "convertor";
    }

    @GetMapping("/convertor/history")
    public String admin(Principal principal, Model model) {
        model.addAttribute("convertors", conversionService.getConversionListByUser(principal));
        return "convertor-history";
    }
}
