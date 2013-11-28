package org.patientview.radar.test.dao;

import org.junit.Ignore;
import org.patientview.radar.dao.TherapyDao;
import org.patientview.radar.model.sequenced.Therapy;
import org.junit.Test;
import org.patientview.radar.test.TestDataHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TherapyDaoTest extends BaseDaoTest {

    @Autowired
    private TherapyDao therapyDao;

    @Inject
    private TestDataHelper testDataHelper;

    @Test
    public void testSaveTherapy() throws Exception {
        // save new
        Therapy therapy = new Therapy();
        therapy.setAceInhibitor(false);
        therapyDao.saveTherapy(therapy);
        assertNotNull(therapy.getId());

        // update
        Therapy therapy_update = new Therapy();
        therapy_update.setId(new Long(3));
        therapyDao.saveTherapy(therapy_update);
    }

    @Test
    public void testGetTherapy() {
        testDataHelper.createTherapy();
        Therapy therapy = therapyDao.getTherapy(6L);
        assertNotNull("Therapy object was null", therapy);
    }

    @Test
    public void testGetTherapyUnknownId() {
        Therapy therapy = therapyDao.getTherapy(69787L);
        assertNull("Therapy object was null", therapy);
    }

    @Test
    public void testGetTherapyByRadarNumber() {
        testDataHelper.createTherapy();
        List<Therapy> therapy = therapyDao.getTherapyByRadarNumber(6L);
        assertNotNull("Therapy object was null", therapy);
    }
}
