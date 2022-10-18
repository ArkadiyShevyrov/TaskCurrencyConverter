package com.example.taskcurrencyconverter.repositories;

import com.example.taskcurrencyconverter.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    List<Currency> findByValuteID(String valuteID);
}
