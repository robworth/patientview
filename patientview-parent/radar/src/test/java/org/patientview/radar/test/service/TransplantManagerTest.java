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

import org.junit.Before;
import org.patientview.radar.model.Transplant;
import org.patientview.radar.model.exception.InvalidModelException;
import org.patientview.radar.service.TransplantManager;
import org.patientview.radar.test.TestDataHelper;
import org.patientview.radar.test.TestPvDbSchema;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;
import java.text.SimpleDateFormat;

import static junit.framework.Assert.assertEquals;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class TransplantManagerTest extends TestPvDbSchema {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    @Autowired
    private TransplantManager transplantManager;

    @Inject
    private TestDataHelper testDataHelper;

    @Before
    public void setUp() {
        testDataHelper.createTransplant();
        testDataHelper.createTransplantReject();
    }

    @Test
    public void testSaveTransplantValidatoin() throws Exception{
        // save invalid transplant and check the list of errors
        Transplant transplant = new Transplant();
        transplant.setRadarNumber(219L);
        transplant.setDate(DATE_FORMAT.parse("20/09/2009"));
        transplant.setDateRecurr(DATE_FORMAT.parse("19/09/2009"));
        Transplant.RejectData rejectData = new Transplant.RejectData();
        rejectData.setFailureDate(DATE_FORMAT.parse("19/09/2009"));
        transplant.setDateFailureRejectData(rejectData);
        try {
            transplantManager.saveTransplant(transplant);
        } catch (InvalidModelException e) {
            assertEquals(e.getErrors().size(), 4);
        }
    }
}
