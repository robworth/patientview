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

import org.patientview.patientview.model.SplashPage;
import org.patientview.patientview.model.SplashPageUserSeen;
import org.patientview.patientview.model.Specialty;
import org.patientview.patientview.model.User;
import org.patientview.repository.SplashPageDao;
import org.patientview.repository.SplashPageUserSeenDao;
import org.patientview.test.helpers.RepositoryHelpers;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class SplashPageDaoTest extends BaseDaoTest {

    @Inject
    private SplashPageDao splashPageDao;

    @Inject
    private SplashPageUserSeenDao splashPageUserSeenDao;

    @Inject
    private RepositoryHelpers repositoryHelpers;

    private Specialty specialty;

    @Before
    public void createSplashPagesAndSeen() {

        specialty = repositoryHelpers.createSpecialty("Specialty1", "Specialty1", "A test specialty");

        SplashPage splashPage = new SplashPage();
        splashPage.setSpecialty(specialty);
        splashPage.setName("name1");
        splashPage.setLive(true);
        splashPage.setHeadline("headline1");
        splashPage.setBodytext("body1");
        splashPage.setUnitcode("UNITCODE1");
        splashPageDao.save(splashPage);

        SplashPageUserSeen splashPageUserSeen = new SplashPageUserSeen();
        splashPageUserSeen.setSplashpageid(splashPage.getId());
        splashPageUserSeen.setUsername("user1");
        splashPageUserSeenDao.save(splashPageUserSeen);

        splashPage = new SplashPage();
        splashPage.setSpecialty(specialty);
        splashPage.setName("name2");
        splashPage.setLive(true);
        splashPage.setHeadline("headline2");
        splashPage.setBodytext("body2");
        splashPage.setUnitcode("UNITCODE2");
        splashPageDao.save(splashPage);

        splashPageUserSeen = new SplashPageUserSeen();
        splashPageUserSeen.setSplashpageid(splashPage.getId());
        splashPageUserSeen.setUsername("user2");
        splashPageUserSeenDao.save(splashPageUserSeen);

        splashPage = new SplashPage();
        splashPage.setSpecialty(specialty);
        splashPage.setName("name3");
        splashPage.setLive(true);
        splashPage.setHeadline("headline3");
        splashPage.setBodytext("body3");
        splashPage.setUnitcode("UNITCODE3");
        splashPageDao.save(splashPage);

        splashPageUserSeen = new SplashPageUserSeen();
        splashPageUserSeen.setSplashpageid(splashPage.getId());
        splashPageUserSeen.setUsername("user3");
        splashPageUserSeenDao.save(splashPageUserSeen);
    }

    @Test
    public void testSaveGet() {

        SplashPage splashPage = new SplashPage();
        splashPage.setSpecialty(specialty);
        splashPage.setName("name");
        splashPage.setLive(true);
        splashPage.setHeadline("headline");
        splashPage.setBodytext("body");
        splashPage.setUnitcode("UNITCODE1");
        splashPageDao.save(splashPage);

        assertTrue("Invalid id after save", splashPage.getId() > 0);

        SplashPage check = splashPageDao.get(splashPage.getId());

        assertEquals("Check has incorrect name", "name", check.getName());
    }

    @Test
    public void testGetWithUnitCodes() {

        List<String> unitCodes = new ArrayList<String>();
        unitCodes.add("UNITCODE2");
        unitCodes.add("UNITCODE3");

        assertEquals("Incorrect number of splash pages returned", 2, splashPageDao.getAll(unitCodes, specialty).size());
    }

    @Test
    public void testGetSeenForPatientAndDelete() {

        SplashPage splashPage = new SplashPage();
        splashPage.setSpecialty(specialty);
        splashPage.setName("nameA");
        splashPage.setLive(true);
        splashPage.setHeadline("headlineA");
        splashPage.setBodytext("bodyA");
        splashPage.setUnitcode("UNITCODEA");
        splashPageDao.save(splashPage);

        SplashPageUserSeen splashPageUserSeen = new SplashPageUserSeen();
        splashPageUserSeen.setSplashpageid(splashPage.getId());
        splashPageUserSeen.setUsername("userA");
        splashPageUserSeenDao.save(splashPageUserSeen);

        User user = new User();
        user.setUsername("userA");

        assertEquals("Incorrect number of splash pages seen for patient returned",
                1, splashPageUserSeenDao.getSeenForPatient(user).size());

        splashPageUserSeenDao.delete(splashPageUserSeen.getId());

        assertEquals("Splash page seen not removed",
                0, splashPageUserSeenDao.getSeenForPatient(user).size());
    }
}
