package com.example.taskcurrencyconverter.services;

import com.example.taskcurrencyconverter.models.Conversion;
import com.example.taskcurrencyconverter.models.Currency;
import com.example.taskcurrencyconverter.models.User;
import com.example.taskcurrencyconverter.models.ValueOfDate;
import com.example.taskcurrencyconverter.repositories.ConversionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConversionService {
    private final CurrencyService currencyService;
    private final ConversionRepository conversionRepository;

    public Conversion saveConversion(User user, Long currencyIdFrom, Long currencyIdTo, Double valueFrom) {
        Conversion conversion = new Conversion();
        ValueOfDate valueOfDateFrom = currencyService.getValueOfDataByCurrencyID(currencyIdFrom);
        ValueOfDate valueOfDateTo = currencyService.getValueOfDataByCurrencyID(currencyIdTo);

        if (valueOfDateFrom != null && valueOfDateTo != null) {
            conversion.setValueOfDateFrom(valueOfDateFrom);
            conversion.setValueOfDateTo(valueOfDateTo);
            conversion.setDate(valueOfDateFrom.getDate());
            conversion.setUser(user);
            conversion.setValueFrom(valueFrom);
            conversion.setValueTo(valueFrom * valueOfDateFrom.getValue() / valueOfDateTo.getValue());
        }

        conversionRepository.save(conversion);

        log.info("Saving new conversion. Author's username: {}", conversion.getUser().getUsername());

        return conversion;
    }

    public List<Conversion> getByUser(User user) {
        if (user == null) {
            return null;
        }
        return conversionRepository.findByUser(user);
    }

    public List<Conversion> getByUserAndDateAndCurrencyFromAndCurrencyTo(
            User user, LocalDate date, Currency currencyFrom, Currency currencyTo) {
        if (user == null) {
            return null;
        }
        List<Conversion> conversions;
        if (date != null) {
           conversions = conversionRepository.findByUserAndDate(user, date);
        } else {
            conversions = getByUser(user);
        }

        if (currencyFrom != null) {
            conversions = conversions.stream().filter(conversion ->
                    conversion.getValueOfDateFrom().getCurrency().getId()
                            .equals(currencyFrom.getId())).toList();
        }
        if (currencyTo != null) {
            conversions = conversions.stream().filter(conversion ->
                    conversion.getValueOfDateTo().getCurrency().getId()
                            .equals(currencyTo.getId())).toList();
        }
        return conversions;
    }

}
