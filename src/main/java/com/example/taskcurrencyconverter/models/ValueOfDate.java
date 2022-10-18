package com.example.taskcurrencyconverter.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "valueofdate")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValueOfDate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Currency currency;

    private Double value;

    private LocalDate date;
}
