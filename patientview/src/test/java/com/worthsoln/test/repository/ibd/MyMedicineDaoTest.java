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
