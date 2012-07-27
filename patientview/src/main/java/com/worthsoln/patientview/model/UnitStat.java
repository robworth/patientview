package com.worthsoln.patientview.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UnitStat extends BaseModel {

    @Column(nullable = false)
    private String unitcode;

    @Column(nullable = false)
    private String yearmonth;

    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private int count;

    public UnitStat() {
    }

    public UnitStat(Long id, String unitcode, String yearmonth, String action, int count) {
        this.setId(id);
        setUnitcode(unitcode);
        this.yearmonth = yearmonth;
        this.action = action;
        this.count = count;
    }

    public UnitStat(String unitcode, String yearmonth, String action, int count) {
        setUnitcode(unitcode);
        this.yearmonth = yearmonth;
        this.action = action;
        this.count = count;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = (unitcode != null) ? unitcode.toUpperCase() : unitcode;
    }

    public String getYearmonth() {
        return yearmonth;
    }

    public void setYearmonth(String yearmonth) {
        this.yearmonth = yearmonth;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
