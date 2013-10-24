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

import org.patientview.patientview.model.Specialty;
import org.patientview.patientview.model.SpecialtyUserRole;
import org.patientview.patientview.model.User;
import org.patientview.repository.SpecialtyDao;
import org.patientview.repository.SpecialtyUserRoleDao;
import org.patientview.test.helpers.RepositoryHelpers;
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
