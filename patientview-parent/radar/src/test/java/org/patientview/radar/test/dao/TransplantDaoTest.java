package org.patientview.radar.test.dao;

import org.junit.Before;
import org.junit.Ignore;
import org.patientview.radar.dao.TransplantDao;
import org.patientview.radar.model.Transplant;
import junit.framework.Assert;
import org.junit.Test;
import org.patientview.radar.test.TestDataHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TransplantDaoTest extends BaseDaoTest {

    @Autowired
    private TransplantDao transplantDao;

    @Inject
    private TestDataHelper testDataHelper;

    @Test
    public void testSaveTransplant() {
        // save
        Transplant transplant = new Transplant();
        transplant.setRecurr(false);
        transplantDao.saveTransplant(transplant);
        assertNotNull(transplant.getId());

        // update
        Transplant transplant_update = new Transplant();
        transplant_update.setId(new Long(1));
        transplantDao.saveTransplant(transplant_update);
    }

    @Test
    public void testDeleteTransplant() {
        testDataHelper.createTransplant();
        Transplant transplant = transplantDao.getTransplant(new Long(2));
        assertNotNull(transplant);

        transplantDao.deleteTransplant(transplant);
        transplant = transplantDao.getTransplant(new Long(2));
        assertNull(transplant);
    }


    @Test
    public void testGetTransplant() {
        testDataHelper.createTransplant();
        Transplant transplant = transplantDao.getTransplant(4L);
        assertNotNull("Transplant was null", transplant);
        assertEquals("Wrong ID on transplant", new Long(4), transplant.getId());
    }

    @Test
    public void testGetTransplantUnknown() {
        testDataHelper.createTransplant();
        Transplant transplant = transplantDao.getTransplant(2324L);
        assertNull("Transplant was not null", transplant);
    }

    @Test
    public void testGetTransplantByRadarNumber() {
        testDataHelper.createTransplant();
        List<Transplant> transplants = transplantDao.getTransplantsByRadarNumber(new Long(219));
        assertNotNull("Transplants list was null", transplants);
        assertTrue(!transplants.isEmpty()
        );
    }

    @Test
    public void testGetTransplantModalitites() {
        testDataHelper.createTransplantModality();
        List<Transplant.Modality> modalities = transplantDao.getTransplantModalitites();
        assertTrue(!modalities.isEmpty());
    }

    @Test
    public void testGetTransplantModality() {
        testDataHelper.createTransplantModality();
        Transplant.Modality modality = transplantDao.getTransplantModality(new Long(20));
        Assert.assertNotNull(modality);
    }

    @Test
    public void saveRejectData() {
        // save
        Transplant.RejectData rejectData = new Transplant.RejectData();
        rejectData.setTransplantId(new Long(15));
        transplantDao.saveRejectData(rejectData);
        assertNotNull(rejectData.getId());

        // update
        Transplant.RejectData rejectData_update = new Transplant.RejectData();
        rejectData_update.setId(new Long(1));
        rejectData_update.setTransplantId(new Long(15));
        transplantDao.saveRejectData(rejectData_update);
    }

    @Test
    public void deleteRejectData() {
        testDataHelper.createTransplantReject();
        Transplant.RejectData rejectData = transplantDao.getRejectData(new Long(2));
        assertNotNull(rejectData);

        transplantDao.deleteRejectData(rejectData);
        rejectData = transplantDao.getRejectData(new Long(2));
        assertNull(rejectData);
    }

    @Test
    public void getRejectDataByTransplantNumber() {
        testDataHelper.createTransplantReject();
        List<Transplant.RejectData> rejectDatas = transplantDao.getRejectDataByTransplantNumber(new Long(25));
        assertNotNull("Reject data list was null", rejectDatas);
        assertTrue(!rejectDatas.isEmpty());
    }

    @Test
    public void getRejectData() {
        testDataHelper.createTransplantReject();
        Transplant.RejectData rejectData = transplantDao.getRejectData(new Long(2));
        assertNotNull(rejectData);
    }
}
