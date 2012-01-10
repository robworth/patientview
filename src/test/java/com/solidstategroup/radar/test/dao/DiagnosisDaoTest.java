package com.solidstategroup.radar.test.dao;

import com.solidstategroup.radar.dao.DiagnosisDao;
import com.solidstategroup.radar.model.Diagnosis;
import com.solidstategroup.radar.model.DiagnosisCode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DiagnosisDaoTest extends BaseDaoTest {

    @Autowired
    private DiagnosisDao diagnosisDao;

    @Test
    public void testGetDiagnosis() {
        Diagnosis diagnosis = diagnosisDao.getDiagnosis(117L);
        assertNotNull("Diagnosis is null", diagnosis);
        assertEquals("ID is wrong on diagnosis", new Long(117), diagnosis.getId());
    }

    @Test
    public void testGetDiagnosisCode() {
        DiagnosisCode diagnosisCode = diagnosisDao.getDiagnosisCode(1L);
        assertNotNull("Diagnosis code was null", diagnosisCode);
        assertEquals("Wrong ID", new Long(1), diagnosisCode.getId());
        assertEquals("Wrong desc", "Steroid Resistant Nephrotic Syndrome ", diagnosisCode.getDescription());
        assertEquals("Wrong abbr", "SRNS", diagnosisCode.getAbbreviation());
    }

    @Test
    public void testGetDiagnosisCodes() {
        List<DiagnosisCode> diagnosisCodes = diagnosisDao.getDiagnosisCodes();
        assertNotNull("Diagnosis code list was null", diagnosisCodes);
        assertEquals("Wrong size", 2, diagnosisCodes.size());
    }

}
