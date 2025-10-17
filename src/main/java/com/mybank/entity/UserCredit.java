package com.mybank.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_credit")
public class UserCredit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_id")
    private Credit credit;

    private LocalDate assignedDate;
    private String approvedBy;

    //getteri si seteri
}
