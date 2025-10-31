package com.mybank.dto.depositType;

import org.hibernate.validator.constraints.NotBlank;

public class DepositTypeCreateDTO {
    @NotBlank
    private String code;

    @NotBlank
    private String labelRo;

    private String descriptionRo;

    private Double defaultRate;

    public String getDescriptionRo() {

        return descriptionRo;
    }

    public Double getDefaultRate() {
        return defaultRate;
    }

    public Object getLabelRo() {
        return labelRo;
    }

    public String getCode() {
        return code;
    }
}
