package org.patientview.radar.test.dao;

import org.patientview.radar.dao.RelapseDao;
import org.patientview.radar.model.sequenced.Relapse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RelapseDaoTest extends BaseDaoTest {

    @Autowired
    private RelapseDao relapseDao;

    @Test
    public void testSaveRelapse() throws Exception {
        // save
        Relapse relapse = new Relapse();
        relapseDao.saveRelapse(relapse);
        assertNotNull(relapse.getId());

        // update
        Relapse relapseUpdate = new Relapse();
        relapseUpdate.setId(new Long(1));
        relapseDao.saveRelapse(relapseUpdate);
    }

    @Test
    public void testDeleteRelapse() throws Exception {
        Relapse relapse = relapseDao.getRelapse(new Long(1));
        assertNotNull(relapse);

        relapseDao.deleteRelapse(relapse);
        relapse = relapseDao.getRelapse(new Long(1));
        assertNull(relapse);
    }

    @Test
    public void getLabData() {
        // We have a lab data with ID 16 in the test dataset
        Relapse relapse = relapseDao.getRelapse(4L);
        assertNotNull("Relapse object was null", relapse);

        // Check IDs
        assertEquals("Wrong ID", new Long(4), relapse.getId());
        assertEquals("Wrong radar number", new Long(237), relapse.getRadarNumber());
    }

    @Test
    public void getLabDataByRadarNumber() {
        List<Relapse> relapses = relapseDao.getRelapsesByRadarNumber(237L);
        assertNotNull("Lab data list was null querying by radar number", relapses);

        // Should be 3 results
        assertEquals("Wrong number results", 3, relapses.size());
    }


}
