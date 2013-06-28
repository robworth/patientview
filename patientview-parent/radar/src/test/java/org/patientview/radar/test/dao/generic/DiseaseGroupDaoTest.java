package org.patientview.radar.test.dao.generic;


import org.patientview.radar.dao.generic.DiseaseGroupDao;
import org.patientview.radar.model.generic.DiseaseGroup;
import org.patientview.radar.test.dao.BaseDaoTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DiseaseGroupDaoTest extends BaseDaoTest {

    @Autowired
    private DiseaseGroupDao diseaseGroupDao;

    @Test
    public void testGetAll()throws Exception {
        List<DiseaseGroup> diseaseGroups = diseaseGroupDao.getAll();
        Assert.assertEquals("returned list of wrong size", 3, diseaseGroups.size());
    }

    @Test
    public void testGetById() throws Exception {
        DiseaseGroup diseaseGroup = diseaseGroupDao.getById("1");
        Assert.assertNotNull(diseaseGroup);
    }
}
