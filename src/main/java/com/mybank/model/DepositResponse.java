package com.mybank.model;
import com.mybank.entity.DepositStatus;
import java.util.Date;

public class DepositResponse {
    private Long id;
    private Double amount;
    private Double rate;
    private Integer termMonths;
    private Date startDate;
    private Date endDate;
    private DepositStatus status;
    private Long userId;
    private Long createdById;
    private Date createdAt;
    private Date updatedAt;

    // Constructor pentru mapare rapidă
    public DepositResponse(Long id, Double amount, Double rate, Integer termMonths,
                           Date startDate, Date endDate, DepositStatus status,
                           Long userId, Long createdById, Date createdAt, Date updatedAt) {
        this.id = id;
        this.amount = amount;
        this.rate = rate;
        this.termMonths = termMonths;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.userId = userId;
        this.createdById = createdById;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters și Setters
    public Long getId() { return id; }
    public Double getAmount() { return amount; }
    public Double getRate() { return rate; }
    public Integer getTermMonths() { return termMonths; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }
    public DepositStatus getStatus() { return status; }
    public Long getUserId() { return userId; }
    public Long getCreatedById() { return createdById; }
    public Date getCreatedAt() { return createdAt; }
    public Date getUpdatedAt() { return updatedAt; }
}
