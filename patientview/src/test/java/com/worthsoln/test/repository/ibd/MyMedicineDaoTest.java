package com.worthsoln.test.repository.ibd;

import com.worthsoln.ibd.model.medication.Medication;
import com.worthsoln.ibd.model.medication.MedicationDose;
import com.worthsoln.ibd.model.medication.MedicationType;
import com.worthsoln.ibd.model.medication.MyMedicine;
import com.worthsoln.ibd.model.medication.enums.MedicationFrequency;
import com.worthsoln.ibd.model.medication.enums.MedicationNoOf;
import com.worthsoln.repository.ibd.MedicationDao;
import com.worthsoln.repository.ibd.MedicationTypeDao;
import com.worthsoln.repository.ibd.MyMedicineDao;
import com.worthsoln.repository.ibd.impl.MedicationDoseDao;
import com.worthsoln.test.repository.BaseDaoTest;
import org.junit.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MyMedicineDaoTest extends BaseDaoTest {

    @Inject
    private MedicationDao medicationDao;

    @Inject
    private MedicationDoseDao medicationDoseDao;

    @Inject
    private MedicationTypeDao medicationTypeDao;

    @Inject
    private MyMedicineDao myMedicineDao;

    @Test
    public void testAddGetMyMedicine() throws Exception {
        MedicationType medicationType = getTestMedicationTypeObject();
        Medication medication = getTestMedicationObject();
        MedicationDose medicationDose = medication.getAllowedDosages().get(0);

        MyMedicine myMedicine = new MyMedicine();

        myMedicine.setNhsno("123456789");
        myMedicine.setDateStarted(new Date());
        myMedicine.setDateStopped(new Date());
        myMedicine.setMedicationType(medicationType);
        myMedicine.setMedication(medication);
        myMedicine.setMedicationDose(medicationDose);
        myMedicine.setMedicationNoOf(MedicationNoOf.ONE_TO_FOUR);
        myMedicine.setMedicationFrequency(MedicationFrequency.ONCE_A_DAY);
        myMedicine.setReasonForStopping("Reason for stopping");

        myMedicineDao.save(myMedicine);

        assertTrue("Invalid id for new medicine", myMedicine.getId() > 0);

        MyMedicine checkMyMedicine = myMedicineDao.get(myMedicine.getId());

        assertNotNull(checkMyMedicine);
        assertEquals("NHS no not persisted", checkMyMedicine.getNhsno(), myMedicine.getNhsno());
        assertEquals("Date started not persisted", checkMyMedicine.getDateStarted(), myMedicine.getDateStarted());
        assertEquals("Date stopped not persisted", checkMyMedicine.getDateStopped(), myMedicine.getDateStopped());
        assertEquals("Medication type not persisted", checkMyMedicine.getMedicationType(),
                myMedicine.getMedicationType());
        assertEquals("Medication not persisted", checkMyMedicine.getMedication(), myMedicine.getMedication());
        assertEquals("Medication dose not persisted", checkMyMedicine.getMedicationDose(),
                myMedicine.getMedicationDose());
        assertEquals("Medication no of not persisted", checkMyMedicine.getMedicationNoOf(),
                myMedicine.getMedicationNoOf());
        assertEquals("Medication frequency of not persisted", checkMyMedicine.getMedicationFrequency(),
                myMedicine.getMedicationFrequency());
        assertEquals("Reason for stopping not persisted", checkMyMedicine.getReasonForStopping(),
                myMedicine.getReasonForStopping());
        assertTrue("Medicine not stopped", checkMyMedicine.hasStopped());
    }

    @Test
    public void testGetAllMedicines() throws Exception {
        MedicationType medicationType = getTestMedicationTypeObject();
        Medication medication = getTestMedicationObject();
        MedicationDose medicationDose = medication.getAllowedDosages().get(0);

        // create two test medicines
        MyMedicine myMedicine1 = new MyMedicine();

        myMedicine1.setNhsno("123456789");
        myMedicine1.setDateStarted(new Date());
        myMedicine1.setDateStopped(new Date());
        myMedicine1.setMedicationType(medicationType);
        myMedicine1.setMedication(medication);
        myMedicine1.setMedicationDose(medicationDose);
        myMedicine1.setMedicationNoOf(MedicationNoOf.ONE_TO_FOUR);
        myMedicine1.setMedicationFrequency(MedicationFrequency.ONCE_A_DAY);
        myMedicine1.setReasonForStopping("Reason for stopping");

        myMedicineDao.save(myMedicine1);

        assertTrue("Invalid id for new medicine 1", myMedicine1.getId() > 0);

        MyMedicine myMedicine2 = new MyMedicine();

        myMedicine2.setNhsno("123456789");
        myMedicine2.setDateStarted(new Date());
        myMedicine2.setDateStopped(new Date());
        myMedicine2.setMedicationType(medicationType);
        myMedicine2.setMedication(medication);
        myMedicine2.setMedicationDose(medicationDose);
        myMedicine2.setMedicationNoOf(MedicationNoOf.ONE_TO_FOUR);
        myMedicine2.setMedicationFrequency(MedicationFrequency.ONCE_A_DAY);
        myMedicine2.setReasonForStopping("Reason for stopping");

        myMedicineDao.save(myMedicine2);

        assertTrue("Invalid id for new medicine 2", myMedicine2.getId() > 0);

        // now try to pull back all medicines for this user and should see 2
        List<MyMedicine> checkMyMedicines = myMedicineDao.getAllMedicines("123456789");

        assertTrue("No medicines found", !checkMyMedicines.isEmpty() && checkMyMedicines.size() > 0);
        assertTrue("To many medicines found", checkMyMedicines.size() == 2);
    }

    @Test
    public void testGetCurrentMedicines() throws Exception {
        MedicationType medicationType = getTestMedicationTypeObject();
        Medication medication = getTestMedicationObject();
        MedicationDose medicationDose = medication.getAllowedDosages().get(0);

        // create two test medicines 1 that does not have a date stopped meaning current and one which has
        MyMedicine myMedicine1 = new MyMedicine();

        myMedicine1.setNhsno("123456789");
        myMedicine1.setDateStarted(new Date());
        myMedicine1.setMedicationType(medicationType);
        myMedicine1.setMedication(medication);
        myMedicine1.setMedicationDose(medicationDose);
        myMedicine1.setMedicationNoOf(MedicationNoOf.ONE_TO_FOUR);
        myMedicine1.setMedicationFrequency(MedicationFrequency.ONCE_A_DAY);

        myMedicineDao.save(myMedicine1);

        assertTrue("Invalid id for new medicine 1", myMedicine1.getId() > 0);

        MyMedicine myMedicine2 = new MyMedicine();

        myMedicine2.setNhsno("123456789");
        myMedicine2.setDateStarted(new Date());
        myMedicine2.setDateStopped(new Date());
        myMedicine2.setMedicationType(medicationType);
        myMedicine2.setMedication(medication);
        myMedicine2.setMedicationDose(medicationDose);
        myMedicine2.setMedicationNoOf(MedicationNoOf.ONE_TO_FOUR);
        myMedicine2.setMedicationFrequency(MedicationFrequency.ONCE_A_DAY);
        myMedicine2.setReasonForStopping("Reason for stopping");

        myMedicineDao.save(myMedicine2);

        // now try to pull back all medicines for this user and should see 1 with the id of the first
        List<MyMedicine> checkMyMedicines = myMedicineDao.getCurrentMedicines("123456789");

        assertTrue("No medicines found", !checkMyMedicines.isEmpty() && checkMyMedicines.size() > 0);
        assertTrue("To many medicines found", checkMyMedicines.size() == 1);
        assertEquals("Wrong id for medicine pulled back", checkMyMedicines.get(0).getId(), myMedicine1.getId());
    }

    @Test
    public void testGetStoppedMedicines() throws Exception {
        MedicationType medicationType = getTestMedicationTypeObject();
        Medication medication = getTestMedicationObject();
        MedicationDose medicationDose = medication.getAllowedDosages().get(0);

        // create two test medicines 1 that does not have a date stopped meaning current and one which has
        MyMedicine myMedicine1 = new MyMedicine();

        myMedicine1.setNhsno("123456789");
        myMedicine1.setDateStarted(new Date());
        myMedicine1.setMedicationType(medicationType);
        myMedicine1.setMedication(medication);
        myMedicine1.setMedicationDose(medicationDose);
        myMedicine1.setMedicationNoOf(MedicationNoOf.ONE_TO_FOUR);
        myMedicine1.setMedicationFrequency(MedicationFrequency.ONCE_A_DAY);

        myMedicineDao.save(myMedicine1);

        assertTrue("Invalid id for new medicine 1", myMedicine1.getId() > 0);

        MyMedicine myMedicine2 = new MyMedicine();

        myMedicine2.setNhsno("123456789");
        myMedicine2.setDateStarted(new Date());
        myMedicine2.setDateStopped(new Date());
        myMedicine2.setMedicationType(medicationType);
        myMedicine2.setMedication(medication);
        myMedicine2.setMedicationDose(medicationDose);
        myMedicine2.setMedicationNoOf(MedicationNoOf.ONE_TO_FOUR);
        myMedicine2.setMedicationFrequency(MedicationFrequency.ONCE_A_DAY);
        myMedicine2.setReasonForStopping("Reason for stopping");

        myMedicineDao.save(myMedicine2);

        // now try to pull back all medicines for this user and should see 1 with the id of the second
        List<MyMedicine> checkMyMedicines = myMedicineDao.getStoppedMedicines("123456789");

        assertTrue("No medicines found", !checkMyMedicines.isEmpty() && checkMyMedicines.size() > 0);
        assertTrue("To many medicines found", checkMyMedicines.size() == 1);
        assertEquals("Wrong id for medicine pulled back", checkMyMedicines.get(0).getId(), myMedicine2.getId());
    }

    private MedicationType getTestMedicationTypeObject() {
        MedicationType medicationType = new MedicationType();
        medicationType.setName("Suppository");

        medicationTypeDao.save(medicationType);

        return medicationType;
    }

    private Medication getTestMedicationObject() {
        MedicationDose medicationDose = new MedicationDose();
        medicationDose.setValue("400mg");

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
