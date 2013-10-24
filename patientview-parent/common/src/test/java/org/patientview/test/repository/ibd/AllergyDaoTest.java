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

package org.patientview.test.repository.ibd;

import org.patientview.ibd.model.Allergy;
import org.patientview.repository.ibd.AllergyDao;
import org.patientview.test.repository.BaseDaoTest;
import org.junit.Test;

import javax.inject.Inject;
import java.util.Calendar;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class AllergyDaoTest extends BaseDaoTest {

    @Inject
    AllergyDao allergyDao;

    @Test
    public void testAddGetAllergy() throws Exception {
        Allergy allergy = getTestObject();

        /**
         * add
         */
        allergyDao.save(allergy);
        assertTrue("Can't save allergy", allergy.getId() > 0);

        /**
         * get
         */
        Allergy savedAllergy = allergyDao.get(allergy.getId());
        assertNotNull("Can't get allergy", savedAllergy);
    }

    @Test
    public void testDeleteAllergy() throws Exception {
        Allergy allergy = getTestObject();

        /**
         * add
         */
        allergyDao.save(allergy);
        assertTrue("Can't save allergy", allergy.getId() > 0);

        /**
         * delete
         */
        allergyDao.delete(allergy.getNhsno(), allergy.getUnitcode());

        Allergy deletedAllergy = allergyDao.getAllergy(allergy.getNhsno());
        assertNull("Can't delete allergy", deletedAllergy);

    }

    private Allergy getTestObject() throws Exception {
        Allergy allergy = new Allergy();
        allergy.setNhsno("1234567890");
        allergy.setUnitcode("unit1");
        allergy.setConfidenceLevel("confidence level");
        allergy.setDescription("description");
        allergy.setInfoSource("info source");
        allergy.setReaction("reaction");
        allergy.setRecordedDate(Calendar.getInstance());

        return allergy;
    }

}
