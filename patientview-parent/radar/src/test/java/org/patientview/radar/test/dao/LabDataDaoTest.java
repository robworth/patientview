package org.patientview.radar.test.dao;

import org.patientview.radar.dao.LabDataDao;
import org.patientview.radar.model.sequenced.LabData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class LabDataDaoTest extends BaseDaoTest {

    @Autowired
    private LabDataDao labDataDao;

    @Test
    public void testSaveLabData () {

       // test creating a new object
       LabData labData = new LabData();
       labDataDao.saveLabData(labData);
       assertNotNull(labData.getId());

       // test update
       LabData labData_update = new LabData();
       labData_update.setId(new Long(16));
       labDataDao.saveLabData(labData_update);
    }

    @Test
    public void getLabData() {
        // We have a lab data with ID 16 in the test dataset
        LabData labData = labDataDao.getLabData(16L);

        assertNotNull("Lab data object was null", labData);
        assertEquals("Wrong ID", new Long(16), labData.getId());
    }

    @Test
    public void getLabDataUnknown() {
        LabData labData = labDataDao.getLabData(1236L);
        assertNull("Lab data object was not null", labData);
    }

    @Test
    public void getLabDataByRadarNumber() {
        List<LabData> labDatas = labDataDao.getLabDataByRadarNumber(236L);
        assertNotNull("Lab data list was null querying by radar number", labDatas);

        // Should be two results in our test dataset
        assertEquals("Wrong size for list", 2, labDatas.size());
    }

}
