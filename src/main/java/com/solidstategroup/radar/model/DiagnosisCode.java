package com.solidstategroup.radar.model;

import java.util.Comparator;

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

    public static Comparator<DiagnosisCode> getComparator() {
        return new Comparator<DiagnosisCode>() {
            public int compare(DiagnosisCode o1, DiagnosisCode o2) {
                return o1.getAbbreviation().compareToIgnoreCase(o2.getAbbreviation());
            }
        };
    }
}
