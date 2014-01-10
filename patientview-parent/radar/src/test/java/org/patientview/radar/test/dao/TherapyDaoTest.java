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
