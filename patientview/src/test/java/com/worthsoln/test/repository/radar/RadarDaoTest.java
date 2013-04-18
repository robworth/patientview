package com.worthsoln.test.repository.radar;

import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.RadarDao;
import com.worthsoln.repository.UnitDao;
import com.worthsoln.repository.UserDao;
import com.worthsoln.test.helpers.RepositoryHelpers;
import com.worthsoln.test.repository.BaseDaoTest;
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
