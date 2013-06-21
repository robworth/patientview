package org.patientview.radar.test.dao;

import org.patientview.radar.dao.PathologyDao;
import org.patientview.radar.model.sequenced.Pathology;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class PathologyDaoTest extends BaseDaoTest {

    @Autowired
    private PathologyDao pathologyDao;

    @Test
    public void testSavePathology() throws Exception {
        // test save
        Pathology pathology = new Pathology();
        pathologyDao.savePathology(pathology);
        assertNotNull(pathology.getId());

        // test update
        Pathology pathologyUpdate = new Pathology();
        pathologyUpdate.setId(new Long(1));
        pathologyDao.savePathology(pathologyUpdate);
    }

    @Test
    public void testGetPathology() {
        Pathology pathology = pathologyDao.getPathology(2L);
        assertNotNull("Pathology object was null", pathology);
        assertEquals("Wrong ID", new Long(2), pathology.getId());
    }

    @Test
    public void testGetPathologyUnknownId() {
        Pathology pathology = pathologyDao.getPathology(223233L);
        assertNull("Pathology object wasn't null", pathology);
    }

    @Test
    public void testGetPathologyByRadarNumber() {
        List<Pathology> pathologyList = pathologyDao.getPathologyByRadarNumber(238L);
        assertNotNull("Pathology list was null", pathologyList);
        assertTrue("Pathology list was empty", !pathologyList.isEmpty());
    }

}
