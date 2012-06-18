package com.solidstategroup.radar.test.dao.generic;


import com.solidstategroup.radar.dao.generic.MedicalResultDao;
import com.solidstategroup.radar.model.generic.MedicalResult;
import com.solidstategroup.radar.test.dao.BaseDaoTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class MedicalResultDaoTest extends BaseDaoTest {

    @Autowired
    private MedicalResultDao medicalResultDao;

    @Test
    public void testSave() throws Exception {
        // save new record
        Date date = new Date();
        MedicalResult medicalResult = new MedicalResult();
        medicalResult.setId("2");
        medicalResult.setSerumCreatanine(10.25);
        medicalResult.setAntihypertensiveDrugs(MedicalResult.YesNo.YES);
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
        medicalResultDao.save(medicalResult);

        medicalResult = medicalResultDao.getById("2");
        Assert.assertNotNull("Medical result should not be null", medicalResult);

        // update record
        medicalResult.setBloodUrea(15.5);
        medicalResultDao.save(medicalResult);
        medicalResult = medicalResultDao.getById("2");
        Assert.assertEquals("Blood urea has wrong value", new Double(15.5), medicalResult.getBloodUrea());
        // @current fix test
    }

    @Test
    public void testGetById() throws Exception {
        MedicalResult medicalResult = medicalResultDao.getById("1");
        Assert.assertNotNull(medicalResult);
    }

}
