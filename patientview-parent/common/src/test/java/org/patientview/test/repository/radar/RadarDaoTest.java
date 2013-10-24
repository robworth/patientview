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

package org.patientview.test.repository.radar;

import org.patientview.patientview.model.Unit;
import org.patientview.patientview.model.User;
import org.patientview.repository.RadarDao;
import org.patientview.repository.UnitDao;
import org.patientview.repository.UserDao;
import org.patientview.test.helpers.RepositoryHelpers;
import org.patientview.test.repository.BaseDaoTest;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RadarDaoTest extends BaseDaoTest {

    @Inject
    private RepositoryHelpers repositoryHelpers;

    @Inject
    private UserDao userDao;

    @Inject
    private UnitDao unitDao;

    @Inject
    private RadarDao radarDao;

    @Test
    public void testCreateProfessionalUserInRadar() throws Exception {
        Unit unit = new Unit();
        unit.setSpecialty(repositoryHelpers.createSpecialty("Specialty1", "Specialty1", "A test specialty"));
        // required fields
        unit.setUnitcode("UNITCODE1");
        unit.setName("z");
        unit.setShortname("nam1");
        unitDao.save(unit);

        // first create a user
        User user = new User();
        user.setEmail("test@worthsolns.com");
        user.setName("Firstname Lastname");
        user.setPassword("password");
        user.setUsername("test");

        userDao.save(user);

        // first test to see if its in there which it should be
        assertFalse(radarDao.userExistsInRadar(user.getId()));

        // then create a user in radar and try again
        radarDao.createProfessionalUserInRadar(user, unit);

        // check again
        assertTrue(radarDao.userExistsInRadar(user.getId()));
    }

    @Test
    public void testRemoveUserFromRadar() throws Exception {
        Unit unit = new Unit();
        unit.setSpecialty(repositoryHelpers.createSpecialty("Specialty1", "Specialty1", "A test specialty"));
        // required fields
        unit.setUnitcode("UNITCODE1");
        unit.setName("z");
        unit.setShortname("nam1");
        unitDao.save(unit);

        // first create a user
        User user = new User();
        user.setEmail("test@worthsolns.com");
        user.setName("Firstname Lastname");
        user.setPassword("password");
        user.setUsername("test");

        userDao.save(user);

        // create the user in radar
        radarDao.createProfessionalUserInRadar(user, unit);

        // now remove the user and there should be no reference to it
        radarDao.removeUserFromRadar(user.getId());

        // test if it exists
        assertFalse(radarDao.userExistsInRadar(user.getId()));
    }
}
