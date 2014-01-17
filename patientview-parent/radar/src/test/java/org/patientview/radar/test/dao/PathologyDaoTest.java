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
import org.patientview.radar.dao.PathologyDao;
import org.patientview.radar.model.sequenced.Pathology;
import org.junit.Test;
import org.patientview.radar.test.TestDataHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class PathologyDaoTest extends BaseDaoTest {

    @Autowired
    private PathologyDao pathologyDao;

    @Inject
    private TestDataHelper testDataHelper;

    @Test
    public void testSavePathology() throws Exception {
        // test save
        Pathology pathology = new Pathology();
        pathologyDao.savePathology(pathology);
        assertNotNull(pathology.getId());

        // test update
        Pathology pathologyUpdate = new Pathology();
        pathologyUpdate.setId(new Long(1));
        pathologyDao.savePathology(pathologyUpdate);
    }

    @Test
    public void testGetPathology() {
        testDataHelper.createPathology();
        Pathology pathology = pathologyDao.getPathology(2L);
        assertNotNull("Pathology object was null", pathology);
        assertEquals("Wrong ID", new Long(2), pathology.getId());
    }

    @Test
    public void testGetPathologyUnknownId() {
        Pathology pathology = pathologyDao.getPathology(223233L);
        assertNull("Pathology object wasn't null", pathology);
    }

    @Test
    public void testGetPathologyByRadarNumber() {
        testDataHelper.createPathology();
        List<Pathology> pathologyList = pathologyDao.getPathologyByRadarNumber(238L);
        assertNotNull("Pathology list was null", pathologyList);
        assertTrue("Pathology list was empty", !pathologyList.isEmpty());
    }

}
