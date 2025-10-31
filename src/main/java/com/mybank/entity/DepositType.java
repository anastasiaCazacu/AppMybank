package com.mybank.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "deposit_types")
public class DepositType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id

    private String code;// ex: "SIGHT"- Retragere oricând, dobândă redusă 0.50%
                        // "TERM" folosit in backend- Blocare fixă, dobândă mai mare 3.20%
                        //FLOTANT- Dobândă variabilă, ajustabilă periodic 3.75%
                        //PREMIUM - Venit stabil, dobândă garantată 4.00 %
    private String labelRo;       // La vedere, La termen
    private String descriptionRo; // explicație pentru UI

    private Double defaultRate; // opțional: rata implicită sugerata

    //petru Audit-va fi optionala
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    //legatura cu userul care in creaza "ADMIN", "BANK"
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    //legatura cine a facut update- pentru audit
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "updated_by")
    private User updatedBy;

    // geteri si seteri

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getDescriptionRo() {
        return descriptionRo;
    }

    public void setDescriptionRo(String descriptionRo) {
        this.descriptionRo = descriptionRo;
    }

    // Getter și Setter pentru defaultRate
    public Double getDefaultRate() {
        return defaultRate;
    }

    public void setDefaultRate(Double defaultRate) {
        this.defaultRate = defaultRate;
    }

    // Getter și Setter pentru id (opțional, dar recomandat)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLabelRo(Object labelRo) {
        this.labelRo = labelRo.toString();
    }
}
