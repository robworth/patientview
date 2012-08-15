package com.worthsoln.test.repository.ibd;

import com.worthsoln.ibd.model.enums.colitis.NumberOfStoolsNighttime;
import com.worthsoln.ibd.model.symptoms.Colitis;
import com.worthsoln.ibd.model.enums.Feeling;
import com.worthsoln.ibd.model.enums.colitis.NumberOfStoolsDaytime;
import com.worthsoln.ibd.model.enums.colitis.PresentBlood;
import com.worthsoln.ibd.model.enums.colitis.ToiletTiming;
import com.worthsoln.repository.ibd.ColitisDao;
import com.worthsoln.test.repository.BaseDaoTest;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.inject.Inject;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ColitisDaoTest extends BaseDaoTest {

    @Inject
    private ColitisDao colitisDao;

    @Test
    @Rollback(value = false)
    public void testAddGetColitis() throws Exception {

        Colitis colitis = getTestObject();

        colitisDao.save(colitis);

        assertTrue("Invalid id for new Colitis", colitis.getId() > 0);

        Colitis checkColitis = colitisDao.get(colitis.getId());

        assertNotNull(checkColitis);
        assertEquals("NHS no not persisted", checkColitis.getNhsno(), colitis.getNhsno());
        assertEquals("Colitis date not persisted", checkColitis.getColitisDate(), colitis.getColitisDate());
        assertEquals("Colitis stools day not persisted", checkColitis.getNumberOfStoolsDaytime(), colitis.getNumberOfStoolsDaytime());
        assertEquals("Colitis stools night not persisted", checkColitis.getNumberOfStoolsNighttime(), colitis.getNumberOfStoolsNighttime());
        assertEquals("Colitis toilet timing not persisted", checkColitis.getToiletTiming(), colitis.getToiletTiming());
        assertEquals("Colitis present blood not persisted", checkColitis.getPresentBlood(), colitis.getPresentBlood());
        assertEquals("Colitis feeling not persisted", checkColitis.getFeeling(), colitis.getFeeling());

    }

    private Colitis getTestObject() throws Exception {
        Colitis colitis = new Colitis();

        colitis.setNhsno("1234567890");
        colitis.setColitisDate(new Date());
        colitis.setNumberOfStoolsDaytime(NumberOfStoolsDaytime.SEVEN_TO_NINE);
        colitis.setNumberOfStoolsNighttime(NumberOfStoolsNighttime.FOUR_TO_SIX);
        colitis.setToiletTiming(ToiletTiming.HAVING_ACCIDENTS);
        colitis.setPresentBlood(PresentBlood.A_TRACE);
        colitis.setFeeling(Feeling.BELOW_PAR);

        return colitis;
    }

}
