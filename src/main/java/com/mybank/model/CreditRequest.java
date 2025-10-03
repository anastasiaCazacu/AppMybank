package com.mybank.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreditRequest {
    @NotNull
    private BigDecimal amount;

    @NotNull
    private Long userId;

    public Long getUserId() {
        return userId;
    }
}
