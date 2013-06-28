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
import org.patientview.ibd.model.medication.MyMedication;
import org.patientview.ibd.model.medication.enums.MedicationFrequency;
import org.patientview.repository.ibd.MedicationDao;
import org.patientview.repository.ibd.MedicationTypeDao;
import org.patientview.repository.ibd.MyMedicationDao;
import org.patientview.repository.ibd.MedicationDoseDao;
import org.patientview.test.repository.BaseDaoTest;
import org.junit.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MyMedicationDaoTest extends BaseDaoTest {

    @Inject
    private MedicationDao medicationDao;

    @Inject
    private MedicationDoseDao medicationDoseDao;

    @Inject
    private MedicationTypeDao medicationTypeDao;

    @Inject
    private MyMedicationDao myMedicationDao;

    @Test
    public void testAddGetMyMedicine() throws Exception {
        MedicationType medicationType = getTestMedicationTypeObject();
        Medication medication = getTestMedicationObject();
        MedicationDose medicationDose = medication.getAllowedDosages().get(0);

        MyMedication myMedication = new MyMedication();

        myMedication.setNhsno("123456789");
        myMedication.setDateStarted(new Date());
        myMedication.setDateStopped(new Date());
        myMedication.setMedicationType(medicationType);
        myMedication.setMedication(medication);
        myMedication.setMedicationDose(medicationDose);
        myMedication.setMedicationFrequency(MedicationFrequency.ONCE_A_DAY);
        myMedication.setReasonForStopping("Reason for stopping");

        myMedicationDao.save(myMedication);

        assertTrue("Invalid id for new medicine", myMedication.getId() > 0);

        MyMedication checkMyMedication = myMedicationDao.get(myMedication.getId());

        assertNotNull(checkMyMedication);
        assertEquals("NHS no not persisted", checkMyMedication.getNhsno(), myMedication.getNhsno());
        assertEquals("Date started not persisted", checkMyMedication.getDateStarted(), myMedication.getDateStarted());
        assertEquals("Date stopped not persisted", checkMyMedication.getDateStopped(), myMedication.getDateStopped());
        assertEquals("Medication type not persisted", checkMyMedication.getMedicationType(),
                myMedication.getMedicationType());
        assertEquals("Medication not persisted", checkMyMedication.getMedication(), myMedication.getMedication());
        assertEquals("Medication dose not persisted", checkMyMedication.getMedicationDose(),
                myMedication.getMedicationDose());
        assertEquals("Medication frequency of not persisted", checkMyMedication.getMedicationFrequency(),
                myMedication.getMedicationFrequency());
        assertEquals("Reason for stopping not persisted", checkMyMedication.getReasonForStopping(),
                myMedication.getReasonForStopping());
        assertTrue("Medicine not stopped", checkMyMedication.hasStopped());
    }

    @Test
    public void testGetAllMedicines() throws Exception {
        MedicationType medicationType = getTestMedicationTypeObject();
        Medication medication = getTestMedicationObject();
        MedicationDose medicationDose = medication.getAllowedDosages().get(0);

        // create two test medicines
        MyMedication myMedication1 = new MyMedication();

        myMedication1.setNhsno("123456789");
        myMedication1.setDateStarted(new Date());
        myMedication1.setDateStopped(new Date());
        myMedication1.setMedicationType(medicationType);
        myMedication1.setMedication(medication);
        myMedication1.setMedicationDose(medicationDose);
        myMedication1.setMedicationFrequency(MedicationFrequency.ONCE_A_DAY);
        myMedication1.setReasonForStopping("Reason for stopping");

        myMedicationDao.save(myMedication1);

        assertTrue("Invalid id for new medicine 1", myMedication1.getId() > 0);

        MyMedication myMedication2 = new MyMedication();

        myMedication2.setNhsno("123456789");
        myMedication2.setDateStarted(new Date());
        myMedication2.setDateStopped(new Date());
        myMedication2.setMedicationType(medicationType);
        myMedication2.setMedication(medication);
        myMedication2.setMedicationDose(medicationDose);
        myMedication2.setMedicationFrequency(MedicationFrequency.ONCE_A_DAY);
        myMedication2.setReasonForStopping("Reason for stopping");

        myMedicationDao.save(myMedication2);

        assertTrue("Invalid id for new medicine 2", myMedication2.getId() > 0);

        // now try to pull back all medicines for this user and should see 2
        List<MyMedication> checkMyMedications = myMedicationDao.getAllMedications("123456789");

        assertTrue("No medicines found", !checkMyMedications.isEmpty() && checkMyMedications.size() > 0);
        assertTrue("To many medicines found", checkMyMedications.size() == 2);
    }

    @Test
    public void testGetCurrentMedicines() throws Exception {
        MedicationType medicationType = getTestMedicationTypeObject();
        Medication medication = getTestMedicationObject();
        MedicationDose medicationDose = medication.getAllowedDosages().get(0);

        // create two test medicines 1 that does not have a date stopped meaning current and one which has
        MyMedication myMedication1 = new MyMedication();

        myMedication1.setNhsno("123456789");
        myMedication1.setDateStarted(new Date());
        myMedication1.setMedicationType(medicationType);
        myMedication1.setMedication(medication);
        myMedication1.setMedicationDose(medicationDose);
        myMedication1.setMedicationFrequency(MedicationFrequency.ONCE_A_DAY);

        myMedicationDao.save(myMedication1);

        assertTrue("Invalid id for new medicine 1", myMedication1.getId() > 0);

        MyMedication myMedication2 = new MyMedication();

        myMedication2.setNhsno("123456789");
        myMedication2.setDateStarted(new Date());
        myMedication2.setDateStopped(new Date());
        myMedication2.setMedicationType(medicationType);
        myMedication2.setMedication(medication);
        myMedication2.setMedicationDose(medicationDose);
        myMedication2.setMedicationFrequency(MedicationFrequency.ONCE_A_DAY);
        myMedication2.setReasonForStopping("Reason for stopping");

        myMedicationDao.save(myMedication2);

        // now try to pull back all medicines for this user and should see 1 with the id of the first
        List<MyMedication> checkMyMedications = myMedicationDao.getCurrentMedications("123456789");

        assertTrue("No medicines found", !checkMyMedications.isEmpty() && checkMyMedications.size() > 0);
        assertTrue("To many medicines found", checkMyMedications.size() == 1);
        assertEquals("Wrong id for medicine pulled back", checkMyMedications.get(0).getId(), myMedication1.getId());
    }

    @Test
    public void testGetStoppedMedicines() throws Exception {
        MedicationType medicationType = getTestMedicationTypeObject();
        Medication medication = getTestMedicationObject();
        MedicationDose medicationDose = medication.getAllowedDosages().get(0);

        // create two test medicines 1 that does not have a date stopped meaning current and one which has
        MyMedication myMedication1 = new MyMedication();

        myMedication1.setNhsno("123456789");
        myMedication1.setDateStarted(new Date());
        myMedication1.setMedicationType(medicationType);
        myMedication1.setMedication(medication);
        myMedication1.setMedicationDose(medicationDose);
        myMedication1.setMedicationFrequency(MedicationFrequency.ONCE_A_DAY);

        myMedicationDao.save(myMedication1);

        assertTrue("Invalid id for new medicine 1", myMedication1.getId() > 0);

        MyMedication myMedication2 = new MyMedication();

        myMedication2.setNhsno("123456789");
        myMedication2.setDateStarted(new Date());
        myMedication2.setDateStopped(new Date());
        myMedication2.setMedicationType(medicationType);
        myMedication2.setMedication(medication);
        myMedication2.setMedicationDose(medicationDose);
        myMedication2.setMedicationFrequency(MedicationFrequency.ONCE_A_DAY);
        myMedication2.setReasonForStopping("Reason for stopping");

        myMedicationDao.save(myMedication2);

        // now try to pull back all medicines for this user and should see 1 with the id of the second
        List<MyMedication> checkMyMedications = myMedicationDao.getStoppedMedications("123456789");

        assertTrue("No medicines found", !checkMyMedications.isEmpty() && checkMyMedications.size() > 0);
        assertTrue("To many medicines found", checkMyMedications.size() == 1);
        assertEquals("Wrong id for medicine pulled back", checkMyMedications.get(0).getId(), myMedication2.getId());
    }

    private MedicationType getTestMedicationTypeObject() {
        MedicationType medicationType = new MedicationType();
        medicationType.setName("Suppository");

        medicationTypeDao.save(medicationType);

        return medicationType;
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
