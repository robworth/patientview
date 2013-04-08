package com.worthsoln.test.repository;

import com.worthsoln.patientview.logon.PatientLogonWithTreatment;
import com.worthsoln.patientview.model.Patient;
import com.worthsoln.patientview.model.PatientCount;
import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.patientview.model.SpecialtyUserRole;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.PatientCountDao;
import com.worthsoln.repository.PatientDao;
import com.worthsoln.repository.UserMappingDao;
import com.worthsoln.test.helpers.RepositoryHelpers;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class PatientDaoTest extends BaseDaoTest {

    @Inject
    private PatientDao patientDao;

    @Inject
    private PatientCountDao patientCountDao;

    @Inject
    private RepositoryHelpers repositoryHelpers;

    private Specialty specialty;
    private User user;
    private SpecialtyUserRole specialtyUserRole;

    // these variables link user, usermapping, patient, and specialityuserrole objects together
    private static String NAME_OF_USER = "name";
    private static String NHS_NO_1 = "nhsno1";
    private static String CENTRE_1 = "centre1";

    @Before
    public void setupSystem() {
        specialty = repositoryHelpers.createSpecialty("Specialty1", "ten1", "A test specialty");

        user = repositoryHelpers.createUserWithMapping("username1", "test@test.com", "password", NAME_OF_USER,
                "screenname", CENTRE_1, NHS_NO_1, specialty);

        specialtyUserRole = repositoryHelpers.createSpecialtyUserRole(specialty, user, "patient");
    }

    @Test
    public void testSaveGet() {

        /**
         * save
         */

        // create a patient
        Patient patient = new Patient();
        patient.setNhsno(NHS_NO_1);
        patient.setCentreCode(CENTRE_1);
        patient.setSurname("surname1");

        // save it
        patientDao.save(patient);
        assertTrue("Cant' save patient", patient.getId() > 0);

        // create another patient
        patient = new Patient();
        patient.setNhsno("nhsno2");
        patient.setCentreCode(CENTRE_1);
        patient.setSurname("surname2");

        // save it too
        patientDao.save(patient);
        assertTrue("Can't save patient", patient.getId() > 0);

        /**
         * get
         */
        List<Patient> patients = patientDao.get(patient.getCentreCode());

        assertEquals("Incorrect number of patients found", 2, patients.size());
        assertEquals("Incorrect first patient surname", "surname1", patients.get(0).getSurname());

        /**
         * getUnitPatientsWithTreatmentDao
         */

        patients = patientDao.getUnitPatientsWithTreatmentDao(CENTRE_1, NHS_NO_1, NAME_OF_USER, false, specialty);
        assertTrue("Can't get patients with treatment", patients.size() > 0);

        /**
         * getUnitPatientsAllWithTreatmentDao
         */

        List<PatientLogonWithTreatment> patientsWithTreatment = patientDao.getUnitPatientsAllWithTreatmentDao(CENTRE_1,
                specialty);
        assertTrue("Can't get patients all with treatment", patientsWithTreatment.size() > 0);
    }

    @Test
    public void testSaveGetPatientCount() {
        PatientCount patientCount = new PatientCount();
        patientCount.setCount(4);
        patientCount.setDatestamp(Calendar.getInstance());
        patientCount.setRole("role1");
        patientCount.setUnitcode("UNITCODE1");
        patientCountDao.save(patientCount);

        assertTrue("Invalid id", patientCount.getId() > 0);

        patientCount = new PatientCount();
        patientCount.setCount(3);
        patientCount.setDatestamp(Calendar.getInstance());
        patientCount.setRole("role2");
        patientCount.setUnitcode("UNITCODE1");
        patientCountDao.save(patientCount);

        patientCount = new PatientCount();
        patientCount.setCount(2);
        patientCount.setDatestamp(Calendar.getInstance());
        patientCount.setRole("role3");
        patientCount.setUnitcode("UNITCODE2");
        patientCountDao.save(patientCount);

        patientCount = new PatientCount();
        patientCount.setCount(1);
        patientCount.setDatestamp(Calendar.getInstance());
        patientCount.setRole("role4");
        patientCount.setUnitcode("UNITCODE2");
        patientCountDao.save(patientCount);

        List<PatientCount> patientCounts = patientCountDao.get("UNITCODE1", "role2");

        assertEquals("Incorrect number resulted", 1, patientCounts.size());

        assertEquals("Incorrect count", 3, patientCounts.get(0).getCount());
    }
}
