package com.mybank.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private Date startDate;
    private Date endDate;
    private Double rate; // rata

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //getere si setere
}
