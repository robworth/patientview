package org.patientview.radar.test.dao.generic;

import org.patientview.radar.dao.generic.GenericDiagnosisDao;
import org.patientview.radar.model.generic.DiseaseGroup;
import org.patientview.radar.model.generic.GenericDiagnosis;
import org.patientview.radar.test.dao.BaseDaoTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GenericDiagnosisDaoTest extends BaseDaoTest {

    @Autowired
    private GenericDiagnosisDao genericDiagnosisDao;

    @Test
    public void testGetAll() throws Exception {
        List<GenericDiagnosis> genericDiagnosises = genericDiagnosisDao.getAll();
        Assert.assertEquals("Returned list of wrong size", 6, genericDiagnosises.size());
    }

    @Test
    public void testGetByDiseaseGroup() throws Exception {
        DiseaseGroup diseaseGroup = new DiseaseGroup();
        diseaseGroup.setId("1");
        List<GenericDiagnosis> genericDiagnosises = genericDiagnosisDao.getByDiseaseGroup(diseaseGroup);
        Assert.assertEquals("Returned list of wrong size", 2, genericDiagnosises.size());
        Assert.assertEquals("Returned generic diagnosis with wrong id", "code6", genericDiagnosises.get(0).getId());
        Assert.assertEquals("Returned generic diagnosis with wrong id", "code5", genericDiagnosises.get(1).getId());

        // test ordering
        Assert.assertEquals("Returned generic diagnosis with wrong ordering", new Integer(5),
                genericDiagnosises.get(0).getOrder());

        Assert.assertEquals("Returned generic diagnosis with wrong ordering", new Integer(6),
                genericDiagnosises.get(1).getOrder());
    }

    @Test
    public void testGetById() throws Exception {
        GenericDiagnosis genericDiagnosis = genericDiagnosisDao.get("code1", "3");
        Assert.assertEquals("Returned generic diagnosis has wrong id", "code1", genericDiagnosis.getId());
    }
}
