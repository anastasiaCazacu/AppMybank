package com.mybank.model;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;


public class CreditRequest {

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Interest rate is required")
    @Min(value = 1, message = "Interest rate must be at least 1%")
    @Max(value = 100, message = "Interest rate cannot exceed 100%")
    private Double interestRate;

    @NotNull(message = "Term is required")
    @Min(value = 1, message = "Term must be at least 1 month")
    @Max(value = 360, message = "Term cannot exceed 30 years")
    private Integer termMonths;

    @FutureOrPresent(message = "Due date must be today or in the future")
    private LocalDate dueDate;

    private String approvedBy;

    // Getters
    public Long getUserId() {
        return userId;
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
    public void setUserId(Long userId) {
        this.userId = userId;
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
