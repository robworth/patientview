package com.worthsoln.test.repository;

import com.worthsoln.patientview.model.Medicine;
import com.worthsoln.repository.MedicineDao;
import org.junit.Test;

import javax.inject.Inject;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MedicineDaoTest extends BaseDaoTest {

    @Inject
    private MedicineDao medicineDao;

    @Test
    public void testAddGetMedicine() throws Exception {
        Medicine medicine = new Medicine();
        medicine.setNhsno("123456789");
        medicine.setUnitcode("testunit");
        medicine.setStartdate(Calendar.getInstance());
        medicine.setName("testname");
        medicine.setDose("testdose");

        medicineDao.save(medicine);

        assertTrue("Invalid id for new medicine", medicine.getId() > 0);

        Medicine checkMedicine = medicineDao.get(medicine.getId());

        assertNotNull(checkMedicine);
        assertEquals("Nhs no not persisted", medicine.getNhsno(), medicine.getNhsno());
        assertEquals("Unit code not persisted", medicine.getUnitcode(), medicine.getUnitcode());
        assertEquals("Start date not persisted", medicine.getStartdate(), medicine.getStartdate());
        assertEquals("Name not persisted", medicine.getName(), medicine.getName());
        assertEquals("Dose not persisted", medicine.getDose(), medicine.getDose());
    }
}
