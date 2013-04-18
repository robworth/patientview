package com.worthsoln.test.repository;

import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.patientview.model.SpecialtyUserRole;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.SpecialtyDao;
import com.worthsoln.repository.SpecialtyUserRoleDao;
import com.worthsoln.test.helpers.RepositoryHelpers;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

/**
 *
 */
public class SpecialtyDaoTest extends BaseDaoTest {

    @Inject
    private RepositoryHelpers repositoryHelpers;

    @Inject
    private SpecialtyDao specialtyDao;

    @Inject
    private SpecialtyUserRoleDao specialtyUserRoleDao;

    @Test
    public void testAddGetSpecialty() {

        Specialty specialty = repositoryHelpers.createSpecialty("Specialty 1", "Specialty", "Test description");

        Specialty checkSpecialty = specialtyDao.get(specialty.getId());

        assertTrue("Invalid id for new specialty", checkSpecialty.getId() > 0);
        assertEquals("Name not persisted", specialty.getName(), checkSpecialty.getName());
    }

    @Test
    public void testAddGetSpecialtyUserRoles() {

        Specialty specialty = repositoryHelpers.createSpecialty("Specialty 1", "Specialty", "Test description");

        Specialty specialty2 = repositoryHelpers.createSpecialty("Specialty 2", "Specialty2", "Test description 2");

        User user = repositoryHelpers.createUser("test", "test@worthsolns.com", "password", "Firstname Lastname"
        );

        SpecialtyUserRole specialtyUserRole1 = repositoryHelpers.createSpecialtyUserRole(specialty, user, "patient");

        assertTrue("Invalid id", specialtyUserRole1.getId() > 0);

        SpecialtyUserRole specialtyUserRole2 = repositoryHelpers.createSpecialtyUserRole(specialty2, user, "patient");
        List<SpecialtyUserRole> specialtyUserRoles = specialtyUserRoleDao.get(user);

        assertEquals("specialtyUserRoles not persisted", 2, specialtyUserRoles.size());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testUniqueSpecialtyUserRoleConstraint() {
        Specialty specialty = repositoryHelpers.createSpecialty("Specialty 1", "Specialty", "Test description");

        User user = repositoryHelpers.createUser("test", "test@worthsolns.com", "password", "Firstname Lastname"
        );

        repositoryHelpers.createSpecialtyUserRole(specialty, user, "patient");
        repositoryHelpers.createSpecialtyUserRole(specialty, user, "patient");
    }
}
