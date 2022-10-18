package com.example.taskcurrencyconverter.repositories;

import com.example.taskcurrencyconverter.models.Currency;
import com.example.taskcurrencyconverter.models.ValueOfDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ValueOfDateRepository extends JpaRepository<ValueOfDate, Long> {
    ValueOfDate findByCurrencyAndDate(Currency currency, LocalDate localDate);
    List<ValueOfDate> findByCurrency(Currency currency);

}
