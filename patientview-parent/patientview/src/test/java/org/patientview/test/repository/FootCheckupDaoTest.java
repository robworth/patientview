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
import org.patientview.patientview.model.FootCheckup;
import org.patientview.model.Specialty;
import org.patientview.repository.FootCheckupDao;
import org.patientview.test.helpers.RepositoryHelpers;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

public class FootCheckupDaoTest extends BaseDaoTest {

    @Inject
    private FootCheckupDao checkupsDao;

    @Inject
    private RepositoryHelpers repositoryHelpers;

    @Before
    public void setup() {
        Specialty specialty = repositoryHelpers.createSpecialty("Specialty1", "Specialty1", "A test specialty");
        repositoryHelpers.createUserWithMapping("testUser", "test@hotmail.com", "111111", "test", "unitcodeA", "1234567890", specialty);
    }

    @Test
    public void testAddGetDelFootCheckup() throws Exception {
        FootCheckup checkups = new FootCheckup();
        checkups.setFootCheckPlace("Test foot place");
        //checkups.setLastRetinalPlace("Test retinal place");
        checkups.setLeftDpPulse("23");
        checkups.setRightDpPulse("55");
        checkups.setUnitcode("testUnitcode");
        checkups.setNhsno("1234567890");

        checkupsDao.save(checkups);

        assertTrue("Invalid id for new checkups", checkups.getId() > 0);

        List<FootCheckup> checkupses = checkupsDao.get("testUser");

        assertNotNull("FootCheckup not saved", checkupses);
        assertEquals("Wrong size of checkups", 1, checkupses.size());
        assertEquals("Foot check place not persisted", checkups.getFootCheckPlace(), checkupses.get(0).getFootCheckPlace());
        //assertEquals("Last retinal place not persisted", checkups.getLastRetinalPlace(), checkupses.get(0).getLastRetinalPlace());
        assertEquals("Left DP Pulse not persisted", checkups.getLeftDpPulse(), checkupses.get(0).getLeftDpPulse());
        assertEquals("Right DP Pulse not persisted", checkups.getRightDpPulse(), checkupses.get(0).getRightDpPulse());

        checkupsDao.delete(checkups.getId());
        List<FootCheckup> checkDelete = checkupsDao.get("testUser");
        assertEquals("FootCheckup not deleted", 0, checkDelete.size());

    }

    @Test
    public void testUpdateDelFootCheckup() throws Exception {
        FootCheckup checkups = new FootCheckup();
        checkups.setFootCheckPlace("Test foot place");
        //checkups.setLastRetinalPlace("Test retinal place");
        checkups.setLeftDpPulse("23");
        checkups.setRightDpPulse("55");
        checkups.setUnitcode("testUnitcode");
        checkups.setNhsno("1234567890");

        checkupsDao.save(checkups);

        checkups.setRightDpPulse("255");

        checkupsDao.save(checkups);

        assertTrue("Invalid id for new checkups", checkups.getId() > 0);

        List<FootCheckup> checkupses = checkupsDao.get("testUser");

        assertNotNull("FootCheckup not saved", checkupses);
        assertEquals("Wrong size of checkups", 1, checkupses.size());
        assertEquals("Right DP Pulse not persisted", "255", checkupses.get(0).getRightDpPulse());

        checkupsDao.delete(checkups.getId());
        List<FootCheckup> checkDelete = checkupsDao.get("testUser");
        assertEquals("FootCheckup not deleted", 0, checkDelete.size());

    }


}
