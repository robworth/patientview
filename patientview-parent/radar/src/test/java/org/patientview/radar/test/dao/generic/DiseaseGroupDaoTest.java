package org.patientview.radar.test.dao.generic;


import org.junit.Before;
import org.junit.Ignore;
import org.patientview.model.generic.DiseaseGroup;
import org.patientview.radar.dao.generic.DiseaseGroupDao;
import org.patientview.radar.test.TestDataHelper;
import org.patientview.radar.test.dao.BaseDaoTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.List;

public class DiseaseGroupDaoTest extends BaseDaoTest {

    @Autowired
    private DiseaseGroupDao diseaseGroupDao;

    @Inject
    private TestDataHelper testDataHelper;

    @Before
    public void setUp() throws Exception {
        testDataHelper.createUnit();
    }

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
