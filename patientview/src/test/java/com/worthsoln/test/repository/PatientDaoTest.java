package com.worthsoln.test.repository;

import com.worthsoln.patientview.model.Patient;
import com.worthsoln.patientview.model.PatientCount;
import com.worthsoln.repository.PatientCountDao;
import com.worthsoln.repository.PatientDao;
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

    @Test
    public void testSaveGet() {

        Patient patient = new Patient();
        // required fields
        patient.setNhsno("nhsno1");
        patient.setCentreCode("centrecode1");
        // a not required fields
        patient.setSurname("surname1");
        patientDao.save(patient);

        patient = new Patient();
        // required fields
        patient.setNhsno("nhsno2");
        patient.setCentreCode("centrecode1");
        // a not required fields
        patient.setSurname("surname2");
        patientDao.save(patient);

        assertTrue("Invalid id", patient.getId() > 0);

        List<Patient> patients = patientDao.get(patient.getCentreCode());

        assertEquals("Incorrect number of patients found", 2, patients.size());

        assertEquals("Incorrect first patient surname", "surname1", patients.get(0).getSurname());
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
