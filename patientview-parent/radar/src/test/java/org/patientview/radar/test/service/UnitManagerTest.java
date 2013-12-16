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

import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.patientview.model.Unit;
import org.patientview.radar.model.user.User;
import org.patientview.radar.service.UnitManager;
import org.patientview.radar.test.TestDataHelper;
import org.patientview.radar.test.TestPvDbSchema;
import org.patientview.radar.test.roles.unitadmin.RoleHelper;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * User: james@solidstategroup.com
 * Date: 04/12/13
 * Time: 18:00
 */
@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class UnitManagerTest extends TestPvDbSchema {

    @Inject
    private UnitManager unitManager;

    @Inject
    private RoleHelper roleHelper;

    @Inject
    private TestDataHelper testDataHelper;


    @Before
    public void setup(){
        // Create the following radar units "1,2,58"
        // Create the following renal units "RWM51, 5"
        testDataHelper.createUnit();
    }

    @Test
    @Ignore // Incomplete test
    public void testGetRenalUnitsForUser() throws Exception {

        User user = roleHelper.createUnitAdmin("9999999999", "RW51");

        List<Unit> units = unitManager.getRenalUnits(user);

        assertTrue("The unit admin has one Renal Unit" , CollectionUtils.isNotEmpty(units) && units.size() == 1);

    }

}
