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

import org.patientview.model.Unit;
import org.patientview.patientview.model.Panel;
import org.patientview.patientview.model.ResultHeading;
import org.patientview.model.Specialty;
import org.patientview.patientview.model.*;
import org.patientview.repository.ResultHeadingDao;
import org.patientview.repository.TestResultDao;
import org.patientview.repository.UnitDao;
import org.patientview.test.helpers.RepositoryHelpers;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class ResultHeadingTest extends BaseDaoTest {

    @Inject
    private ResultHeadingDao resultHeadingDao;

    @Inject
    private RepositoryHelpers repositoryHelpers;

    private Specialty specialty;

    @Inject
    private UnitDao unitDao;

    @Inject
    private TestResultDao testResultDao;

    @Before
    public void setupSystem() {
        specialty = repositoryHelpers.createSpecialty("Specialty", "specialty1", "A test Specialty");
    }

    @Test
    public void testSaveGet() {

        ResultHeading resultHeading = new ResultHeading();
        resultHeading.setSpecialty(specialty);
        resultHeading.setHeading("heading");
        resultHeading.setHeadingcode("HEADA");
        resultHeading.setLink("http://www.google.com/");
        resultHeading.setPanel(2);
        resultHeading.setPanelorder(3);
        resultHeading.setRollover("rollover");

        resultHeadingDao.save(resultHeading);

        assertTrue("Invalid id", resultHeading.getId() > 0);

        ResultHeading check = resultHeadingDao.get(resultHeading.getHeadingcode(), specialty);

        assertTrue("Result heading not found via headingcode", check != null && check.getHeading().equals("heading"));
    }

    @Test
    public void testSaveGetAllDelete() {

        ResultHeading resultHeading = new ResultHeading();
        resultHeading.setSpecialty(specialty);
        resultHeading.setHeading("heading");
        resultHeading.setHeadingcode("HEAD");
        resultHeading.setLink("http://www.google.com/");
        resultHeading.setPanel(2);
        resultHeading.setPanelorder(3);
        resultHeading.setRollover("rollover");

        resultHeadingDao.save(resultHeading);

        resultHeading = new ResultHeading();
        resultHeading.setSpecialty(specialty);
        resultHeading.setHeading("heading2");
        resultHeading.setHeadingcode("HEAD2");
        resultHeading.setLink("http://www.google2.com/");
        resultHeading.setPanel(2);
        resultHeading.setPanelorder(3);
        resultHeading.setRollover("rollover2");

        resultHeadingDao.save(resultHeading);

        List<ResultHeading> results = resultHeadingDao.getAll(specialty);

        assertEquals("Incorrect number of results", 2, results.size());

        resultHeadingDao.delete(resultHeading.getHeadingcode(), specialty);

        results = resultHeadingDao.getAll(specialty);

        assertEquals("Incorrect number of results after delete", 1, results.size());
    }

    @Test
    public void testSaveGetUsingPanel() {

        ResultHeading resultHeading = new ResultHeading();
        resultHeading.setSpecialty(specialty);
        resultHeading.setHeading("heading");
        resultHeading.setHeadingcode("HEAD");
        resultHeading.setLink("http://www.google.com/");
        resultHeading.setPanel(2);
        resultHeading.setPanelorder(3);
        resultHeading.setRollover("rollover");

        resultHeadingDao.save(resultHeading);

        resultHeading = new ResultHeading();
        resultHeading.setSpecialty(specialty);
        resultHeading.setHeading("heading2");
        resultHeading.setHeadingcode("HEAD2");
        resultHeading.setLink("http://www.google2.com/");
        resultHeading.setPanel(1);
        resultHeading.setPanelorder(3);
        resultHeading.setRollover("rollover2");

        resultHeadingDao.save(resultHeading);

        resultHeading = new ResultHeading();
        resultHeading.setSpecialty(specialty);
        resultHeading.setHeading("heading3");
        resultHeading.setHeadingcode("HEAD3");
        resultHeading.setLink("http://www.google3.com/");
        resultHeading.setPanel(1);
        resultHeading.setPanelorder(1);
        resultHeading.setRollover("rollover3");

        resultHeadingDao.save(resultHeading);

        List<ResultHeading> results = resultHeadingDao.get(1, specialty);

        assertEquals("Incorrect number of results", 2, results.size());

        // heading3 returned first due to panel order
        assertEquals("Incorrect result heading returned", resultHeading, results.get(0));
    }

    @Test
    public void testGetPanels() {

        ResultHeading resultHeading = new ResultHeading();
        resultHeading.setSpecialty(specialty);
        resultHeading.setHeading("heading");
        resultHeading.setHeadingcode("HEAD");
        resultHeading.setLink("http://www.google.com/");
        resultHeading.setPanel(2);
        resultHeading.setPanelorder(3);
        resultHeading.setRollover("rollover");

        resultHeadingDao.save(resultHeading);

        resultHeading = new ResultHeading();
        resultHeading.setSpecialty(specialty);
        resultHeading.setHeading("heading2");
        resultHeading.setHeadingcode("HEAD2");
        resultHeading.setLink("http://www.google2.com/");
        resultHeading.setPanel(1);
        resultHeading.setPanelorder(3);
        resultHeading.setRollover("rollover2");

        resultHeadingDao.save(resultHeading);

        resultHeading = new ResultHeading();
        resultHeading.setSpecialty(specialty);
        resultHeading.setHeading("heading3");
        resultHeading.setHeadingcode("HEAD3");
        resultHeading.setLink("http://www.google3.com/");
        resultHeading.setPanel(1);
        resultHeading.setPanelorder(1);
        resultHeading.setRollover("rollover3");

        resultHeadingDao.save(resultHeading);

        List<Panel> results = resultHeadingDao.getPanels(specialty);

        assertEquals("Incorrect number of results", 2, results.size());

        // heading3 returned first due to panel order
        assertEquals("Incorrect panel returned first", 1, results.get(0).getPanel());
        assertEquals("Incorrect panel returned last", 2, results.get(1).getPanel());
    }

    @Test
    public void testGetResultHeading() {
        Unit unit = new Unit();
        unit.setSpecialty(specialty);
        unit.setUnitcode("UNITCODEA");
        unit.setName("unit 1");
        unit.setShortname("unit 1");
        unitDao.save(unit);

        unit = new Unit();
        unit.setSpecialty(specialty);
        unit.setUnitcode("UNITCODEB");
        unit.setName("unit 2");
        unit.setShortname("unit 2");
        unitDao.save(unit);

        User user = repositoryHelpers.createUserWithMapping("testuser", "testuser@test.com", "p", "testuser", "UNITCODEA",
                "1234567890", specialty);
        repositoryHelpers.createSpecialtyUserRole(specialty, user, "patient");

        ResultHeading resultHeading = new ResultHeading();
        resultHeading.setSpecialty(specialty);
        resultHeading.setHeading("headingA");
        resultHeading.setHeadingcode("HEADA");
        resultHeading.setLink("http://www.google.com/");
        resultHeading.setPanel(2);
        resultHeading.setPanelorder(3);
        resultHeading.setRollover("rollover");
        resultHeadingDao.save(resultHeading);

        ResultHeading resultHeading2 = new ResultHeading();
        resultHeading2.setSpecialty(specialty);
        resultHeading2.setHeading("headingB");
        resultHeading2.setHeadingcode("HEADB");
        resultHeading2.setLink("http://www.test.com/");
        resultHeading2.setPanel(1);
        resultHeading2.setPanelorder(2);
        resultHeading2.setRollover("rollover");
        resultHeadingDao.save(resultHeading2);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -4);
        TestResult testResult1 = new TestResult();
        testResult1.setNhsno("1234567890");
        testResult1.setUnitcode("UNITCODEA");
        testResult1.setPrepost("prepost");
        testResult1.setTestcode("HEADA");
        testResult1.setTimestamp(calendar);
        testResult1.setValue("1");
        testResultDao.save(testResult1);

        List<ResultHeading> checkList = resultHeadingDao.getAll(specialty, "testuser");

        assertNotNull("Couldn't get testresults", checkList);
        assertEquals("Wrong size of testresults", 1, checkList.size());
        assertEquals("Get incorrect testresults", resultHeading.getHeadingcode(), checkList.get(0).getHeadingcode());
        assertEquals("Get incorrect testresults", resultHeading.getHeading(), checkList.get(0).getHeading());
    }
}
