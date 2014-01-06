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
import org.patientview.radar.dao.TreatmentDao;
import org.patientview.radar.model.Treatment;
import org.patientview.radar.model.TreatmentModality;
import org.junit.Test;
import org.patientview.radar.test.TestDataHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TreatmentDaoTest extends BaseDaoTest {

    @Autowired
    private TreatmentDao treatmentDao;

    @Inject
    private TestDataHelper testDataHelper;

    @Before
    public void setUp() {
        testDataHelper.createRRTTreatment();
    }

    @Test
    public void testSaveTreatment() throws Exception {
        // save
        Treatment  treatment = new Treatment();
        treatmentDao.saveTreatment(treatment);
        assertNotNull(treatment.getId());

        // update
        Treatment treatmentUpdate = new Treatment();
        treatmentUpdate.setId(new Long(2));
        treatmentDao.saveTreatment(treatmentUpdate);
    }

    @Test
    public void testDeleteTreatment() throws Exception {
        Treatment treatment =
                treatmentDao.getTreatment(new Long(2));
        assertNotNull(treatment);

        treatmentDao.deleteTreatment(treatment);
        treatment = treatmentDao.getTreatment(new Long(2));
        assertNull(treatment);
    }

    @Test
    public void testGetTreatment() {
        Treatment treatment = treatmentDao.getTreatment(10L);
        assertNotNull("Treatment was null", treatment);
        assertEquals("Wrong ID", new Long(10), treatment.getId());
    }

    @Test
    public void testGetTreatmentUnknown() {
        Treatment treatment = treatmentDao.getTreatment(10232L);
        assertNull("Treatment was not null", treatment);
    }

    @Test
    public void testGetTreatmentsByRadarNumber() {
        List<Treatment> treatments = treatmentDao.getTreatmentsByRadarNumber(237L);
        assertNotNull("Treatments list was null", treatments);
        assertEquals("Wrong size", 3, treatments.size());
    }

    @Test
    public void testGetTreatmentModalities() throws Exception {
        testDataHelper.createRRTModality();
        List<TreatmentModality> treatmentModalities = treatmentDao.getTreatmentModalities();
        assertNotNull("List was null", treatmentModalities);
        assertEquals("Wrong size", 15, treatmentModalities.size());
    }
}
