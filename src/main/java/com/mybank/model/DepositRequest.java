package com.mybank.model;
import jakarta.validation.constraints.*;

public class DepositRequest {
    @NotNull
    @Positive
    private Double amount;

    @NotNull
    @Min(0)
    @Max(100)
    private Double rate;

    @NotNull
    @Positive
    private Integer termMonths;

    @NotNull
    private Long userId;

    @NotNull
    private Long createdById;

    // Getters și Setters
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public Double getRate() { return rate; }
    public void setRate(Double rate) { this.rate = rate; }

    public Integer getTermMonths() { return termMonths; }
    public void setTermMonths(Integer termMonths) { this.termMonths = termMonths; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getCreatedById() { return createdById; }
    public void setCreatedById(Long createdById) { this.createdById = createdById; }

}
