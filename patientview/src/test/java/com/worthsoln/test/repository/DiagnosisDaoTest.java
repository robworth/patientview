package com.worthsoln.test.repository;

import com.worthsoln.patientview.model.Diagnosis;
import com.worthsoln.repository.DiagnosisDao;
import org.junit.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DiagnosisDaoTest extends BaseDaoTest {

    @Inject
    private DiagnosisDao diagnosisDao;

    @Test
    public void testAddGetDiagnosis() throws Exception {
        Diagnosis diagnosis = getTestObject();

        /**
         * add
         */
        diagnosisDao.save(diagnosis);
        assertTrue("Invalid id for new diagnosis", diagnosis.getId() > 0);

        /**
         * get
         */
        Diagnosis savedDiagnosis = diagnosisDao.get(diagnosis.getId());

        assertNotNull(savedDiagnosis);
        assertEquals("Diagnosis diagnosis not persisted", diagnosis.getDiagnosis(), savedDiagnosis.getDiagnosis());
        assertEquals("Diagnosis display order not persisted", diagnosis.getDisplayorder(),
                savedDiagnosis.getDisplayorder());
        assertEquals("Diagnosis unit code not persisted", diagnosis.getUnitcode(), savedDiagnosis.getUnitcode());
        assertEquals("Diagnosis nhs no not persisted", diagnosis.getNhsno(), savedDiagnosis.getNhsno());

        /**
         * delete
         */
        diagnosisDao.deleteOtherDiagnoses(savedDiagnosis.getNhsno(), savedDiagnosis.getUnitcode());

        List<Diagnosis> savedDiagnoses = diagnosisDao.getOtherDiagnoses(savedDiagnosis.getNhsno(),
                savedDiagnosis.getUnitcode());
        assertTrue("Can't delete diagnoses", savedDiagnoses.size() == 0);
    }

    @Test
    public void testDeleteDiagnosis() throws Exception {
        Diagnosis diagnosis = getTestObject();

        /**
         * add
         */
        diagnosisDao.save(diagnosis);
        assertTrue("Invalid id for new diagnosis", diagnosis.getId() > 0);

        /**
         * delete
         */
        diagnosisDao.deleteOtherDiagnoses(diagnosis.getNhsno(), diagnosis.getUnitcode());

        List<Diagnosis> savedDiagnoses = diagnosisDao.getOtherDiagnoses(diagnosis.getNhsno(), diagnosis.getUnitcode());
        assertTrue("Can't delete diagnoses", savedDiagnoses.size() == 0);
    }


    @Test
    public void testGetOtherDiagnosis() throws Exception {
        Diagnosis diagnosis = getTestObject();

        diagnosisDao.save(diagnosis);
        assertTrue("Invalid id for new diagnosis 1", diagnosis.getId() > 0);

        Diagnosis diagnosis2 = getSecondTestObject();

        diagnosisDao.save(diagnosis2);
        assertTrue("Invalid id for new diagnosis 2", diagnosis2.getId() > 0);

        List<Diagnosis> checkDiagnoses = diagnosisDao.getOtherDiagnoses("123456789", "unitcode1");

        assertNotNull(checkDiagnoses);
        assertTrue("No diagnosis found found", !checkDiagnoses.isEmpty() && checkDiagnoses.size() > 0);
    }

    private Diagnosis getTestObject() throws Exception {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDiagnosis("Test diagnosis");
        diagnosis.setDisplayorder("1");
        diagnosis.setUnitcode("unitcode1");
        diagnosis.setNhsno("123456789");

        return diagnosis;
    }

    private Diagnosis getSecondTestObject() throws Exception {
        Diagnosis diagnosis2 = new Diagnosis();
        diagnosis2.setDiagnosis("Test diagnosis 2");
        diagnosis2.setDisplayorder("2");
        diagnosis2.setUnitcode("unitcode1");
        diagnosis2.setNhsno("123456789");

        return diagnosis2;
    }
}
