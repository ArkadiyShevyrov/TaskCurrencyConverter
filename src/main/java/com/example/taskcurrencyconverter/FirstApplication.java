package com.example.taskcurrencyconverter;

import com.example.taskcurrencyconverter.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FirstApplication {

    private final CurrencyService currencyService;

    @EventListener(ApplicationReadyEvent.class)
    public void downloadCurrencyToDB() {
        currencyService.updateCurrency();
        currencyService.updateValueOfDate();
    }
}
