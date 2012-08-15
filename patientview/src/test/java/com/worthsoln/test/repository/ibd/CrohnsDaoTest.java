package com.worthsoln.test.repository.ibd;

import com.worthsoln.ibd.model.symptoms.Crohns;
import com.worthsoln.ibd.model.enums.Feeling;
import com.worthsoln.ibd.model.enums.crohns.AbdominalPain;
import com.worthsoln.ibd.model.enums.crohns.Complication;
import com.worthsoln.ibd.model.enums.crohns.MassInTummy;
import com.worthsoln.repository.ibd.CrohnsDao;
import com.worthsoln.test.repository.BaseDaoTest;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.inject.Inject;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CrohnsDaoTest extends BaseDaoTest {

    @Inject
    private CrohnsDao crohnsDao;

    @Test
    @Rollback(value = false)
    public void testAddGetCrohns() throws Exception {

        Crohns crohns = getTestObject();

        crohnsDao.save(crohns);

        assertTrue("Invalid id for new crohns", crohns.getId() > 0);

        Crohns checkCrohns = crohnsDao.get(crohns.getId());

        assertNotNull(checkCrohns);
        assertEquals("NHS no not persisted", checkCrohns.getNhsno(), crohns.getNhsno());
        assertEquals("Crohns date not persisted", checkCrohns.getSymptomDate(), crohns.getSymptomDate());
        assertEquals("Crohns abdominal pain not persisted", checkCrohns.getAbdominalPain(), crohns.getAbdominalPain());
        assertEquals("Crohns open bowels not persisted", checkCrohns.getOpenBowels(), crohns.getOpenBowels());
        assertEquals("Crohns feeling not persisted", checkCrohns.getFeeling(), crohns.getFeeling());
        assertEquals("Crohns complications not persisted", checkCrohns.getComplications(), crohns.getComplications());
        assertEquals("Crohns mass in tummy not persisted", checkCrohns.getMassInTummy(), crohns.getMassInTummy());

    }

    private Crohns getTestObject() throws Exception {
        Crohns crohns = new Crohns();

        crohns.setNhsno("1234567890");
        crohns.setSymptomDate(new Date());
        crohns.setAbdominalPain(AbdominalPain.MODERATE.getId());
        crohns.setOpenBowels(5);
        crohns.setFeeling(Feeling.POOR.getId());
        crohns.setComplications(Complication.APTHOUS_ULCERS.getId());
        crohns.setMassInTummy(MassInTummy.DEFINITE.getId());

        return crohns;
    }

}
