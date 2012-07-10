package com.worthsoln.patientview;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import com.worthsoln.database.StorableItem;

public class TestResultDao extends StorableItem {

    private TestResult testResult;

    public TestResultDao() {
    }

    public TestResultDao(TestResult testResult) {
        this.testResult = testResult;
    }

    public String getIdColumnName() {
        return "nhsno";
    }

    public Object getIdParameter() {
        return testResult.getNhsno();
    }

    public String[] getColumnNames() {
        return new String[]{"unitcode", "testcode", "prepost", "datestamp", "value"};
    }

    public ArrayList getColumnParameters() {
        ArrayList params = new ArrayList();

        params.add(testResult.getUnitcode());
        params.add(testResult.getTestcode());

        Calendar timestampCal = testResult.getDatestamped();
        Timestamp timestamp = new Timestamp(timestampCal.getTimeInMillis());

        params.add(timestamp);
        params.add(testResult.getPrepost());
        params.add(testResult.getValue());

        return params;
    }

    public Class getTableMapper() {
        return TestResult.class;
    }

    public String getTableName() {
        return "testresult";
    }
}
