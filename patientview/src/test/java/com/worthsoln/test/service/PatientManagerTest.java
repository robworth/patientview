package com.worthsoln.test.service;

import com.worthsoln.patientview.model.Patient;
import com.worthsoln.patientview.model.Tenancy;
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
    private Tenancy tenancy1, tenancy2;
    private Patient patient;

    @Before
    public void setupSystem() {
        tenancy1 = serviceHelpers.createTenancy("Tenant 1", "ten1", "Test description");
        tenancy2 = serviceHelpers.createTenancy("Tenant 2", "ten2", "Test description 2");

        // create an admin adminUser and tenancy and log them in
        User adminUser = serviceHelpers.createUser("admin1", "admin1@test.com", "pass", "Test Unit Admin", "Testy");
        serviceHelpers.createTenancyUserRole(tenancy1, adminUser, "unitadmin");
        securityHelpers.loginAsUser(adminUser.getUsername(), tenancy1);

        // setup a system with a user with 2 tenancy roles
        user = serviceHelpers.createUserWithMapping("Username", "username@test.com", "pass", "Test User",
                "Testy", "unitcode1", "nhsno1", tenancy1);


        serviceHelpers.createTenancyUserRole(tenancy1, user, "patient");
        serviceHelpers.createTenancyUserRole(tenancy2, user, "admin");

        // setup a patient record for this user in tenancy 1
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
     *  This is run by a "unitadmin" who is logged into a tenancy
     */
    public void testGetUnitPatientsWithTreatment() {

//      todo work out why this doesn't work when run with H2 database
//
//        List results = patientManager.getUnitPatientsWithTreatment(patient.getCentreCode(), patient.getNhsno(),
//                user.getName(), false);
//
//        List<TenancyUserRole> tenancyUserRoles = userManager.getTenancyUserRoles(user);
//
//        assertTrue("Incorrect results", results != null && results.size() == 1);
    }
}
