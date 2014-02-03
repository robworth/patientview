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
import org.patientview.radar.dao.PlasmapheresisDao;
import org.patientview.radar.model.Plasmapheresis;
import org.patientview.radar.model.PlasmapheresisExchangeUnit;
import org.junit.Test;
import org.patientview.radar.test.TestDataHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class PlasmapheresisDaoTest extends BaseDaoTest {

    @Autowired
    private PlasmapheresisDao plasmapheresisDao;

    @Inject
    private TestDataHelper testDataHelper;

    @Test
    public void testSavePlasmapheresis() throws Exception {
        // save
        Plasmapheresis plasmapheresis = new Plasmapheresis();
        plasmapheresisDao.savePlasmapheresis(plasmapheresis);
        assertNotNull(plasmapheresis.getId());

        // update
        Plasmapheresis plasmapheresisUpdate = new Plasmapheresis();
        plasmapheresisUpdate.setId(new Long(1));
        plasmapheresisDao.savePlasmapheresis(plasmapheresisUpdate);
    }

    @Test
    public void testPlasmapheresisDelete() throws Exception {
        createTestData();
        Plasmapheresis plasmapheresis =
                plasmapheresisDao.getPlasmapheresis(new Long(1));
        assertNotNull(plasmapheresis);

        plasmapheresisDao.deletePlasmaPheresis(plasmapheresis);
        plasmapheresis = plasmapheresisDao.getPlasmapheresis(new Long(1));
        assertNull(plasmapheresis);
    }

    @Test
    public void testGetPlasmapheresis() {
        createTestData();
        Plasmapheresis plasmapheresis = plasmapheresisDao.getPlasmapheresis(1L);
        assertNotNull("Plasmapheresis wasn't null", plasmapheresis);
        assertEquals("Wrong ID", new Long(1), plasmapheresis.getId());
        assertEquals("Wrong radar number", new Long(218), plasmapheresis.getRadarNumber());
        assertNotNull("Null exchange", plasmapheresis.getPlasmapheresisExchanges());
        assertEquals("Wrong exchange", "x4/wk", plasmapheresis.getPlasmapheresisExchanges().getName());
    }

    @Test
    public void testGetPlasmapheresisByRadarNumber() {
        createTestData();
        List<Plasmapheresis> plasmapheresisList = plasmapheresisDao.getPlasmapheresisByRadarNumber(218L);
        assertNotNull("List was null", plasmapheresisList);
        assertEquals("Wrong size", 5, plasmapheresisList.size());

        // All the items should have right radar number
        for (Plasmapheresis plasmapheresis : plasmapheresisList) {
            assertEquals("Wrong radar number", new Long(218), plasmapheresis.getRadarNumber());
        }
    }

    @Test
    public void testGetPlasmapheresisExchangeUnits() {
        testDataHelper.createPlasmaLu();
        List<PlasmapheresisExchangeUnit> exchangeUnits = plasmapheresisDao.getPlasmapheresisExchangeUnits();
        assertNotNull("List null", exchangeUnits);
        assertEquals("List is wrong size", 8, exchangeUnits.size());
    }

    private void createTestData(){
        testDataHelper.createPlasmapheresis();
        testDataHelper.createPlasmaLu();
    }
}
