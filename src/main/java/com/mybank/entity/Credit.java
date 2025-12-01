package com.mybank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
//import java.time.Date;
//import java.time.LocalDate;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private boolean active;

    @NotNull
    private BigDecimal amount;
    @NotNull
    private Double interestRate;
    @NotNull
    private Integer termMonths;
    @NotNull
    private Date dueDate;
    private String approvedBy;

    @Column(name = "monthly_income", precision = 15, scale = 2)
    private BigDecimal monthlyIncome;

    @Column(name = "debt_ratio", precision = 5, scale = 3)
    private BigDecimal debtRatio;

    //seters
    //@OneToMany(mappedBy = "credit")
    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserCredit> usersCredits;


    //setteri
    public void setActive(boolean active) {
        this.active = active;
    }

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

    public void setMonthlyIncome(BigDecimal monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
    public void setDebtRatio(BigDecimal debtRatio) {
        this.debtRatio = debtRatio;
    }


    //getters
    public boolean getActive() {return active;}
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

    public BigDecimal getMonthlyIncome() {
        return monthlyIncome;
    }

    public BigDecimal getDebtRatio() {
        return debtRatio;
    }

    public void setUser(User user) {
        var userCredit=new UserCredit();
        userCredit.setUser(user);
        userCredit.setCredit(this);

        if (usersCredits == null){
            usersCredits=new ArrayList<>();
        }
        usersCredits.add(userCredit);
    }
}
