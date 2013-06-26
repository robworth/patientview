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
import org.patientview.ibd.model.enums.Diagnosis;
import org.patientview.ibd.model.enums.DiseaseExtent;
import org.patientview.repository.ibd.MyIbdDao;
import org.patientview.test.repository.BaseDaoTest;
import org.junit.Test;

import javax.inject.Inject;


import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MyIbdDaoTest extends BaseDaoTest {

    @Inject
    private MyIbdDao myIbdDao;

    @Test
    public void testAddGetMyIbd() throws Exception {
        MyIbd myIbd = getTestObject();

        myIbdDao.save(myIbd);

        assertTrue("Invalid id for new ibd", myIbd.getId() > 0);

        MyIbd checkMyIbd = myIbdDao.get(myIbd.getId());

        assertNotNull(checkMyIbd);
        assertEquals("Diagnosis not persisted", checkMyIbd.getDiagnosis(), myIbd.getDiagnosis());
        assertEquals("Disease extent not persisted", checkMyIbd.getDiseaseExtent(), myIbd.getDiseaseExtent());
        assertEquals("Year of diagnosis not persisted", checkMyIbd.getYearOfDiagnosis(), myIbd.getYearOfDiagnosis());
        assertEquals("Body part affected not persisted", checkMyIbd.getBodyPartAffected(), myIbd.getBodyPartAffected());
        assertEquals("Complications not persisted", checkMyIbd.getComplications(), myIbd.getComplications());
        assertEquals("Year for surveillance colonoscopy not persisted", checkMyIbd.getYearForSurveillanceColonoscopy(),
                myIbd.getYearForSurveillanceColonoscopy());
        assertEquals("Named consultant not persisted", checkMyIbd.getNamedConsultant(), myIbd.getNamedConsultant());
        assertEquals("Nurses not persisted", checkMyIbd.getNurses(), myIbd.getNurses());
    }

    @Test
    public void testGetByNhsNo() throws Exception {
        MyIbd myIbd = getTestObject();

        myIbdDao.save(myIbd);

        assertTrue("Invalid id for new ibd", myIbd.getId() > 0);

        MyIbd checkMyIbd = myIbdDao.get(myIbd.getNhsno());

        assertNotNull(checkMyIbd);
    }

    private MyIbd getTestObject() throws Exception {
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

        return myIbd;
    }
}
