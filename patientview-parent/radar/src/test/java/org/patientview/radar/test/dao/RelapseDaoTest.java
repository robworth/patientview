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

import org.junit.Ignore;
import org.patientview.radar.dao.RelapseDao;
import org.patientview.radar.model.sequenced.Relapse;
import org.junit.Test;
import org.patientview.radar.test.TestDataHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RelapseDaoTest extends BaseDaoTest {

    @Autowired
    private RelapseDao relapseDao;

    @Inject
    private TestDataHelper testDataHelper;

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
        testDataHelper.createRelapse();
        Relapse relapse = relapseDao.getRelapse(new Long(1));
        assertNotNull(relapse);

        relapseDao.deleteRelapse(relapse);
        relapse = relapseDao.getRelapse(new Long(1));
        assertNull(relapse);
    }

    @Test
    public void getLabData() {
        // We have a lab data with ID 16 in the test dataset
        testDataHelper.createRelapse();
        Relapse relapse = relapseDao.getRelapse(4L);
        assertNotNull("Relapse object was null", relapse);

        // Check IDs
        assertEquals("Wrong ID", new Long(4), relapse.getId());
        assertEquals("Wrong radar number", new Long(237), relapse.getRadarNumber());
    }

    @Test
    public void getLabDataByRadarNumber() {
        testDataHelper.createRelapse();
        List<Relapse> relapses = relapseDao.getRelapsesByRadarNumber(237L);
        assertNotNull("Lab data list was null querying by radar number", relapses);

        // Should be 3 results
        assertEquals("Wrong number results", 3, relapses.size());
    }


}
