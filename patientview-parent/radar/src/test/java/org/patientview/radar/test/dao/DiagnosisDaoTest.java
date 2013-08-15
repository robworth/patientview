package org.patientview.radar.test.dao;

import org.patientview.radar.dao.DiagnosisDao;
import org.patientview.radar.model.Diagnosis;
import org.patientview.radar.model.DiagnosisCode;
import org.patientview.radar.model.Karotype;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DiagnosisDaoTest extends BaseDaoTest {

    @Autowired
    private DiagnosisDao diagnosisDao;

    @Test
    public void testSaveDiagnosis() {
        // test save
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setText("Testing");
        diagnosisDao.saveDiagnosis(diagnosis);
        assertNotNull(diagnosis.getId());

        // test update
        Diagnosis diagnosis2 = new Diagnosis();
        diagnosis2.setId(new Long(139));
        diagnosis2.setText("Testing");
        diagnosisDao.saveDiagnosis(diagnosis2);
    }

    @Test
    public void testGetDiagnosis() {
        Diagnosis diagnosis = diagnosisDao.getDiagnosis(117L);
        assertNotNull("Diagnosis is null", diagnosis);
        assertEquals("ID is wrong on diagnosis", new Long(117), diagnosis.getId());
    }

    @Test
    public void testGetDiagnosisUnknown() {
        Diagnosis diagnosis = diagnosisDao.getDiagnosis(1172323L);
        assertNull("Diagnosis is not null", diagnosis);
    }

    @Test
    public void testGetDiagnosisByRadar() {
        Diagnosis diagnosis = diagnosisDao.getDiagnosisByRadarNumber(239);
        assertNotNull("Diagnosis is null", diagnosis);
        assertEquals("ID is wrong on diagnosis", new Long(114), diagnosis.getId());
        assertEquals("Radar number is wrong", new Long(239), diagnosis.getRadarNumber());
    }

    @Test
    public void testGetDiagnosisByRadarUnknown() {
        Diagnosis diagnosis = diagnosisDao.getDiagnosisByRadarNumber(1172323L);
        assertNull("Diagnosis is not null", diagnosis);
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
    public void testGetDiagnosisCodeUnknown() {
        DiagnosisCode diagnosisCode = diagnosisDao.getDiagnosisCode(1231L);
        assertNull("Diagnosis code was not null", diagnosisCode);
    }

    @Test
    public void testGetDiagnosisCodes() {
        List<DiagnosisCode> diagnosisCodes = diagnosisDao.getDiagnosisCodes();
        assertNotNull("Diagnosis code list was null", diagnosisCodes);
        assertEquals("Wrong size", 2, diagnosisCodes.size());
    }

    @Test
    public void testGetKarotype() {
        Karotype karotype = diagnosisDao.getKarotype(1L);
        assertNotNull("Karotype is null", karotype);
        assertEquals("Wrong ID", new Long(1), karotype.getId());
        assertEquals("Wrong description", "XX", karotype.getDescription());
    }

    @Test
    public void testGetKarotypeUnknown() {
        Karotype karotype = diagnosisDao.getKarotype(123L);
        assertNull("Karotype is not null", karotype);
    }

    @Test
    public void testGetKarotypes() {
        List<Karotype> karotypes = diagnosisDao.getKarotypes();
        assertNotNull("Karotype list is null", karotypes);
        assertEquals("List is wrong size", 4, karotypes.size());
    }
}
