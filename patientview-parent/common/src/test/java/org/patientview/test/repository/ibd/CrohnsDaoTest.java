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

import org.patientview.ibd.model.symptoms.CrohnsSymptoms;
import org.patientview.ibd.model.enums.Feeling;
import org.patientview.ibd.model.enums.crohns.AbdominalPain;
import org.patientview.ibd.model.enums.crohns.Complication;
import org.patientview.ibd.model.enums.crohns.MassInTummy;
import org.patientview.repository.ibd.CrohnsSymptomsDao;
import org.patientview.test.repository.BaseDaoTest;
import org.junit.Test;

import javax.inject.Inject;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CrohnsDaoTest extends BaseDaoTest {

    @Inject
    private CrohnsSymptomsDao crohnsSymptomsDao;

    @Test
    public void testAddGetCrohns() throws Exception {

        CrohnsSymptoms crohnsSymptoms = getTestObject();

        crohnsSymptomsDao.save(crohnsSymptoms);

        assertTrue("Invalid id for new crohnsSymptoms", crohnsSymptoms.getId() > 0);

        CrohnsSymptoms checkCrohnsSymptoms = crohnsSymptomsDao.get(crohnsSymptoms.getId());

        assertNotNull(checkCrohnsSymptoms);
        assertEquals("NHS no not persisted", checkCrohnsSymptoms.getNhsno(), crohnsSymptoms.getNhsno());
        assertEquals("CrohnsSymptoms date not persisted", checkCrohnsSymptoms.getSymptomDate(), crohnsSymptoms.getSymptomDate());
        assertEquals("CrohnsSymptoms abdominal pain not persisted", checkCrohnsSymptoms.getAbdominalPain(), crohnsSymptoms.getAbdominalPain());
        assertEquals("CrohnsSymptoms open bowels not persisted", checkCrohnsSymptoms.getOpenBowels(), crohnsSymptoms.getOpenBowels());
        assertEquals("CrohnsSymptoms feeling not persisted", checkCrohnsSymptoms.getFeeling(), crohnsSymptoms.getFeeling());
        assertEquals("CrohnsSymptoms complications not persisted", checkCrohnsSymptoms.getComplication(), crohnsSymptoms.getComplication());
        assertEquals("CrohnsSymptoms mass in tummy not persisted", checkCrohnsSymptoms.getMassInTummy(), crohnsSymptoms.getMassInTummy());

    }

    private CrohnsSymptoms getTestObject() throws Exception {
        CrohnsSymptoms crohnsSymptoms = new CrohnsSymptoms();

        crohnsSymptoms.setNhsno("1234567890");
        crohnsSymptoms.setSymptomDate(new Date());
        crohnsSymptoms.setAbdominalPain(AbdominalPain.MODERATE);
        crohnsSymptoms.setOpenBowels(5);
        crohnsSymptoms.setFeeling(Feeling.POOR);
        crohnsSymptoms.setComplication(Complication.NONE);
        crohnsSymptoms.setMassInTummy(MassInTummy.DEFINITE);

        return crohnsSymptoms;
    }

}
