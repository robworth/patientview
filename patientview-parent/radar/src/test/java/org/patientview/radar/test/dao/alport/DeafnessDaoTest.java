package org.patientview.radar.test.dao.alport;

import org.patientview.radar.dao.alport.DeafnessDao;
import org.patientview.radar.model.alport.Deafness;
import org.patientview.radar.test.dao.BaseDaoTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DeafnessDaoTest extends BaseDaoTest {

    @Autowired
    private DeafnessDao deafnessDao;

    @Test
    public void testAddGetDeafness() throws Exception {
        Deafness deafness = getTestObject();

        deafnessDao.save(deafness);

        assertTrue("Invalid id for new deafness", deafness.getId() > 0);

        Deafness checkDeafness = deafnessDao.get(deafness.getId());

        assertNotNull(checkDeafness);
        assertEquals("Radar no not persisted", checkDeafness.getRadarNo(), checkDeafness.getRadarNo());
        assertEquals("Evidence of deafness not persisted", checkDeafness.getEvidenceOfDeafness(),
                checkDeafness.getEvidenceOfDeafness());
        assertEquals("Age problem first noticed not persisted", checkDeafness.getAgeProblemFirstNoticed(),
                checkDeafness.getAgeProblemFirstNoticed());
        assertEquals("Age started using hearing aid not persisted", checkDeafness.getAgeStartedUsingHearingAid(),
                checkDeafness.getAgeStartedUsingHearingAid());
    }

    private Deafness getTestObject() {
        Deafness deafness = new Deafness();
        deafness.setRadarNo(1L);
        deafness.setEvidenceOfDeafness(Deafness.EvidenceOfDeafness.YES_HEARING_AID_NEEDED);
        deafness.setAgeProblemFirstNoticed(1);
        deafness.setAgeStartedUsingHearingAid(1);
        return deafness;
    }
}
