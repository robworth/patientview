package com.solidstategroup.radar.test.dao;

import com.solidstategroup.radar.dao.LabDataDao;
import com.solidstategroup.radar.model.sequenced.LabData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LabDataDaoTest extends BaseDaoTest {

    @Autowired
    private LabDataDao labDataDao;

    @Test
    public void getLabData() {
        // We have a lab data with ID 16 in the test dataset
        LabData labData = labDataDao.getLabData(16L);

        assertNotNull("Lab data object was null", labData);
    }

    @Test
    public void getLabDataByRadarNumber() {
        List<LabData> labDatas = labDataDao.getLabDataByRadarNumber(236L);
        assertNotNull("Lab data list was null querying by radar number", labDatas);

        // Should be two results in our test dataset
        assertEquals("Wrong size for list", 2, labDatas.size());
    }

}
