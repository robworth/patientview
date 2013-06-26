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

import org.patientview.patientview.model.Panel;
import org.patientview.patientview.model.ResultHeading;
import org.patientview.patientview.model.Specialty;
import org.patientview.repository.ResultHeadingDao;
import org.patientview.test.helpers.RepositoryHelpers;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.assertEquals;
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
}
