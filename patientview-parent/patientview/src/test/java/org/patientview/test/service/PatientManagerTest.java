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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.patientview.model.Patient;
import org.patientview.model.Specialty;
import org.patientview.model.Unit;
import org.patientview.model.enums.SourceType;
import org.patientview.patientview.model.User;
import org.patientview.service.PatientManager;
import org.patientview.service.UnitManager;
import org.patientview.service.UserManager;
import org.patientview.test.helpers.SecurityHelpers;
import org.patientview.test.helpers.ServiceHelpers;

import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

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

    @Inject
    private UnitManager unitManager;

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
        patient.setUnitcode("unitcode1");
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
//        List results = patientManager.getUnitPatientsWithTreatment(patient.getUnitcode(), patient.getNhsno(),
//                user.getName(), false);
//
//        List<SpecialtyUserRole> specialtyUserRoles = userManager.getSpecialtyUserRoles(user);
//
//        assertTrue("Incorrect results", results != null && results.size() == 1);
    }


    /**
     * This is to test that the PatientManager can get the latest test results with unit code;
     *
     * Create 3 patient record all with different dates and get the latest one.
     *
     */
    @Test
    public void testGetPatientTestRecentTestDataWithUnitCode(){

        final String testNhsNo = "7865675675";
        final Unit unit1 = createUnit("unitCode1");
        final Unit unit2 = createUnit("unitCode2");
        final Unit unit3 = createUnit("unitCode3");

        Patient testPatient1 = createPatient(unit1.getUnitcode(), testNhsNo);
        Patient testPatient2 = createPatient(unit2.getUnitcode(), testNhsNo);
        Patient testPatient3 = createPatient(unit3.getUnitcode(), testNhsNo);

        Calendar calendar = Calendar.getInstance();

        // Set the date to the current date
        testPatient2.setMostRecentTestResultDateRangeStopDate(calendar.getTime());
        patientManager.save(testPatient2);

        //Set the clock back and then set the date again. Therefore making unit2 the unit which should be returned
        calendar.add(Calendar.MONTH, -1);
        testPatient3.setMostRecentTestResultDateRangeStopDate(calendar.getTime());
        patientManager.save(testPatient3);


        Map.Entry<String, Date> dateEntry =  patientManager.getLatestTestResultUnit(testNhsNo);

        Assert.assertEquals("Unit 2 should be the unit entry returned", dateEntry.getKey(), unit2.getUnitcode());



    }

    private Unit createUnit(String unitCode) {
        Unit unit = new Unit();
        unit.setUnitcode(unitCode);
        unit.setName(unitCode);
        unit.setSourceType("renalunit");
        unit.setShortname(unitCode);

        unitManager.save(unit);

        return unit;
    }

    private Patient createPatient(String unitCode, String nshNo) {
        Patient testPatient = new Patient();
        testPatient.setForename("Test");
        testPatient.setSurname("Patient");
        testPatient.setNhsno(nshNo);
        testPatient.setUnitcode(SourceType.PATIENT_VIEW.getName());
        testPatient.setUnitcode(unitCode);

        patientManager.save(testPatient);

        return testPatient;
    }


}
