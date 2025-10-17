package com.mybank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
//import java.time.Date;
//import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "credits")

public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Date date;

    @OneToMany(mappedBy = "credit")
    private List<UserCredit> usersCredits;

    //Getteri, setteri, constructori
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public void setDate(Date date) {
        this.date = date;
    }

}
