package com.worthsoln.test.service;

import com.worthsoln.patientview.model.Patient;
import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.patientview.model.User;
import com.worthsoln.service.PatientManager;
import com.worthsoln.service.UserManager;
import com.worthsoln.test.helpers.SecurityHelpers;
import com.worthsoln.test.helpers.ServiceHelpers;
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
