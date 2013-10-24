/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.test.repository;

import org.patientview.patientview.model.TestResult;
import org.patientview.repository.TestResultDao;
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
