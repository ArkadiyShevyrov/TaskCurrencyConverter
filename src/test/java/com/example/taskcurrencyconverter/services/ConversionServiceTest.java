package com.example.taskcurrencyconverter.services;

import com.example.taskcurrencyconverter.models.Conversion;
import com.example.taskcurrencyconverter.models.Currency;
import com.example.taskcurrencyconverter.models.User;
import com.example.taskcurrencyconverter.models.ValueOfDate;
import com.example.taskcurrencyconverter.repositories.ConversionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class ConversionServiceTest {

    @Autowired
    ConversionService conversionService;

    @MockBean
    private CurrencyService currencyService;
    @MockBean
    private ConversionRepository conversionRepository;


    @Test
    @DisplayName("Test saveConversion")
    void testSaveConversion() {
        Currency currency = new Currency(1L, "", "", "", 1, "",
                null);
        LocalDate date = LocalDate.now();
        ValueOfDate valueOfDate = new ValueOfDate(1L, currency, 2.2, date);


        User user = new User();
        Conversion conversion = new Conversion();
        Double valueFrom = 2.2;
        conversion.setValueOfDateFrom(valueOfDate);
        conversion.setValueOfDateTo(valueOfDate);
        conversion.setDate(valueOfDate.getDate());
        conversion.setUser(user);
        conversion.setValueFrom(valueFrom);
        conversion.setValueTo(valueFrom * valueOfDate.getValue() / valueOfDate.getValue());

        doReturn(null).when(conversionRepository).save(any());
        doReturn(valueOfDate).when(currencyService).getValueOfDataByCurrencyID(any());

        Conversion returnValue = conversionService.saveConversion(user, currency.getId(), currency.getId(), valueFrom);

        Assertions.assertEquals(conversion, returnValue);
    }

    @Test
    @DisplayName("Test saveConversionNull")
    void testSaveConversionNull() {
        Currency currency = new Currency(1L, "", "", "", 1, "",
                null);
        LocalDate date = LocalDate.now();
        ValueOfDate valueOfDate = new ValueOfDate(1L, currency, 2.2, date);


        User user = new User();
        Conversion conversion = new Conversion();
        Double valueFrom = 2.2;
        conversion.setValueOfDateFrom(valueOfDate);
        conversion.setValueOfDateTo(valueOfDate);
        conversion.setDate(valueOfDate.getDate());
        conversion.setUser(user);
        conversion.setValueFrom(valueFrom);
        conversion.setValueTo(valueFrom * valueOfDate.getValue() / valueOfDate.getValue());

        doReturn(null).when(conversionRepository).save(any());
        doReturn(valueOfDate).when(currencyService).getValueOfDataByCurrencyID(any());

        Conversion returnValue = conversionService.saveConversion(null, currency.getId(), currency.getId(), valueFrom);

        Assertions.assertNull(returnValue);
    }

    @Test
    @DisplayName("Test getByUser")
    void testGetByUser() {
        User user = new User();
        List<Conversion> currencyList = new ArrayList<>();
        currencyList.add(new Conversion(1L, user, null, null, null, 1., 2.));
        currencyList.add(new Conversion(2L, user, null, null, null, 5., 3.));
        currencyList.add(new Conversion(3L, user, null, null, null, 6., 4.));

        doReturn(currencyList).when(conversionRepository).findByUser(any());

        List<Conversion> returnValue = conversionService.getByUser(user);

        Assertions.assertEquals(currencyList, returnValue);
    }

    @Test
    @DisplayName("Test getByUserNull")
    void testGetByUserNull() {
        List<Conversion> returnValue = conversionService.getByUser(null);

        Assertions.assertNull(returnValue);
    }

    @Test
    @DisplayName("Test getByUserAndDateAndCurrencyFromAndCurrencyTo")
    void testGetByUserAndDateAndCurrencyFromAndCurrencyTo() {
        User user = new User();
        List<Conversion> currencyList = new ArrayList<>();
        LocalDate date = LocalDate.now();
        currencyList.add(new Conversion(1L, user, null, null, null, 1., 2.));
        currencyList.add(new Conversion(2L, user, null, null, null, 5., 3.));
        currencyList.add(new Conversion(3L, user, null, null, null, 6., 4.));


        doReturn(currencyList).when(conversionRepository).findByUserAndDate(user, date);
        doReturn(null).when(conversionRepository).findByUser(any());

        List<Conversion> returnValue = conversionService.getByUserAndDateAndCurrencyFromAndCurrencyTo(user, date, null, null);

        Assertions.assertEquals(currencyList, returnValue);
    }

    @Test
    @DisplayName("Test getByUserAndDateAndCurrencyFromAndCurrencyToNull")
    void testGetByUserAndDateAndCurrencyFromAndCurrencyToNull() {
        User user = new User();
        List<Conversion> currencyList = new ArrayList<>();
        LocalDate date = LocalDate.now();
        currencyList.add(new Conversion(1L, user, null, null, null, 1., 2.));
        currencyList.add(new Conversion(2L, user, null, null, null, 5., 3.));
        currencyList.add(new Conversion(3L, user, null, null, null, 6., 4.));


        doReturn(currencyList).when(conversionRepository).findByUserAndDate(user, date);
        doReturn(null).when(conversionRepository).findByUser(any());

        List<Conversion> returnValue = conversionService.getByUserAndDateAndCurrencyFromAndCurrencyTo(null, date, null, null);

        Assertions.assertNull(returnValue);
    }

}
