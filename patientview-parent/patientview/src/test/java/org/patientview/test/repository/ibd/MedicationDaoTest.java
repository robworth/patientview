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

import org.patientview.ibd.model.medication.Medication;
import org.patientview.ibd.model.medication.MedicationDose;
import org.patientview.repository.ibd.MedicationDao;
import org.patientview.repository.ibd.MedicationDoseDao;
import org.patientview.test.repository.BaseDaoTest;
import org.junit.Test;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MedicationDaoTest extends BaseDaoTest {

    @Inject
    private MedicationDao medicationDao;

    @Inject
    private MedicationDoseDao medicationDoseDao;

    @Test
    public void testAddGetMedication() throws Exception {
        MedicationDose medicationDose = new MedicationDose();
        medicationDose.setMg(400.00);
        medicationDose.setExtraInformation("Per kilo");

        medicationDoseDao.save(medicationDose);

        MedicationDose checkMedicationDose = medicationDoseDao.get(medicationDose.getId());

        assertTrue("Invalid id for new medication dose", medicationDose.getId() > 0);
        assertEquals("MG for dose not persisted", medicationDose.getMg(), checkMedicationDose.getMg());
        assertEquals("Extra information for dose not persisted", medicationDose.getExtraInformation(),
                checkMedicationDose.getExtraInformation());

        List<MedicationDose> allowedDosages = new ArrayList<MedicationDose>();
        allowedDosages.add(medicationDose);

        Medication medication = new Medication();
        medication.setName("Asacol");
        medication.setAllowedDosages(allowedDosages);

        medicationDao.save(medication);

        assertTrue("Invalid id for new medication", medication.getId() > 0);

        Medication checkMedication = medicationDao.get(medication.getId());

        assertNotNull(checkMedication);
        assertEquals("Name not persisted", checkMedication.getName(), medication.getName());
        assertEquals("Allowed dosages not persisted", checkMedication.getAllowedDosages().size(),
                medication.getAllowedDosages().size());
    }
}
