package com.example.taskcurrencyconverter.services;

import com.example.taskcurrencyconverter.models.Currency;
import com.example.taskcurrencyconverter.models.ValueOfDate;
import com.example.taskcurrencyconverter.repositories.CurrencyRepository;
import com.example.taskcurrencyconverter.repositories.ValueOfDateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class CurrencyServiceTest {
    @Autowired
    CurrencyService currencyService;

    @MockBean
    private CurrencyRepository currencyRepository;
    @MockBean
    private ValueOfDateRepository valueOfDateRepository;

    @Test
    @DisplayName("Test listCurrency")
    void testListCurrency() {
        List<Currency> currencyList = new ArrayList<>();
        currencyList.add(new Currency(1L, "", "", "", 1, "", null));
        currencyList.add(new Currency(2L, "", "", "", 2, "", null));
        currencyList.add(new Currency(3L, "", "", "", 3, "", null));

        doReturn(currencyList).when(currencyRepository).findAll();

        List<Currency> returnList = currencyService.listCurrency();

        Assertions.assertEquals(currencyList, returnList);
    }

    @Test
    @DisplayName("Test getCurrentValueByCurrency")
    void testGetCurrentValueByCurrency() {
        Currency currency = new Currency(1L, "", "", "", 1, "",
                null);
        LocalDate date = LocalDate.now();
        ValueOfDate valueOfDate = new ValueOfDate(1L, currency, 2.2, date);

        doReturn(valueOfDate).when(valueOfDateRepository).findByCurrencyAndDate(currency, date);

        ValueOfDate returnValue = currencyService.getCurrentValueByCurrency(currency);

        Assertions.assertEquals(valueOfDate, returnValue);

    }

    @Test
    @DisplayName("Test getCurrentValueByCurrencyNull")
    void testGetCurrentValueByCurrencyNull() {
        ValueOfDate returnValue = currencyService.getCurrentValueByCurrency(null);

        Assertions.assertNull(returnValue);

    }

    @Test
    @DisplayName("Test getValueOfDataByCurrencyID")
    void testGetValueOfDataByCurrencyID() {
        Currency currency = new Currency(1L, "", "", "", 1, "",
                null);
        LocalDate date = LocalDate.now();
        ValueOfDate valueOfDate = new ValueOfDate(1L, currency, 2.2, date);

        doReturn(valueOfDate).when(valueOfDateRepository).findByCurrencyAndDate(currency, date);
        doReturn(Optional.of(currency)).when(currencyRepository).findById(1L);

        ValueOfDate returnValue = currencyService.getValueOfDataByCurrencyID(1L);

        Assertions.assertEquals(valueOfDate, returnValue);
    }

    @Test
    @DisplayName("Test getValueOfDataByCurrencyIDNull")
    void testGetValueOfDataByCurrencyIDNull() {
        ValueOfDate returnValue = currencyService.getValueOfDataByCurrencyID(-1L);

        Assertions.assertNull(returnValue);

    }

    @Test
    @DisplayName("Test getCurrencyById")
    void testGetCurrencyById() {
        Currency currency = new Currency(1L, "", "", "", 1, "",
                null);

        doReturn(Optional.of(currency)).when(currencyRepository).findById(1L);

        Currency returnValue = currencyService.getCurrencyById(1L);

        Assertions.assertEquals(currency, returnValue);
    }

    @Test
    @DisplayName("Test getCurrencyByIdNull")
    void testGetCurrencyByIdNull() {
        doReturn(Optional.empty()).when(currencyRepository).findById(1L);

        Currency returnValue = currencyService.getCurrencyById(1L);

        Assertions.assertNull(returnValue);
    }
}
