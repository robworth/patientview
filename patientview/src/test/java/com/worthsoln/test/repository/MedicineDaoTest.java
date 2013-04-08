package com.worthsoln.test.repository;

import com.worthsoln.patientview.model.Medicine;
import com.worthsoln.repository.MedicineDao;
import org.junit.Test;

import javax.inject.Inject;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MedicineDaoTest extends BaseDaoTest {

    @Inject
    private MedicineDao medicineDao;

    @Test
    public void testAddGetMedicine() throws Exception {
        Medicine medicine = getTestObject();

        /**
         * add
         */
        medicineDao.save(medicine);

        assertTrue("Invalid id for new medicine", medicine.getId() > 0);

        /**
         * get
         */
        Medicine savedMedicine = medicineDao.get(medicine.getId());

        assertNotNull(savedMedicine);
        assertEquals("Nhs no not persisted", medicine.getNhsno(), medicine.getNhsno());
        assertEquals("Unit code not persisted", medicine.getUnitcode(), medicine.getUnitcode());
        assertEquals("Start date not persisted", medicine.getStartdate(), medicine.getStartdate());
        assertEquals("Name not persisted", medicine.getName(), medicine.getName());
        assertEquals("Dose not persisted", medicine.getDose(), medicine.getDose());
    }

    @Test
    public void testDeleteMedicine() throws Exception {
        Medicine medicine = getTestObject();

        /**
         * add
         */
        medicineDao.save(medicine);

        assertTrue("Invalid id for new medicine", medicine.getId() > 0);

        /**
         * delete
         */
        medicineDao.delete(medicine.getNhsno(), medicine.getUnitcode());

        List<Medicine> savedMedicines = medicineDao.getAll();

        assertTrue("Can't delete medicines", savedMedicines.size() == 0);
    }

    private Medicine getTestObject() {
        Medicine medicine = new Medicine();
        medicine.setNhsno("123456789");
        medicine.setUnitcode("testunit");
        medicine.setStartdate(Calendar.getInstance());
        medicine.setName("testname");
        medicine.setDose("testdose");

        return medicine;
    }

}
