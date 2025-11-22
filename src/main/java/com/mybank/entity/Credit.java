package com.mybank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
//import java.time.Date;
//import java.time.LocalDate;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "credits")

public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Date date;

    @NotNull
    private BigDecimal amount;
    @NotNull
    private Double interestRate;
    @NotNull
    private Integer termMonths;
    @NotNull
    private Date dueDate;
    private String approvedBy;

    //seters
    @OneToMany(mappedBy = "credit")
    private List<UserCredit> usersCredits;

    //setteri
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public void setTermMonths(Integer termMonths) {
        this.termMonths = termMonths;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    //getters
    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

//    public String getUser() {
//        return user;
//    }

    public BigDecimal getAmount() {
        return amount; //
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public Integer getTermMonths() {
        return termMonths;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }
}
