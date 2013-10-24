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

import org.patientview.ibd.model.enums.colitis.NumberOfStoolsNighttime;
import org.patientview.ibd.model.symptoms.ColitisSymptoms;
import org.patientview.ibd.model.enums.Feeling;
import org.patientview.ibd.model.enums.colitis.NumberOfStoolsDaytime;
import org.patientview.ibd.model.enums.colitis.PresentBlood;
import org.patientview.ibd.model.enums.colitis.ToiletTiming;
import org.patientview.repository.ibd.ColitisSymptomsDao;
import org.patientview.test.repository.BaseDaoTest;
import org.junit.Test;

import javax.inject.Inject;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ColitisDaoTest extends BaseDaoTest {

    @Inject
    private ColitisSymptomsDao colitisSymptomsDao;

    @Test
    public void testAddGetColitis() throws Exception {

        ColitisSymptoms colitisSymptoms = getTestObject();

        colitisSymptomsDao.save(colitisSymptoms);

        assertTrue("Invalid id for new ColitisSymptoms", colitisSymptoms.getId() > 0);

        ColitisSymptoms checkColitisSymptoms = colitisSymptomsDao.get(colitisSymptoms.getId());

        assertNotNull(checkColitisSymptoms);
        assertEquals("NHS no not persisted", checkColitisSymptoms.getNhsno(), colitisSymptoms.getNhsno());
        assertEquals("ColitisSymptoms date not persisted", checkColitisSymptoms.getSymptomDate(), colitisSymptoms.getSymptomDate());
        assertEquals("ColitisSymptoms stools day not persisted", checkColitisSymptoms.getNumberOfStoolsDaytime(), colitisSymptoms.getNumberOfStoolsDaytime());
        assertEquals("ColitisSymptoms stools night not persisted", checkColitisSymptoms.getNumberOfStoolsNighttime(), colitisSymptoms.getNumberOfStoolsNighttime());
        assertEquals("ColitisSymptoms toilet timing not persisted", checkColitisSymptoms.getToiletTiming(), colitisSymptoms.getToiletTiming());
        assertEquals("ColitisSymptoms present blood not persisted", checkColitisSymptoms.getPresentBlood(), colitisSymptoms.getPresentBlood());
        assertEquals("ColitisSymptoms feeling not persisted", checkColitisSymptoms.getFeeling(), colitisSymptoms.getFeeling());

    }

    private ColitisSymptoms getTestObject() throws Exception {
        ColitisSymptoms colitisSymptoms = new ColitisSymptoms();

        colitisSymptoms.setNhsno("1234567890");
        colitisSymptoms.setSymptomDate(new Date());
        colitisSymptoms.setNumberOfStoolsDaytime(NumberOfStoolsDaytime.SEVEN_TO_NINE);
        colitisSymptoms.setNumberOfStoolsNighttime(NumberOfStoolsNighttime.FOUR_TO_SIX);
        colitisSymptoms.setToiletTiming(ToiletTiming.HAVING_ACCIDENTS);
        colitisSymptoms.setPresentBlood(PresentBlood.A_TRACE);
        colitisSymptoms.setFeeling(Feeling.BELOW_PAR);

        return colitisSymptoms;
    }

}
