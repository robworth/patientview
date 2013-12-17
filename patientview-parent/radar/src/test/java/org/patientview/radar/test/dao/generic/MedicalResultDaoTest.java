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

package org.patientview.radar.test.dao.generic;

import org.junit.Before;
import org.patientview.model.generic.DiseaseGroup;
import org.patientview.radar.dao.generic.DiseaseGroupDao;
import org.patientview.radar.dao.generic.MedicalResultDao;
import org.patientview.radar.model.generic.MedicalResult;
import org.patientview.radar.test.TestDataHelper;
import org.patientview.radar.test.dao.BaseDaoTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.Date;

public class MedicalResultDaoTest extends BaseDaoTest {

    @Autowired
    private MedicalResultDao medicalResultDao;

    @Autowired
    DiseaseGroupDao diseaseGroupDao;

    @Inject
    private TestDataHelper testDataHelper;

    @Before
    public void setUp() {
        testDataHelper.createUnit();
    }

    @Test
    public void testSave() throws Exception {
        //save new record
        Date date = new Date();

        MedicalResult medicalResult = new MedicalResult();
        medicalResult.setNhsNo("123456789");
        medicalResult.setRadarNo(1L);
        medicalResult.setSerumCreatanine(10.25);
        medicalResult.setAntihypertensiveDrugs(MedicalResult.YesNo.YES);
        medicalResult.setAntihypertensiveDrugsDate(date);
        medicalResult.setBloodUrea(12.25);
        medicalResult.setBloodUreaDate(date);
        medicalResult.setSerumCreatanine(15.5);
        medicalResult.setCreatanineDate(date);
        medicalResult.setBpDiastolic(18);
        medicalResult.setBpSystolic(19);
        medicalResult.setHeight(100.5);
        medicalResult.setHeightDate(date);
        medicalResult.setWeight(122.0);
        medicalResult.setWeightDate(date);
        medicalResult.setBpDate(date);
        medicalResult.setPcr(1);
        medicalResult.setPcrDate(date);
        medicalResult.setAcr(1);
        medicalResult.setAcrDate(date);

        DiseaseGroup diseaseGroup = diseaseGroupDao.getById("1");
        medicalResult.setDiseaseGroup(diseaseGroup);

        medicalResultDao.save(medicalResult);

        medicalResult = medicalResultDao.getMedicalResult(1L, diseaseGroup.getId());

        Assert.assertNotNull("Medical result should not be null", medicalResult);

        // update record
        medicalResult.setBloodUrea(15.5);
        medicalResult.setNhsNo("123456789");
        medicalResultDao.save(medicalResult);

        medicalResult = medicalResultDao.getMedicalResult(1L, diseaseGroup.getId());
        Assert.assertEquals("Blood urea has wrong value", new Double(15.5), medicalResult.getBloodUrea());
    }
}
