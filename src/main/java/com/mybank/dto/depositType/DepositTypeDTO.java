package com.mybank.dto.depositType;

public class DepositTypeDTO {
    private Long id;
    private String code;
    private String labelRo;
    private String descriptionRo;
    private Double defaultRate;

    public void setId(Long id) {
        this.id = id;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public void setLabelRo(String labelRo) {
        this.labelRo = labelRo;
    }

    public void setDescriptionRo(String descriptionRo) {
        this.descriptionRo = descriptionRo;
    }

    public void setDefaultRate(Double defaultRate) {
        this.defaultRate = defaultRate;
    }
}
