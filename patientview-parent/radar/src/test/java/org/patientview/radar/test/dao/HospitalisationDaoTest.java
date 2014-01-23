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
import org.patientview.radar.dao.HospitalisationDao;
import org.patientview.radar.model.Hospitalisation;
import org.junit.Test;
import org.patientview.radar.test.TestDataHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class HospitalisationDaoTest extends BaseDaoTest {

    @Autowired
    private HospitalisationDao hospitalisationDao;

    @Inject
    private TestDataHelper testDataHelper;

    @Test
    public void testSaveHospitilisation() throws Exception {

        // save
        Hospitalisation hospitalisation = new Hospitalisation();
        hospitalisationDao.saveHospitilsation(hospitalisation);
        assertNotNull(hospitalisation.getId());

        // update
        Hospitalisation hospitalisationUpdate = new Hospitalisation();
        hospitalisationUpdate.setId(new Long(1));
        hospitalisationDao.saveHospitilsation(hospitalisationUpdate);
    }

    @Test
    public void testGetHospitalisation() {
        testDataHelper.createHospitalisation();
        Hospitalisation hospitalisation = hospitalisationDao.getHospitalisation(3L);
        assertNotNull("Hospitalisation was null", hospitalisation);
        assertEquals("Wrong ID", new Long(3), hospitalisation.getId());
    }

    @Test
    public void testGetHospitalisationUnknown() {
        Hospitalisation hospitalisation = hospitalisationDao.getHospitalisation(3232L);
        assertNull("Hospitalisation null", hospitalisation);
    }

    @Test
    public void testGetHospitalisationByRadarNumber() {
        testDataHelper.createHospitalisation();
        List<Hospitalisation> hospitalisations = hospitalisationDao.getHospitalisationsByRadarNumber(250L);
        assertNotNull("Hospitalisations list was null");
        assertEquals("Wrong size", 1, hospitalisations.size());
    }
}
