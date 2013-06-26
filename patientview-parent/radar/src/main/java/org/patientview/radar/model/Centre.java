package org.patientview.radar.model;

import java.util.Comparator;

public class Centre extends BaseModel {
    private String name;
    private String abbreviation;
    private Country country;
    private String unitCode;
    private String renalAdminEmail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getRenalAdminEmail() {
        return renalAdminEmail;
    }

    public void setRenalAdminEmail(String renalAdminEmail) {
        this.renalAdminEmail = renalAdminEmail;
    }

    public static Comparator<Centre> getComparator() {
        return new Comparator<Centre>() {
            public int compare(Centre o1, Centre o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        };
    }
}
