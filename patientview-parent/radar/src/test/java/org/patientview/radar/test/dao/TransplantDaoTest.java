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
import org.patientview.radar.dao.TransplantDao;
import org.patientview.radar.model.Transplant;
import junit.framework.Assert;
import org.junit.Test;
import org.patientview.radar.test.TestDataHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TransplantDaoTest extends BaseDaoTest {

    @Autowired
    private TransplantDao transplantDao;

    @Inject
    private TestDataHelper testDataHelper;

    @Test
    public void testSaveTransplant() {
        // save
        Transplant transplant = new Transplant();
        transplant.setRecurr(false);
        transplantDao.saveTransplant(transplant);
        assertNotNull(transplant.getId());

        // update
        Transplant transplant_update = new Transplant();
        transplant_update.setId(new Long(1));
        transplantDao.saveTransplant(transplant_update);
    }

    @Test
    public void testDeleteTransplant() {
        testDataHelper.createTransplant();
        Transplant transplant = transplantDao.getTransplant(new Long(2));
        assertNotNull(transplant);

        transplantDao.deleteTransplant(transplant);
        transplant = transplantDao.getTransplant(new Long(2));
        assertNull(transplant);
    }


    @Test
    public void testGetTransplant() {
        testDataHelper.createTransplant();
        Transplant transplant = transplantDao.getTransplant(4L);
        assertNotNull("Transplant was null", transplant);
        assertEquals("Wrong ID on transplant", new Long(4), transplant.getId());
    }

    @Test
    public void testGetTransplantUnknown() {
        testDataHelper.createTransplant();
        Transplant transplant = transplantDao.getTransplant(2324L);
        assertNull("Transplant was not null", transplant);
    }

    @Test
    public void testGetTransplantByRadarNumber() {
        testDataHelper.createTransplant();
        List<Transplant> transplants = transplantDao.getTransplantsByRadarNumber(new Long(219));
        assertNotNull("Transplants list was null", transplants);
        assertTrue(!transplants.isEmpty()
        );
    }

    @Test
    public void testGetTransplantModalitites() {
        testDataHelper.createTransplantModality();
        List<Transplant.Modality> modalities = transplantDao.getTransplantModalitites();
        assertTrue(!modalities.isEmpty());
    }

    @Test
    public void testGetTransplantModality() {
        testDataHelper.createTransplantModality();
        Transplant.Modality modality = transplantDao.getTransplantModality(new Long(20));
        Assert.assertNotNull(modality);
    }

    @Test
    public void saveRejectData() {
        // save
        Transplant.RejectData rejectData = new Transplant.RejectData();
        rejectData.setTransplantId(new Long(15));
        transplantDao.saveRejectData(rejectData);
        assertNotNull(rejectData.getId());

        // update
        Transplant.RejectData rejectData_update = new Transplant.RejectData();
        rejectData_update.setId(new Long(1));
        rejectData_update.setTransplantId(new Long(15));
        transplantDao.saveRejectData(rejectData_update);
    }

    @Test
    public void deleteRejectData() {
        testDataHelper.createTransplantReject();
        Transplant.RejectData rejectData = transplantDao.getRejectData(new Long(2));
        assertNotNull(rejectData);

        transplantDao.deleteRejectData(rejectData);
        rejectData = transplantDao.getRejectData(new Long(2));
        assertNull(rejectData);
    }

    @Test
    public void getRejectDataByTransplantNumber() {
        testDataHelper.createTransplantReject();
        List<Transplant.RejectData> rejectDatas = transplantDao.getRejectDataByTransplantNumber(new Long(25));
        assertNotNull("Reject data list was null", rejectDatas);
        assertTrue(!rejectDatas.isEmpty());
    }

    @Test
    public void getRejectData() {
        testDataHelper.createTransplantReject();
        Transplant.RejectData rejectData = transplantDao.getRejectData(new Long(2));
        assertNotNull(rejectData);
    }
}
