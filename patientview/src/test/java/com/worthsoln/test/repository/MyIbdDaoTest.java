package com.worthsoln.test.repository;

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
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.inject.Inject;


import java.util.ArrayList;
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
        assertEquals("Weight not persisted", checkMyIbd.getWeight(), myIbd.getWeight());
        assertEquals("Body part affected not persisted", checkMyIbd.getBodyPartAffected(), myIbd.getBodyPartAffected());
        assertEquals("Family history not persisted", checkMyIbd.getFamilyHistory(), myIbd.getFamilyHistory());
        assertEquals("Smoking not persisted", checkMyIbd.getSmoking(), myIbd.getSmoking());
        assertEquals("Surgery not persisted", checkMyIbd.getSurgery(), myIbd.getSurgery());
        assertEquals("Vaccination record not persisted", checkMyIbd.getVaccinationRecord(), myIbd.getVaccinationRecord());
        assertEquals("Complications not persisted", checkMyIbd.getComplications().size(), myIbd.getComplications().size());
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
        myIbd.setDiagnosis(Diagnosis.COLITIS_UNSPECIFIED);
        myIbd.setDiseaseExtent(DiseaseExtent.ILEO_COLONIC_DISEASE);
        myIbd.setWeight(12.0);
        myIbd.setBodyPartAffected(BodyPartAffected.ANKYLOSING_SPONDYLITIS);
        myIbd.setFamilyHistory(FamilyHistory.NONE);
        myIbd.setSmoking(Smoking.CURRENT_SMOKER);
        myIbd.setSurgery(Surgery.DOUBLE_BALLOON_ENTEROSTOMY);
        myIbd.setVaccinationRecord(VaccinationRecord.PREVIOUS_HEP_B_VACCINATION);

        List<Complication> complications = new ArrayList<Complication>();
        complications.add(Complication.ABSCESS);
        complications.add(Complication.GASTRO_DUODENAL_CROHNS);

        myIbd.setComplications(complications);

        return myIbd;
    }
}
