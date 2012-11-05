package com.worthsoln.test.repository.ibd;

import com.worthsoln.ibd.model.MyIbd;
import com.worthsoln.ibd.model.enums.BodyPartAffected;
import com.worthsoln.ibd.model.enums.Complication;
import com.worthsoln.ibd.model.enums.Diagnosis;
import com.worthsoln.ibd.model.enums.DiseaseExtent;
import com.worthsoln.ibd.model.enums.FamilyHistory;
import com.worthsoln.ibd.model.enums.Smoking;
import com.worthsoln.ibd.model.enums.Surgery;
import com.worthsoln.ibd.model.enums.VaccinationRecord;
import com.worthsoln.repository.ibd.MyIbdDao;
import com.worthsoln.test.repository.BaseDaoTest;
import org.junit.Test;

import javax.inject.Inject;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MyIbdDaoTest extends BaseDaoTest {

    @Inject
    private MyIbdDao myIbdDao;

    @Test
    public void testAddGetMyIbd() throws Exception {
        MyIbd myIbd = getTestObject();

        myIbdDao.save(myIbd);

        assertTrue("Invalid id for new ibd", myIbd.getId() > 0);

        MyIbd checkMyIbd = myIbdDao.get(myIbd.getId());

        assertNotNull(checkMyIbd);
        assertEquals("Diagnosis not persisted", checkMyIbd.getDiagnosis(), myIbd.getDiagnosis());
        assertEquals("Disease extent not persisted", checkMyIbd.getDiseaseExtent(), myIbd.getDiseaseExtent());
        assertEquals("Year of diagnosis not persisted", checkMyIbd.getYearOfDiagnosis(), myIbd.getYearOfDiagnosis());
        assertEquals("Body part affected not persisted", checkMyIbd.getBodyPartAffected(), myIbd.getBodyPartAffected());
        assertEquals("Complications not persisted", checkMyIbd.getComplications(), myIbd.getComplications());
        assertEquals("Year for surveillance colonoscopy not persisted", checkMyIbd.getYearForSurveillanceColonoscopy(),
                myIbd.getYearForSurveillanceColonoscopy());
        assertEquals("Named consultant not persisted", checkMyIbd.getNamedConsultant(), myIbd.getNamedConsultant());
        assertEquals("Nurses not persisted", checkMyIbd.getNurses(), myIbd.getNurses());
    }

    @Test
    public void testGetByNhsNo() throws Exception {
        MyIbd myIbd = getTestObject();

        myIbdDao.save(myIbd);

        assertTrue("Invalid id for new ibd", myIbd.getId() > 0);

        MyIbd checkMyIbd = myIbdDao.get(myIbd.getNhsno());

        assertNotNull(checkMyIbd);
    }

    private MyIbd getTestObject() throws Exception {
        MyIbd myIbd = new MyIbd();

        myIbd.setNhsno("1234567890");
        myIbd.setUnitcode("unit1");
        myIbd.setDiagnosis(Diagnosis.COLITIS_UNSPECIFIED);
        myIbd.setDiseaseExtent(DiseaseExtent.ILEO_COLONIC_DISEASE);
        myIbd.setYearOfDiagnosis(new Date());
        myIbd.setBodyPartAffected("Test");
        myIbd.setYearForSurveillanceColonoscopy(new Date());
        myIbd.setNamedConsultant("Test consultant");
        myIbd.setNurses("Test nurses");

        myIbd.setComplications("Test");

        return myIbd;
    }
}
