package com.mybank.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreditResponse {
    private Long id;
    private LocalDate date;
    private String userName;

    private BigDecimal amount;
    private Double interestRate;
    private Integer termMonths;
    private LocalDate dueDate;
    private String approvedBy;

    private BigDecimal monthlyIncome;
    private BigDecimal debtRatio;

    // Getters
    public BigDecimal getMonthlyIncome() {return monthlyIncome;}
    public void setMonthlyIncome(BigDecimal monthlyIncome) {this.monthlyIncome = monthlyIncome;}
    public BigDecimal getDebtRatio() {return debtRatio;}

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getUserName() {
        return userName;
    }

    public BigDecimal getAmount() {
        return amount; //
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public Integer getTermMonths() {
        return termMonths;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    // Setters


    public void setDebtRatio(BigDecimal debtRatio) {
        this.debtRatio = debtRatio;
    }
    public void setMonthlyIncome(LocalDate date) {
        this.date = date;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setUserName(String userName) {
        this.userName = userName;
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

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }
}
