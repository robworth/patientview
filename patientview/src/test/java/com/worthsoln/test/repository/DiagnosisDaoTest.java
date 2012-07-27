package com.worthsoln.test.repository;

import com.worthsoln.patientview.model.Diagnosis;
import com.worthsoln.repository.DiagnosisDao;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

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
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDiagnosis("Test diagnosis");
        diagnosis.setDisplayorder("1");
        diagnosis.setUnitcode("unitcode1");
        diagnosis.setNhsno("123456789");

        diagnosisDao.save(diagnosis);

        assertTrue("Invalid id for new diagnosis", diagnosis.getId() > 0);

        Diagnosis checkDiagnosis = diagnosisDao.get(diagnosis.getId());

        assertNotNull(checkDiagnosis);
        assertEquals("Diagnosis diagnosis not persisted", diagnosis.getDiagnosis(), checkDiagnosis.getDiagnosis());
        assertEquals("Diagnosis display order not persisted", diagnosis.getDisplayorder(),
                checkDiagnosis.getDisplayorder());
        assertEquals("Diagnosis unit code not persisted", diagnosis.getUnitcode(), checkDiagnosis.getUnitcode());
        assertEquals("Diagnosis nhs no not persisted", diagnosis.getNhsno(), checkDiagnosis.getNhsno());
    }

    @Test
    public void testGetOtherDiagnosis() throws Exception {
        Diagnosis diagnosis1 = new Diagnosis();
        diagnosis1.setDiagnosis("Test diagnosis 1");
        diagnosis1.setDisplayorder("1");
        diagnosis1.setUnitcode("unitcode1");
        diagnosis1.setNhsno("123456789");

        diagnosisDao.save(diagnosis1);
        assertTrue("Invalid id for new diagnosis 1", diagnosis1.getId() > 0);

        Diagnosis diagnosis2 = new Diagnosis();
        diagnosis2.setDiagnosis("Test diagnosis 2");
        diagnosis2.setDisplayorder("2");
        diagnosis2.setUnitcode("unitcode1");
        diagnosis2.setNhsno("123456789");

        diagnosisDao.save(diagnosis2);
        assertTrue("Invalid id for new diagnosis 2", diagnosis2.getId() > 0);

        List<Diagnosis> checkDiagnoses = diagnosisDao.getOtherDiagnoses("123456789", "unitcode1");

        assertNotNull(checkDiagnoses);
        assertTrue("No diagnosis found found", !checkDiagnoses.isEmpty() && checkDiagnoses.size() > 0);
    }
}
