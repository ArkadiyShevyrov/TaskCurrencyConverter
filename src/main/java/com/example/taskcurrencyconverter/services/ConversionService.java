package com.example.taskcurrencyconverter.services;

import com.example.taskcurrencyconverter.models.Conversion;
import com.example.taskcurrencyconverter.models.User;
import com.example.taskcurrencyconverter.models.ValueOfDate;
import com.example.taskcurrencyconverter.repositories.ConversionRepository;
import com.example.taskcurrencyconverter.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConversionService {

    private final UserRepository userRepository;
    private final CurrencyService currencyService;
    private final ConversionRepository conversionRepository;

    public Conversion saveConversion(Principal principal, Long currencyIdFrom, Long currencyIdTo, Double valueFrom) {
        Conversion conversion = new Conversion();
        ValueOfDate valueOfDateFrom = currencyService.getValueOfDataByCurrencyID(currencyIdFrom);
        ValueOfDate valueOfDateTo = currencyService.getValueOfDataByCurrencyID(currencyIdTo);

        if (valueOfDateFrom != null && valueOfDateTo != null) {
            conversion.setValueOfDateFrom(valueOfDateFrom);
            conversion.setValueOfDateTo(valueOfDateTo);
            conversion.setDate(valueOfDateFrom.getDate());
            conversion.setUser(getUserByPrincipal(principal));
            conversion.setValueFrom(valueFrom);
            conversion.setValueTo(valueFrom * valueOfDateFrom.getValue() / valueOfDateTo.getValue());
        }
        log.info("Saving new conversion. Author's username: {}",
                conversion.getUser().getUsername());
        conversionRepository.save(conversion);
        return conversion;
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByUsername(principal.getName());
    }

    public List<Conversion> getConversionListByUser(Principal principal) {
        return conversionRepository.findByUser(getUserByPrincipal(principal));
    }

}
