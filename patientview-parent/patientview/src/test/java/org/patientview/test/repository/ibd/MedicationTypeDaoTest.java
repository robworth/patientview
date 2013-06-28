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
import org.patientview.ibd.model.medication.MedicationType;
import org.patientview.repository.ibd.MedicationDao;
import org.patientview.repository.ibd.MedicationTypeDao;
import org.patientview.repository.ibd.MedicationDoseDao;
import org.patientview.test.repository.BaseDaoTest;
import org.junit.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MedicationTypeDaoTest extends BaseDaoTest {

    @Inject
    private MedicationDao medicationDao;

    @Inject
    private MedicationDoseDao medicationDoseDao;

    @Inject
    private MedicationTypeDao medicationTypeDao;

    @Test
    public void testAddGetMedicationType() throws Exception {
        MedicationType medicationType = new MedicationType();
        medicationType.setName("Suppository");

        List<Medication> medications = new ArrayList<Medication>();
        medications.add(getTestMedicationObject());

        medicationType.setMedications(medications);

        medicationTypeDao.save(medicationType);

        assertTrue("Invalid id for new medication type", medicationType.getId() > 0);

        MedicationType checkMedicationType = medicationTypeDao.get(medicationType.getId());

        assertNotNull(checkMedicationType);
        assertEquals("Name not persisted", checkMedicationType.getName(), medicationType.getName());
        assertEquals("Medications not persisted", checkMedicationType.getMedications().size(),
                medicationType.getMedications().size());
    }

    private Medication getTestMedicationObject() {
        MedicationDose medicationDose = new MedicationDose();
        medicationDose.setMg(400.00);

        medicationDoseDao.save(medicationDose);

        List<MedicationDose> allowedDosages = new ArrayList<MedicationDose>();
        allowedDosages.add(medicationDose);

        Medication medication = new Medication();
        medication.setName("Asacol");
        medication.setAllowedDosages(allowedDosages);

        medicationDao.save(medication);

        return medication;
    }
}
