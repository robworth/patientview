package com.worthsoln.test.repository.ibd;

import com.worthsoln.ibd.model.medication.Medication;
import com.worthsoln.ibd.model.medication.MedicationDose;
import com.worthsoln.repository.ibd.MedicationDao;
import com.worthsoln.repository.ibd.impl.MedicationDoseDao;
import com.worthsoln.test.repository.BaseDaoTest;
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
        medicationDose.setValue("400mg");

        medicationDoseDao.save(medicationDose);

        assertTrue("Invalid id for new medication dose", medicationDose.getId() > 0);

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
