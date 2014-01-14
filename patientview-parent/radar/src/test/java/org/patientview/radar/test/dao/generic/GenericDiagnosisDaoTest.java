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
import org.junit.Ignore;
import org.patientview.model.Centre;
import org.patientview.model.generic.DiseaseGroup;
import org.patientview.model.generic.GenericDiagnosis;
import org.patientview.radar.dao.generic.GenericDiagnosisDao;
import org.patientview.radar.test.TestDataHelper;
import org.patientview.radar.test.dao.BaseDaoTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.List;

public class GenericDiagnosisDaoTest extends BaseDaoTest {

    @Autowired
    private GenericDiagnosisDao genericDiagnosisDao;

    @Inject
    private TestDataHelper testDataHelper;

    @Before
    public void setUp() throws Exception {
        testDataHelper.createPrdCode();
        testDataHelper.createDiagnosisMapping();
    }

    @Test
    public void testGetAll() throws Exception {
        List<GenericDiagnosis> genericDiagnosises = genericDiagnosisDao.getAll();
        Assert.assertEquals("Returned list of wrong size", 6, genericDiagnosises.size());
    }

    @Test
    public void testGetByDiseaseGroup() throws Exception {
        DiseaseGroup diseaseGroup = new DiseaseGroup();
        diseaseGroup.setId("1");
        List<GenericDiagnosis> genericDiagnosises = genericDiagnosisDao.getByDiseaseGroup(diseaseGroup);
        Assert.assertEquals("Returned list of wrong size", 2, genericDiagnosises.size());
        Assert.assertEquals("Returned generic diagnosis with wrong id", "code6", genericDiagnosises.get(0).getId());
        Assert.assertEquals("Returned generic diagnosis with wrong id", "code5", genericDiagnosises.get(1).getId());

        // test ordering
        Assert.assertEquals("Returned generic diagnosis with wrong ordering", new Integer(5),
                genericDiagnosises.get(0).getOrder());

        Assert.assertEquals("Returned generic diagnosis with wrong ordering", new Integer(6),
                genericDiagnosises.get(1).getOrder());
    }

    @Test
    public void testGetById() throws Exception {
        GenericDiagnosis genericDiagnosis = genericDiagnosisDao.get("code1", "3");
        Assert.assertEquals("Returned generic diagnosis has wrong id", "code1", genericDiagnosis.getId());
    }
}
