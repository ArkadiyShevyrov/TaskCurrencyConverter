package com.example.taskcurrencyconverter.services;

import com.example.taskcurrencyconverter.models.Currency;
import com.example.taskcurrencyconverter.models.ValueOfDate;
import com.example.taskcurrencyconverter.models.xml.CourseDto;
import com.example.taskcurrencyconverter.repositories.CurrencyRepository;
import com.example.taskcurrencyconverter.repositories.ValueOfDateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final ValueOfDateRepository valueOfDateRepository;
    @Value("${com.example.taskcurrencyconverter.url.cbr-currency}")
    private String URL;
    private final RestTemplate restTemplate = new RestTemplate();


    public List<Currency> listCurrency() {
        return currencyRepository.findAll();
    }

    public ValueOfDate getCurrentValueByCurrency(Currency currency) {
        if (currency == null) {
            return null;
        }
        LocalDate date = LocalDate.now();
        if (!isValueByCurrencyAndDate(currency, date)) {
            updateValueOfDate();
        }
        return getValueByCurrencyAndDate(currency, date);
    }

    public void updateCurrency() {
        List<Currency> currencyList = getCurrency();
        if (currencyList.isEmpty()) {
            log.info("currencyList is empty");
        }
        for (Currency currency : currencyList) {
            if (currencyRepository.findByValuteID(currency.getValuteID()).isEmpty()) {
                currencyRepository.save(currency);
                log.info("save in currencyRepository: {}", currency);
            }
        }
    }

    public void updateValueOfDate() {
        List<ValueOfDate> valueOfDateList = getValueOfDate();
        if (valueOfDateList.isEmpty()) {
            log.info("valueOfDateList is empty");
        }
        for (ValueOfDate valueOfDate : valueOfDateList) {
            if (valueOfDateRepository.findByCurrencyAndDate(
                            valueOfDate.getCurrency(), valueOfDate.getDate())
                    == null) {
                valueOfDateRepository.save(valueOfDate);
                log.info("save in valueOfDateRepository: {}", valueOfDate);
            }
        }
    }

    private List<Currency> getCurrency() {
        CourseDto response = restTemplate.getForObject(URL, CourseDto.class);
        List<Currency> currencyList = new ArrayList<>();
        if (response != null) {
            response
                    .getValute()
                    .forEach(x -> {
                        Currency currency = new Currency();

                        currency.setValuteID(x.getValuteID());
                        currency.setNumCode(x.getNumCode());
                        currency.setCharCode(x.getCharCode());
                        currency.setNominal(x.getNominal());
                        currency.setName(x.getName());

                        currencyList.add(currency);
                    });
        }
        return currencyList;
    }

    private List<ValueOfDate> getValueOfDate() {
        LocalDate date = LocalDate.now();
        CourseDto response = restTemplate.getForObject(URL, CourseDto.class);
        List<ValueOfDate> valueOfDateList = new ArrayList<>();
        if (response != null) {
            response
                    .getValute()
                    .forEach(x -> {
                        ValueOfDate valueOfDate = new ValueOfDate();
                        Currency currency = currencyRepository.findByValuteID(x.getValuteID()).get(0);

                        valueOfDate.setValue(Double.parseDouble(x.getValue().replace(",", ".")));
                        valueOfDate.setDate(date);
                        valueOfDate.setCurrency(currency);

                        valueOfDateList.add(valueOfDate);
                    });
        }
        return valueOfDateList;
    }

    private boolean isValueByCurrencyAndDate(Currency currency, LocalDate date) {
        if (currency == null || date == null) {
            return false;
        }
        return valueOfDateRepository.findByCurrencyAndDate(currency, date) != null;
    }

    private ValueOfDate getValueByCurrencyAndDate(Currency currency, LocalDate date) {
        if (currency == null || date == null) {
            return null;
        }
        return valueOfDateRepository.findByCurrencyAndDate(currency, date);
    }

    public ValueOfDate getValueOfDataByCurrencyID(Long id) {
        if (id == null || id < 0) {
            return null;
        }
        Currency currency = currencyRepository.findById(id).orElse(null);
        return getCurrentValueByCurrency(currency);
    }

    public Currency getCurrencyById(Long id) {
        if (id == null || id < 0) {
            return null;
        }
        return currencyRepository.findById(id).orElse(null);
    }

}
