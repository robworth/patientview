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
import org.patientview.radar.dao.ClinicalDataDao;
import org.patientview.radar.model.Phenotype;
import org.patientview.radar.model.sequenced.ClinicalData;
import org.junit.Test;
import org.patientview.radar.test.TestDataHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ClinicalDataDaoTest extends BaseDaoTest {

    @Autowired
    private ClinicalDataDao clinicalDataDao;

    @Inject
    private TestDataHelper testDataHelper;

    @Before
    public void setUp() {
        testDataHelper.createClinicalData();
        testDataHelper.createPhenotypes();
    }

    @Test
    public void testSaveClinicalData() {

        ClinicalData clinicalData = new ClinicalData();
        clinicalData.setAnaemia(false);
        clinicalData.setRadarNumber(new Long(238));
        clinicalDataDao.saveClinicalDate(clinicalData);
        assertNotNull(clinicalData.getId());

        // test update
        ClinicalData clinicalData_update = new ClinicalData();
        clinicalData_update.setId(new Long(195));
        clinicalData_update.setRadarNumber(new Long(238));
        clinicalData_update.setAnaemia(true);
        clinicalDataDao.saveClinicalDate(clinicalData_update);
    }

    @Test
    public void testGetClinicalData() {
        ClinicalData clinicalData = clinicalDataDao.getClinicalData(135L);
        assertNotNull("Clinical data is null for ID", clinicalData);
    }

    @Test
    public void testGetClinicalDataUnknown() {
        ClinicalData clinicalData = clinicalDataDao.getClinicalData(132325L);
        assertNull("Clinical data is not null for ID", clinicalData);
    }

    @Test
    public void testGetClinicalDataByRadarNumber() {
        List<ClinicalData> clinicalDatas = clinicalDataDao.getClinicalDataByRadarNumber(244L);
        assertNotNull("List of clinical datas was null", clinicalDatas);
    }

    @Test
    public void testGetPhenotype() {
        Phenotype phenotype = clinicalDataDao.getPhenotype(4L);
        assertNotNull("Phenotype was null", phenotype);
        assertEquals("Phenotype had wrong description", "Blindness", phenotype.getDescription());
        assertEquals("Phenotype had ID wrong", new Long(4L), phenotype.getId());
    }

    @Test
    public void testGetPhenotypes() {
        List<Phenotype> phenotypes = clinicalDataDao.getPhenotypes();
        assertNotNull("Phenotypes list was null", phenotypes);
        assertTrue("Phenotypes list was empty", phenotypes.size() > 0);
    }

}
