package com.mybank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "credits")

public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private LocalDate  date;

    @ManyToMany
    @JoinColumn(name = "user_id")
    private User user;

    //Getteri, setteri, constructori
}
