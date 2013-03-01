package com.worthsoln.test.repository.ibd;

import com.worthsoln.ibd.model.medication.Medication;
import com.worthsoln.ibd.model.medication.MedicationDose;
import com.worthsoln.ibd.model.medication.MedicationType;
import com.worthsoln.repository.ibd.MedicationDao;
import com.worthsoln.repository.ibd.MedicationTypeDao;
import com.worthsoln.repository.ibd.MedicationDoseDao;
import com.worthsoln.test.repository.BaseDaoTest;
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
