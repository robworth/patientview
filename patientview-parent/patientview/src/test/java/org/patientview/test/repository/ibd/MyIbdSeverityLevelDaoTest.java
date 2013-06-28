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

package org.patientview.test.repository.ibd;

import org.patientview.ibd.model.MyIbd;
import org.patientview.ibd.model.MyIbdSeverityLevel;
import org.patientview.ibd.model.enums.Diagnosis;
import org.patientview.ibd.model.enums.DiseaseExtent;
import org.patientview.ibd.model.enums.Severity;
import org.patientview.repository.ibd.MyIbdDao;
import org.patientview.repository.ibd.MyIbdSeverityLevelDao;
import org.patientview.test.repository.BaseDaoTest;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class MyIbdSeverityLevelDaoTest extends BaseDaoTest {

    @Inject
    private MyIbdSeverityLevelDao myIbdSeverityLevelDao;

    @Inject
    private MyIbdDao myIbdDao;

    @Before
    public void initMyIbd() {
        // first create a myIbd object to go with this:
        MyIbd myIbd = new MyIbd();

        myIbd.setNhsno("1234567890");
        myIbd.setUnitcode("unit1");
        myIbd.setDiagnosis(Diagnosis.COLITIS_UNSPECIFIED);
        myIbd.setDiseaseExtent(DiseaseExtent.ILEO_COLONIC_DISEASE);
        myIbd.setYearOfDiagnosis(new Date());
        myIbd.setBodyPartAffected("Test");
        myIbd.setYearForSurveillanceColonoscopy(new Date());
        myIbd.setNamedConsultant("Test consultant");
        myIbd.setNurses("Test nurses");
        myIbd.setComplications("Test");
        myIbdDao.save(myIbd);
    }

    @Test
    public void testAddGetMyIbdSeverityLevel() throws Exception {
        MyIbdSeverityLevel myIbdSeverityLevel = getTestObject();

        myIbdSeverityLevelDao.save(myIbdSeverityLevel);

        MyIbdSeverityLevel checkMyIbdSeverityLevel = myIbdSeverityLevelDao.get(myIbdSeverityLevel.getId());

        assertNotNull(checkMyIbdSeverityLevel);
        assertTrue("Invalid id for new MyIbdSeverityLevel", checkMyIbdSeverityLevel.getId() > 0);
        assertEquals("Nhs no not stored", checkMyIbdSeverityLevel.getNhsno(), myIbdSeverityLevel.getNhsno());
        assertEquals("Severity not stored", checkMyIbdSeverityLevel.getSeverity(), myIbdSeverityLevel.getSeverity());
        assertEquals("Level not stored", checkMyIbdSeverityLevel.getLevel(Diagnosis.ULCERATIVE_COLITIS),
                myIbdSeverityLevel.getLevel(Diagnosis.ULCERATIVE_COLITIS));
    }

    @Test
    public void testAddGetUpdateNoDuplicateLevel() throws Exception {
        // first make a severity level and save
        MyIbdSeverityLevel myIbdSeverityLevel1 = getTestObject();
        myIbdSeverityLevelDao.save(myIbdSeverityLevel1);
        assertTrue("Id invalud", myIbdSeverityLevel1.getId() > 0);

        // then make another seveirty level of same type and when saved should have same id as the first
        MyIbdSeverityLevel myIbdSeverityLevel2 = getTestObject();
        myIbdSeverityLevelDao.save(myIbdSeverityLevel2);
        assertEquals("First object was not updated", myIbdSeverityLevel1.getId(), myIbdSeverityLevel2.getId());
    }

    @Test
    public void testAddGetMyIbdSeverityLevelWithNoValue() throws Exception {
        // because no value has been set it should not have stored in db so should not have an id after
        MyIbdSeverityLevel myIbdSeverityLevel = getTestObject();
        myIbdSeverityLevel.setLevel(-1);

        myIbdSeverityLevelDao.save(myIbdSeverityLevel);

        assertNull(myIbdSeverityLevel.getId());
    }

    @Test
    public void testAddGetMyIbdSeverityLevelUpdateWithNoValue() throws Exception {
        // first make a level and save
        MyIbdSeverityLevel myIbdSeverityLevel = getTestObject();
        myIbdSeverityLevelDao.save(myIbdSeverityLevel);
        assertTrue("Id invalud", myIbdSeverityLevel.getId() > 0);

        // next pull it back update the value and save again
        MyIbdSeverityLevel checkMyIbdSeverityLevel = myIbdSeverityLevelDao.get(myIbdSeverityLevel.getNhsno(),
                myIbdSeverityLevel.getSeverity());
        assertNotNull(checkMyIbdSeverityLevel);
        checkMyIbdSeverityLevel.setLevel(checkMyIbdSeverityLevel.getSeverity().getDefaultLevel(
                Diagnosis.ULCERATIVE_COLITIS));
        myIbdSeverityLevelDao.save(checkMyIbdSeverityLevel);

        // now try pull back one time it should be null
        checkMyIbdSeverityLevel = myIbdSeverityLevelDao.get(myIbdSeverityLevel.getNhsno(),
                myIbdSeverityLevel.getSeverity());

        assertTrue(!checkMyIbdSeverityLevel.hasValidId());
    }

    private MyIbdSeverityLevel getTestObject() {
        MyIbdSeverityLevel myIbdSeverityLevel = new MyIbdSeverityLevel();
        myIbdSeverityLevel.setNhsno("1234567890");
        myIbdSeverityLevel.setSeverity(Severity.SEVERE);
        myIbdSeverityLevel.setLevel(12);
        return myIbdSeverityLevel;
    }
}
