package com.mybank.dto.deposit;

import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public class DepositCreateDTO {
    @NotNull
    private Double amount;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @NotNull
    private Double rate;

    @NotNull
    private Long userId; // clientul beneficiar

    @NotNull
    private Long depositTypeId;

}
