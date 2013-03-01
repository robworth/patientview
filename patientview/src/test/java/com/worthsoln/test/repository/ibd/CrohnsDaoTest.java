package com.worthsoln.test.repository.ibd;

import com.worthsoln.ibd.model.symptoms.CrohnsSymptoms;
import com.worthsoln.ibd.model.enums.Feeling;
import com.worthsoln.ibd.model.enums.crohns.AbdominalPain;
import com.worthsoln.ibd.model.enums.crohns.Complication;
import com.worthsoln.ibd.model.enums.crohns.MassInTummy;
import com.worthsoln.repository.ibd.CrohnsSymptomsDao;
import com.worthsoln.test.repository.BaseDaoTest;
import org.junit.Test;

import javax.inject.Inject;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CrohnsDaoTest extends BaseDaoTest {

    @Inject
    private CrohnsSymptomsDao crohnsSymptomsDao;

    @Test
    public void testAddGetCrohns() throws Exception {

        CrohnsSymptoms crohnsSymptoms = getTestObject();

        crohnsSymptomsDao.save(crohnsSymptoms);

        assertTrue("Invalid id for new crohnsSymptoms", crohnsSymptoms.getId() > 0);

        CrohnsSymptoms checkCrohnsSymptoms = crohnsSymptomsDao.get(crohnsSymptoms.getId());

        assertNotNull(checkCrohnsSymptoms);
        assertEquals("NHS no not persisted", checkCrohnsSymptoms.getNhsno(), crohnsSymptoms.getNhsno());
        assertEquals("CrohnsSymptoms date not persisted", checkCrohnsSymptoms.getSymptomDate(), crohnsSymptoms.getSymptomDate());
        assertEquals("CrohnsSymptoms abdominal pain not persisted", checkCrohnsSymptoms.getAbdominalPain(), crohnsSymptoms.getAbdominalPain());
        assertEquals("CrohnsSymptoms open bowels not persisted", checkCrohnsSymptoms.getOpenBowels(), crohnsSymptoms.getOpenBowels());
        assertEquals("CrohnsSymptoms feeling not persisted", checkCrohnsSymptoms.getFeeling(), crohnsSymptoms.getFeeling());
        assertEquals("CrohnsSymptoms complications not persisted", checkCrohnsSymptoms.getComplication(), crohnsSymptoms.getComplication());
        assertEquals("CrohnsSymptoms mass in tummy not persisted", checkCrohnsSymptoms.getMassInTummy(), crohnsSymptoms.getMassInTummy());

    }

    private CrohnsSymptoms getTestObject() throws Exception {
        CrohnsSymptoms crohnsSymptoms = new CrohnsSymptoms();

        crohnsSymptoms.setNhsno("1234567890");
        crohnsSymptoms.setSymptomDate(new Date());
        crohnsSymptoms.setAbdominalPain(AbdominalPain.MODERATE);
        crohnsSymptoms.setOpenBowels(5);
        crohnsSymptoms.setFeeling(Feeling.POOR);
        crohnsSymptoms.setComplication(Complication.NONE);
        crohnsSymptoms.setMassInTummy(MassInTummy.DEFINITE);

        return crohnsSymptoms;
    }

}
