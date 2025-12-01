package com.mybank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Date;

@Entity
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private Double amount; // suma depozitului

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @NotNull
    @Min(0)
    @Max(100)
    private Double rate; // rata dobânzii

    @Enumerated(EnumType.STRING)
    private DepositStatus status; // ACTIVE, CLOSED, EXPIRED

    @NotNull
    @Positive
    private Integer termMonths; // durata depozitului în luni

    // referință către tipul de depozit
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deposit_type_id")
    private DepositType type;

    // clientul beneficiar
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // cine a creat depozitul
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    //  am adaugat un
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
        if (status == null) {
            status = DepositStatus.ACTIVE;
        }
        if (startDate == null) {
            startDate = new Date();
        }
        if (endDate == null && termMonths != null) {
            // calculează endDate pe baza startDate + termMonths
            endDate = Date.from(startDate.toInstant()
                    .plusSeconds((long) termMonths * 30L * 24L * 60L * 60L));
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
        updateStatus();
    }

    // Metoda pentru actualizarea statusului
    public void updateStatus() {
        Date today = new Date();
        if (endDate != null && endDate.before(today)) {
            status = DepositStatus.EXPIRED;
        }
    }

    // Gettere si setere
    public Long getId() { return id; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public Double getRate() { return rate; }
    public void setRate(Double rate) { this.rate = rate; }

    public DepositStatus getStatus() { return status; }
    public void setStatus(DepositStatus status) { this.status = status; }

    public Integer getTermMonths() { return termMonths; }
    public void setTermMonths(Integer termMonths) { this.termMonths = termMonths; }

    public DepositType getType() { return type; }
    public void setType(DepositType type) { this.type = type; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public User getCreatedBy() { return createdBy; }
    public void setCreatedBy(User createdBy) { this.createdBy = createdBy; }

    public Date getCreatedAt() { return createdAt; }
    public Date getUpdatedAt() { return updatedAt; }

}
