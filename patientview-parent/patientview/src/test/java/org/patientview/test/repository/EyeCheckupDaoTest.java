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

import org.junit.Before;
import org.junit.Test;
import org.patientview.model.Specialty;
import org.patientview.patientview.model.EyeCheckup;
import org.patientview.repository.EyeCheckupDao;
import org.patientview.test.helpers.RepositoryHelpers;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class EyeCheckupDaoTest extends BaseDaoTest {

    @Inject
    private EyeCheckupDao eyeCheckupDao;

    @Inject
    private RepositoryHelpers repositoryHelpers;

    @Before
    public void setup() {
        Specialty specialty = repositoryHelpers.createSpecialty("Specialty1", "Specialty1", "A test specialty");
        repositoryHelpers.createUserWithMapping("testUser", "test@hotmail.com", "111111", "test", "unitcodeA", "1234567890", specialty);
    }

    @Test
    public void testAddGetDelEyeCheckup() throws Exception {
        EyeCheckup checkups = new EyeCheckup();
        checkups.setLastRetinalPlace("Test retinal place");
        checkups.setLeftMGrade("23");
        checkups.setRightRGrade("55");
        checkups.setUnitcode("testUnitcode");
        checkups.setNhsno("1234567890");

        eyeCheckupDao.save(checkups);

        assertTrue("Invalid id for new checkups", checkups.getId() > 0);

        List<EyeCheckup> checkupses = eyeCheckupDao.get("testUser");

        assertNotNull("EyeCheckup not saved", checkupses);
        assertEquals("Wrong size of checkups", 1, checkupses.size());
        assertEquals("Eye check place not persisted", checkups.getLastRetinalPlace(), checkupses.get(0).getLastRetinalPlace());
        assertEquals("Left DP Pulse not persisted", checkups.getLeftMGrade(), checkupses.get(0).getLeftMGrade());
        assertEquals("Right DP Pulse not persisted", checkups.getRightRGrade(), checkupses.get(0).getRightRGrade());

        eyeCheckupDao.delete(checkups.getId());
        List<EyeCheckup> checkDelete = eyeCheckupDao.get("testUser");
        assertEquals("EyeCheckup not deleted", 0, checkDelete.size());

    }

    @Test
    public void testUpdateDelEyeCheckup() throws Exception {
        EyeCheckup checkups = new EyeCheckup();
        checkups.setLastRetinalPlace("Test foot place");
        checkups.setLeftRGrade("23");
        checkups.setRightVA("55");
        checkups.setUnitcode("testUnitcode");
        checkups.setNhsno("1234567890");

        eyeCheckupDao.save(checkups);

        checkups.setRightVA("255");

        eyeCheckupDao.save(checkups);

        assertTrue("Invalid id for new checkups", checkups.getId() > 0);

        List<EyeCheckup> checkupses = eyeCheckupDao.get("testUser");

        assertNotNull("EyeCheckup not saved", checkupses);
        assertEquals("Wrong size of checkups", 1, checkupses.size());
        assertEquals("Right DP Pulse not persisted", "255", checkupses.get(0).getRightVA());

        eyeCheckupDao.delete(checkups.getId());
        List<EyeCheckup> checkDelete = eyeCheckupDao.get("testUser");
        assertEquals("EyeCheckup not deleted", 0, checkDelete.size());

    }


}
