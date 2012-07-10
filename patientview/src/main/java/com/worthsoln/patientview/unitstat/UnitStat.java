package com.worthsoln.patientview.unitstat;

public class UnitStat {

    private int id;
    private String unitcode;
    private String yearmonth;
    private String action;
    private int count;

    public UnitStat() {
    }

    public UnitStat(int id, String unitcode, String yearmonth, String action, int count) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
