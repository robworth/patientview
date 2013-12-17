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

package org.patientview.radar.test.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.patientview.model.Patient;
import org.patientview.radar.dao.UtilityDao;
import org.patientview.radar.service.PatientManager;
import org.patientview.radar.test.TestPvDbSchema;
import org.patientview.radar.test.roles.unitadmin.RoleHelper;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;

/**
 * User: james@solidstategroup.com
 * Date: 05/12/13
 * Time: 15:13
 */
@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class PatientManagerTest extends TestPvDbSchema {

    private final static String testDiseaseUnit = "Allports";
    private final static String testRenalUnit = "RENALA";

    @Inject
    RoleHelper roleHelper;

    @Inject
    UtilityDao utilityDao;

    @Inject
    PatientManager patientManager;

    @Before
    public void setup() {
        // Setup a Renal Unit and Disease Group
        try {
            utilityDao.createUnit(testRenalUnit);
            utilityDao.createUnit(testDiseaseUnit);
        } catch (Exception e) {

        }
    }

    /**
     * Create a patient and then requery it
     *
     */
    @Test
    public void testGetById() throws Exception {
        Patient patient = roleHelper.createPatient("564564564" , testRenalUnit, testDiseaseUnit);

        patientManager.save(patient);


        Patient returnPatient = patientManager.getById(patient.getId());

        Assert.assertNotNull("There must be a patient returned", returnPatient);
        Assert.assertTrue("The two patients must have the same id", patient.getId().equals(patient.getId()));

    }


}
