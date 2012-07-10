package com.worthsoln.patientview;

import com.worthsoln.patientview.utils.TimestampUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestResult {

    private String nhsno;
    private String unitcode;
    private Calendar datestamped;
    private String prepost;
    private String testcode;
    private String value;

    public TestResult() {
    }

    public TestResult(String nhsno, String unitcode, Calendar datestamp, String testcode, String value) {
        this.nhsno = nhsno;
        setUnitcode(unitcode);
        this.testcode = testcode;
        this.datestamped = datestamp;
        this.value = value;
        this.prepost = "";
    }

    public Calendar getDatestamped() {
        return datestamped;
    }

    public void setDatestamp(Timestamp datestamped) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(datestamped.getTime());
        this.datestamped = cal;
    }

    public void setTimestamp(Calendar timestamp) {
        this.datestamped = timestamp;    
    }

    public Calendar getTimestamp() {
        return datestamped;
    }

    public void setDatestampString(String dateStampString) {
        this.datestamped = TimestampUtils.createTimestamp(dateStampString);
    }

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = (unitcode != null) ? unitcode.toUpperCase() : unitcode;
    }

    public String getPrepost() {
        return prepost;
    }

    public void setPrepost(String prepost) {
        this.prepost = prepost;
    }

    public String getTestcode() {
        return testcode;
    }

    public void setTestcode(String testcode) {
        this.testcode = testcode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFormattedDatestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
        if ((datestamped.get(Calendar.HOUR_OF_DAY) == 0) && (datestamped.get(Calendar.MINUTE) == 0)) {
            return dateFormat.format(datestamped.getTime());
        } else {
            return dateTimeFormat.format(datestamped.getTime());
        }
    }

    public String getSortingDatestamp() {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm");
        return dateTimeFormat.format(datestamped.getTime());
    }

    public String getIsoDatestamp() {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return dateTimeFormat.format(datestamped.getTime());
    }

    public String getIsoDayDatestamp() {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateTimeFormat.format(datestamped.getTime());
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TestResult that = (TestResult) o;
        if (datestamped != null ? !datestamped.equals(that.datestamped) : that.datestamped != null) {
            return false;
        }
        if (nhsno != null ? !nhsno.equals(that.nhsno) : that.nhsno != null) {
            return false;
        }
        if (prepost != null ? !prepost.equals(that.prepost) : that.prepost != null) {
            return false;
        }
        if (testcode != null ? !testcode.equals(that.testcode) : that.testcode != null) {
            return false;
        }
        if (unitcode != null ? !unitcode.equals(that.unitcode) : that.unitcode != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result;
        result = (nhsno != null ? nhsno.hashCode() : 0);
        result = 31 * result + (unitcode != null ? unitcode.hashCode() : 0);
        result = 31 * result + (datestamped != null ? datestamped.hashCode() : 0);
        result = 31 * result + (prepost != null ? prepost.hashCode() : 0);
        result = 31 * result + (testcode != null ? testcode.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
