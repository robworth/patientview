package com.worthsoln.test.repository;

import com.worthsoln.patientview.model.SplashPage;
import com.worthsoln.patientview.model.SplashPageUserSeen;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.SplashPageDao;
import com.worthsoln.repository.SplashPageUserSeenDao;
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

    @Test
    public void testSaveGet() {

        SplashPage splashPage = new SplashPage();
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

    @Before
    public void createSplashPagesAndSeen() {

        SplashPage splashPage = new SplashPage();
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
    public void testGetWithUnitCodes() {

        List<String> unitCodes = new ArrayList<String>();
        unitCodes.add("UNITCODE2");
        unitCodes.add("UNITCODE3");

        assertEquals("Incorrect number of splash pages returned", 2, splashPageDao.getAll(unitCodes).size());
    }

    @Test
    public void testGetSeenForPatientAndDelete() {

        SplashPage splashPage = new SplashPage();
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
