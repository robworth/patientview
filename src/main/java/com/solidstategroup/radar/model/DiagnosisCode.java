package com.solidstategroup.radar.model;

public class DiagnosisCode extends BaseModel {

    private String description;
    private String abbreviation;
    public static final Long SRNS_ID = 1l;
    public static final Long MPGN_ID = 2l;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
