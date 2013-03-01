package com.worthsoln.patientview.unitstat;

import com.worthsoln.patientview.model.UnitStat;

import java.util.HashMap;
import java.util.Map;

public class UnitMonthStats {

    private String unitcode;
    private String yearmonth;
    private String downloadFilename;
    private Map<String, String> stats = new HashMap<String, String>();

    public UnitMonthStats(String unitcode, String yearmonth) {
        this.unitcode = unitcode;
        this.yearmonth = yearmonth;
    }

    public UnitMonthStats(UnitStat unitStat) {
        this.unitcode = unitStat.getUnitcode();
        this.yearmonth = unitStat.getYearmonth();
    }

    public String getUnitcode() {
        return unitcode;
    }

    public String getYearmonth() {
        return yearmonth;
    }

    public String getDownloadFilename() {
        return downloadFilename;
    }

    public void setDownloadFilename(String downloadFilename) {
        this.downloadFilename = downloadFilename;
    }

    public void addStat(String action, String value) {
        stats.put(action, value);
    }

    public String getValue(String action) {
        Object value = stats.get(action);
        if (value == null) {
            return "";
        } else {
            return (String) value;
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UnitMonthStats that = (UnitMonthStats) o;
        if (stats != null ? !stats.equals(that.stats) : that.stats != null) {
            return false;
        }
        if (unitcode != null ? !unitcode.equals(that.unitcode) : that.unitcode != null) {
            return false;
        }
        if (yearmonth != null ? !yearmonth.equals(that.yearmonth) : that.yearmonth != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = unitcode != null ? unitcode.hashCode() : 0;
        result = 31 * result + (yearmonth != null ? yearmonth.hashCode() : 0);
        result = 31 * result + (stats != null ? stats.hashCode() : 0);
        return result;
    }
}
