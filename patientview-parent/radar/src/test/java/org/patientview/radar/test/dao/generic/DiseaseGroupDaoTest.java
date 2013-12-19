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
import org.patientview.model.generic.DiseaseGroup;
import org.patientview.radar.dao.generic.DiseaseGroupDao;
import org.patientview.radar.test.TestDataHelper;
import org.patientview.radar.test.dao.BaseDaoTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.List;

public class DiseaseGroupDaoTest extends BaseDaoTest {

    @Autowired
    private DiseaseGroupDao diseaseGroupDao;

    @Inject
    private TestDataHelper testDataHelper;

    @Before
    public void setUp() throws Exception {
        testDataHelper.createUnit();
    }

    @Test
    public void testGetAll()throws Exception {
        List<DiseaseGroup> diseaseGroups = diseaseGroupDao.getAll();
        Assert.assertEquals("returned list of wrong size", 3, diseaseGroups.size());
    }

    @Test
    public void testGetById() throws Exception {
        DiseaseGroup diseaseGroup = diseaseGroupDao.getById("1");
        Assert.assertNotNull(diseaseGroup);
    }
}
