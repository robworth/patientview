package org.patientview.radar.test.dao.alport;

import org.patientview.radar.dao.alport.GeneticsDao;
import org.patientview.radar.model.Genetics;
import org.patientview.radar.test.dao.BaseDaoTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GeneticsDaoTest extends BaseDaoTest {

    @Autowired
    private GeneticsDao geneticsDao;

    @Test
    public void testAddGetGenetics() throws Exception {
        Genetics genetics = getTestObject();

        geneticsDao.save(genetics);

        assertTrue("Invalid id for new genetics", genetics.getId() > 0);

        Genetics checkGenetics = geneticsDao.get(genetics.getId());

        assertNotNull(checkGenetics);
        assertEquals("Radar no not persisted", checkGenetics.getRadarNo(), genetics.getRadarNo());
        assertEquals("Tests done not persisted", checkGenetics.getTestsDone(), genetics.getTestsDone());
        assertEquals("Labs where tests were done not persisted", checkGenetics.getLabWhereTestWasDone(),
                genetics.getLabWhereTestWasDone());
        assertEquals("Tests done by not persisted", checkGenetics.getTestDoneOn(), genetics.getTestDoneOn());
        assertEquals("Reference no not persisted", checkGenetics.getReferenceNumber(), genetics.getReferenceNumber());
        assertEquals("What results showed not persisted", checkGenetics.getWhatResultsShowed(),
                genetics.getWhatResultsShowed());
        assertEquals("Key evidence not persisted", checkGenetics.getKeyEvidence(), genetics.getKeyEvidence());
    }

    private Genetics getTestObject() {
        Genetics genetics = new Genetics();
        genetics.setRadarNo(1L);
        genetics.setTestsDone(Genetics.TestsDone.NO);
        genetics.setLabWhereTestWasDone("Test where test was done");
        genetics.setTestDoneOn("Test tests done on");
        genetics.setReferenceNumber("1234");
        genetics.setWhatResultsShowed("Test what results showed");
        genetics.setKeyEvidence("Test key evidence");
        return genetics;
    }
}
