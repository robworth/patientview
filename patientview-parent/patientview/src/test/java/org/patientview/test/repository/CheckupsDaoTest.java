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
import org.patientview.patientview.model.Checkups;
import org.patientview.patientview.model.Specialty;
import org.patientview.repository.CheckupsDao;
import org.patientview.test.helpers.RepositoryHelpers;

import javax.inject.Inject;

import static org.junit.Assert.*;

public class CheckupsDaoTest extends BaseDaoTest {

    @Inject
    private CheckupsDao checkupsDao;

    @Inject
    private RepositoryHelpers repositoryHelpers;

    @Before
    public void setup() {
        Specialty specialty = repositoryHelpers.createSpecialty("Specialty1", "Specialty1", "A test specialty");
        repositoryHelpers.createUserWithMapping("testUser", "test@hotmail.com", "111111", "test", "unitcodeA", "1234567890", specialty);
    }

    @Test
    public void testAddGetDelCheckups() throws Exception {
        Checkups checkups = new Checkups();
        checkups.setFootCheckPlace("Test foot place");
        checkups.setLastRetinalPlace("Test retinal place");
        checkups.setLeftDpPulse("23");
        checkups.setRightDpPulse("55");
        checkups.setNhsno("1234567890");

        checkupsDao.save(checkups);

        assertTrue("Invalid id for new checkups", checkups.getId() > 0);

        Checkups checkEntity = checkupsDao.get("testUser");

        assertNotNull("Checkups not saved", checkEntity);
        assertEquals("Foot check place not persisted", checkups.getFootCheckPlace(), checkEntity.getFootCheckPlace());
        assertEquals("Last retinal place not persisted", checkups.getLastRetinalPlace(), checkEntity.getLastRetinalPlace());
        assertEquals("Left DP Pulse not persisted", checkups.getLeftDpPulse(), checkEntity.getLeftDpPulse());
        assertEquals("Right DP Pulse not persisted", checkups.getRightDpPulse(), checkEntity.getRightDpPulse());

        checkupsDao.delete(checkups.getId());
        Checkups checkDelete = checkupsDao.get("testUser");
        assertNull("Checkups not deleted", checkDelete);

    }

    @Test
    public void testUpdateDelCheckups() throws Exception {
        Checkups checkups = new Checkups();
        checkups.setFootCheckPlace("Test foot place");
        checkups.setLastRetinalPlace("Test retinal place");
        checkups.setLeftDpPulse("23");
        checkups.setRightDpPulse("55");
        checkups.setNhsno("1234567890");

        checkupsDao.save(checkups);

        checkups.setRightDpPulse("255");

        checkupsDao.save(checkups);

        assertTrue("Invalid id for new checkups", checkups.getId() > 0);

        Checkups checkEntity = checkupsDao.get("testUser");

        assertNotNull("Checkups not saved", checkEntity);
        assertEquals("Right DP Pulse not persisted", "255", checkEntity.getRightDpPulse());

        checkupsDao.delete(checkups.getId());
        Checkups checkDelete = checkupsDao.get("testUser");
        assertNull("Checkups not deleted", checkDelete);

    }


}
