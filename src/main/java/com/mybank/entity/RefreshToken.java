package com.mybank.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;



//Această entitate va stoca tokenul, data expirării și utilizatorul asociat.
@Entity
@Data
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token; //added token

    private Instant expiryDate;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
