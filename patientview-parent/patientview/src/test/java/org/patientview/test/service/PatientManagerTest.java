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

package org.patientview.test.service;

import org.patientview.patientview.model.Patient;
import org.patientview.patientview.model.Specialty;
import org.patientview.patientview.model.User;
import org.patientview.service.PatientManager;
import org.patientview.service.UserManager;
import org.patientview.test.helpers.SecurityHelpers;
import org.patientview.test.helpers.ServiceHelpers;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

/**
 *      Note: this is tested at a service level to allow legacy and hibernate dao to work together,
 *      (in separate transactions).
 */
public class PatientManagerTest extends BaseServiceTest {

    @Inject
    private PatientManager patientManager;

    @Inject
    private SecurityHelpers securityHelpers;

    @Inject
    private ServiceHelpers serviceHelpers;

    @Inject
    private UserManager userManager;

    private User user;
    private Specialty specialty1, specialty2;
    private Patient patient;

    @Before
    public void setupSystem() {
        specialty1 = serviceHelpers.createSpecialty("Specialty 1", "Specialty1", "Test description");
        specialty2 = serviceHelpers.createSpecialty("Specialty 2", "Specialty2", "Test description 2");

        // create an admin adminUser and specialty and log them in
        User adminUser = serviceHelpers.createUser("admin1", "admin1@test.com", "pass", "Test Unit Admin");
        serviceHelpers.createSpecialtyUserRole(specialty1, adminUser, "unitadmin");
        securityHelpers.loginAsUser(adminUser.getUsername(), specialty1);

        // setup a system with a user with 2 specialty roles
        user = serviceHelpers.createUserWithMapping("Username", "username@test.com", "pass", "Test User",
                "unitcode1", "nhsno1", specialty1);


        serviceHelpers.createSpecialtyUserRole(specialty1, user, "patient");
        serviceHelpers.createSpecialtyUserRole(specialty2, user, "admin");

        // setup a patient record for this user in specialty 1
        patient = new Patient();
        // required fields
        patient.setNhsno("nhsno1");
        patient.setCentreCode("unitcode1");
        // a not required fields
        patient.setSurname("surname1");
        patientManager.save(patient);
    }

    @Test
    /**
     *  This is run by a "unitadmin" who is logged into a specialty
     */
    public void testGetUnitPatientsWithTreatment() {

//      todo work out why this doesn't work when run with H2 database
//
//        List results = patientManager.getUnitPatientsWithTreatment(patient.getCentreCode(), patient.getNhsno(),
//                user.getName(), false);
//
//        List<SpecialtyUserRole> specialtyUserRoles = userManager.getSpecialtyUserRoles(user);
//
//        assertTrue("Incorrect results", results != null && results.size() == 1);
    }
}
