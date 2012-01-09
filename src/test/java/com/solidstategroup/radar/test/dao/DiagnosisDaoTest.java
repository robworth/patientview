package com.solidstategroup.radar.test.dao;

import com.solidstategroup.radar.dao.DiagnosisDao;
import com.solidstategroup.radar.model.Diagnosis;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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

}
