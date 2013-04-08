package com.worthsoln.test.repository;

import com.worthsoln.patientview.model.TestResult;
import com.worthsoln.repository.TestResultDao;
import org.junit.Test;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestResultDaoTest extends BaseDaoTest {

    @Inject
    TestResultDao testResultDao;

    @Test
    public void testAddGetTestResult() throws Exception {
        TestResult testResult = getTestObject();

        /**
         * add
         */
        testResultDao.save(testResult);
        assertTrue("Can't save testResult", testResult.getId() > 0);

        /**
         * get
         */
        List<TestResult> savedTestResults = testResultDao.get(testResult.getNhsno(), testResult.getUnitcode());
        assertTrue("Can't get testResults", savedTestResults.size() > 0);
    }

    @Test
    public void testDeleteTestResult() throws Exception {
        TestResult testResult = getTestObject();

        /**
         * add
         */
        testResultDao.save(testResult);
        assertTrue("Can't save testResult", testResult.getId() > 0);

        /**
         * delete
         */
        testResultDao.deleteTestResults(testResult.getNhsno(), testResult.getUnitcode());

        List<TestResult> savedTestResults = testResultDao.get(testResult.getNhsno(), testResult.getUnitcode());
        assertTrue("Can't delete testResults", savedTestResults.size() == 0);

        /**
         * delete within a time range
         */
        testResultDao.save(testResult);
        assertTrue("Can't save testResult", testResult.getId() > 0);

        testResultDao.deleteTestResultsWithinTimeRange(testResult.getNhsno(), testResult.getUnitcode(),
                testResult.getTestcode(), getYesterday(), getTomorrow());

        savedTestResults = testResultDao.get(testResult.getNhsno(), testResult.getUnitcode());
        assertTrue("Can't delete testResults within a time range", savedTestResults.size() == 0);

    }

    private TestResult getTestObject() throws Exception {
        TestResult testResult = new TestResult();

        testResult.setNhsno("1234567890");
        testResult.setUnitcode("unit1");
        testResult.setDatestamp(new Timestamp((new Date()).getTime()));
        testResult.setPrepost("prepost");
        testResult.setTestcode("testcode");
        testResult.setTimestamp(Calendar.getInstance());
        testResult.setValue("1");

        return testResult;
    }

    private Date getYesterday() {
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);

        return yesterday.getTime();
    }

    private Date getTomorrow() {
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DATE, 1);

        return tomorrow.getTime();
    }

}
