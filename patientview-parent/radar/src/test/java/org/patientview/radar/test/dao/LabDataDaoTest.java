/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.radar.test.dao;

import org.junit.Before;
import org.junit.Ignore;
import org.patientview.radar.dao.LabDataDao;
import org.patientview.radar.model.sequenced.LabData;
import org.junit.Test;
import org.patientview.radar.test.TestDataHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class LabDataDaoTest extends BaseDaoTest {

    @Autowired
    private LabDataDao labDataDao;

    @Inject
    private TestDataHelper testDataHelper;

    @Before
    public void setUp() {
        testDataHelper.createLabData();
    }

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
