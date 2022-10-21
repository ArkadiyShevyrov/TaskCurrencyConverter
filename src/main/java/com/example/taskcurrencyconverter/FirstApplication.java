package com.example.taskcurrencyconverter;

import com.example.taskcurrencyconverter.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FirstApplication {

    private final CurrencyService currencyService;

    @Value("${com.example.taskcurrencyconverter.application}")
    private String application;

    @EventListener(ApplicationReadyEvent.class)
    public void downloadCurrencyToDB() {
        if (application != null && application.equals("true")) {
            currencyService.updateCurrency();
            currencyService.updateValueOfDate();
        }
    }
}
