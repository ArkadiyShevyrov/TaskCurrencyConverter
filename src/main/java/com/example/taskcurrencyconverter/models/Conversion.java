package com.example.taskcurrencyconverter.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "conversion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conversion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private User user;

    private LocalDate date;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private ValueOfDate valueOfDateFrom;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private ValueOfDate valueOfDateTo;

    private Double valueFrom;

    private Double valueTo;


}
