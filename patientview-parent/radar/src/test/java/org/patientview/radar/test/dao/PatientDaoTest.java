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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.patientview.model.Patient;
import org.patientview.radar.dao.PatientDao;
import org.patientview.radar.dao.UserDao;
import org.patientview.radar.dao.UtilityDao;
import org.patientview.radar.test.TestPvDbSchema;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;
import java.util.List;

/**
 * User: james@solidstategroup.com
 * Date: 06/11/13
 * Time: 10:15
 */
@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class PatientDaoTest extends TestPvDbSchema {

    @Inject
    private PatientDao patientDao;

    @Inject
    UtilityDao utilityDao;

    @Inject
    UserDao userDao;

    /**
     * Get to retrieve records (SQL syntax test bascially). TODO embellish with actual data!
     *
     */
    @Test
    public void testGetPatientsByNhsNo(){

        List<Patient> patientList = patientDao.getPatientsByNhsNumber("test");


    }

}
