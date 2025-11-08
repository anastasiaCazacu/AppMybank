package com.mybank.dto.deposit;

import java.util.Date;

public class DepositResponseDTO {
    private Long id;
    private Double amount;
    private Date startDate;
    private Date endDate;
    private Double rate;
    private String status;
    private String depositTypeLabel;
    private String clientUsername;
    private String createdByUsername;
    private Date createdAt;
}
