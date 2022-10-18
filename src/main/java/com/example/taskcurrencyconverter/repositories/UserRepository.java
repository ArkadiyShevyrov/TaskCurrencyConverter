package com.example.taskcurrencyconverter.repositories;

import com.example.taskcurrencyconverter.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}