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
import org.patientview.radar.dao.ImmunosuppressionDao;
import org.patientview.radar.model.Immunosuppression;
import org.patientview.radar.model.ImmunosuppressionTreatment;
import org.junit.Test;
import org.patientview.radar.test.TestDataHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ImmunosuppressionDaoTest extends BaseDaoTest {

    @Autowired
    private ImmunosuppressionDao immunosuppressionDao;

    @Inject
    private TestDataHelper testDataHelper;

    @Before
    public void setUp() {
        testDataHelper.createImmunsupTreatment();
        testDataHelper.createImmunoSupp();
    }

    @Test
    public void testSaveImmunoSuppressionTreatment() throws Exception {
        // save
        ImmunosuppressionTreatment immunosuppressionTreatment = new ImmunosuppressionTreatment();
        immunosuppressionTreatment.setFirstFlag(false);
        immunosuppressionDao.saveImmunosuppressionTreatment(immunosuppressionTreatment);
        assertNotNull(immunosuppressionTreatment.getId());

        // update
        ImmunosuppressionTreatment immunosuppressionTreatment_update = new ImmunosuppressionTreatment();
        immunosuppressionTreatment_update.setId(new Long(1));
        immunosuppressionDao.saveImmunosuppressionTreatment(immunosuppressionTreatment_update);
    }

    @Test
    public void testImmunosuppressionDelete() throws Exception {
        ImmunosuppressionTreatment immunosuppressionTreatment =
                immunosuppressionDao.getImmunosuppressionTreatment(new Long(1));
        assertNotNull(immunosuppressionTreatment);

        immunosuppressionDao.deleteImmunosuppressionTreatment(immunosuppressionTreatment);
        immunosuppressionTreatment = immunosuppressionDao.getImmunosuppressionTreatment(new Long(1));
        assertNull(immunosuppressionTreatment);
    }

    @Test
    public void testGetImmunosuppressionTreatment() {
        ImmunosuppressionTreatment immunosuppressionTreatment = immunosuppressionDao.getImmunosuppressionTreatment(14L);
        assertNotNull("Immunosuppression was null", immunosuppressionTreatment);
        assertEquals("Wrong ID", new Long(14), immunosuppressionTreatment.getId());
        assertEquals("Wrong radar number", new Long(218), immunosuppressionTreatment.getRadarNumber());
        assertNotNull("Immuno object was null", immunosuppressionTreatment.getImmunosuppression());
        assertEquals("Wrong immuno", "Cyclophosphamide",
                immunosuppressionTreatment.getImmunosuppression().getDescription());
    }

    @Test
    public void testGetImmunosuppressionTreatmentUnknown() {
        ImmunosuppressionTreatment immunosuppressionTreatment =
                immunosuppressionDao.getImmunosuppressionTreatment(1234L);
        assertNull("Immunosuppression was not null", immunosuppressionTreatment);
    }

    @Test
    public void testGetImmunosuppressionTreatmentByRadarNumber() {
        List<ImmunosuppressionTreatment> immunosuppressions =
                immunosuppressionDao.getImmunosuppressionTreatmentByRadarNumber(218L);
        assertNotNull("List was null", immunosuppressions);
        assertEquals("Wrong size", 5, immunosuppressions.size());
    }

    @Test
    public void testGetImmunosuppressions() throws Exception {
        List<Immunosuppression> immunosuppressions = immunosuppressionDao.getImmunosuppressions();
        assertTrue(!immunosuppressions.isEmpty());
    }
}
