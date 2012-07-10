package com.worthsoln.patientview;

public class TestResultDateRange {

    private String nhsNo;
    private String unitcode;
    private String testCode;
    private String startDate;
    private String stopDate;

    public TestResultDateRange() {
    }

    public TestResultDateRange(String nhsNo, String unitcode, String testCode, String startDate, String endDate) {
        this.nhsNo = nhsNo;
        this.unitcode = unitcode;
        this.testCode = testCode;
        this.startDate = startDate;
        this.stopDate = endDate;
    }

    public String getStopDate() {
        return stopDate;
    }

    public void setStopDate(String stopDate) {
        this.stopDate = stopDate;
    }

    public String getNhsNo() {
        return nhsNo;
    }

    public void setNhsNo(String nhsNo) {
        this.nhsNo = nhsNo;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }
}
