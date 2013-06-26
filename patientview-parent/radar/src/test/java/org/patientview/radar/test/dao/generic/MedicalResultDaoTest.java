package org.patientview.radar.test.dao.generic;

import org.patientview.radar.dao.generic.DiseaseGroupDao;
import org.patientview.radar.dao.generic.MedicalResultDao;
import org.patientview.radar.model.generic.DiseaseGroup;
import org.patientview.radar.model.generic.MedicalResult;
import org.patientview.radar.test.dao.BaseDaoTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class MedicalResultDaoTest extends BaseDaoTest {

    @Autowired
    private MedicalResultDao medicalResultDao;

    @Autowired
    DiseaseGroupDao diseaseGroupDao;

    @Test
    public void testSave() throws Exception {
        //save new record
        Date date = new Date();

        MedicalResult medicalResult = new MedicalResult();
        medicalResult.setNhsNo("123456789");
        medicalResult.setRadarNo(1L);
        medicalResult.setSerumCreatanine(10.25);
        medicalResult.setAntihypertensiveDrugs(MedicalResult.YesNo.YES);
        medicalResult.setAntihypertensiveDrugsDate(date);
        medicalResult.setBloodUrea(12.25);
        medicalResult.setBloodUreaDate(date);
        medicalResult.setSerumCreatanine(15.5);
        medicalResult.setCreatanineDate(date);
        medicalResult.setBpDiastolic(18);
        medicalResult.setBpSystolic(19);
        medicalResult.setHeight(100.5);
        medicalResult.setHeightDate(date);
        medicalResult.setWeight(122.0);
        medicalResult.setWeightDate(date);
        medicalResult.setBpDate(date);
        medicalResult.setPcr(1);
        medicalResult.setPcrDate(date);
        medicalResult.setAcr(1);
        medicalResult.setAcrDate(date);

        DiseaseGroup diseaseGroup = diseaseGroupDao.getById("1");
        medicalResult.setDiseaseGroup(diseaseGroup);

        medicalResultDao.save(medicalResult);

        medicalResult = medicalResultDao.getMedicalResult(1L, diseaseGroup.getId());

        Assert.assertNotNull("Medical result should not be null", medicalResult);

        // update record
        medicalResult.setBloodUrea(15.5);
        medicalResult.setNhsNo("123456789");
        medicalResultDao.save(medicalResult);

        medicalResult = medicalResultDao.getMedicalResult(1L, diseaseGroup.getId());
        Assert.assertEquals("Blood urea has wrong value", new Double(15.5), medicalResult.getBloodUrea());
    }
}
