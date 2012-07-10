package com.worthsoln.patientview;

import java.util.Calendar;

public class TestResultWithUnitShortname extends TestResult {

    private String shortname;

    public TestResultWithUnitShortname() {
    }

    public TestResultWithUnitShortname(String nhsno, String unitcode, Calendar datestamp, String testcode, String value, String shortname) {
        super(nhsno, unitcode, datestamp, testcode, value);
        this.shortname = shortname;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }
}
