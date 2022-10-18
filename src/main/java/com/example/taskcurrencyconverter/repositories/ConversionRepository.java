package com.example.taskcurrencyconverter.repositories;

import com.example.taskcurrencyconverter.models.Conversion;
import com.example.taskcurrencyconverter.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ConversionRepository extends JpaRepository<Conversion, Long> {
    List<Conversion> findByUser(User user);
}