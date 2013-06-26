package org.patientview.radar.test.dao.alport;

import org.patientview.radar.dao.alport.MedicineDao;
import org.patientview.radar.dao.generic.DiseaseGroupDao;
import org.patientview.radar.model.alport.Medicine;
import org.patientview.radar.model.generic.DiseaseGroup;
import org.patientview.radar.test.dao.BaseDaoTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class MedicineDaoTest extends BaseDaoTest {

    @Autowired
    private MedicineDao medicineDao;

    @Autowired
    private DiseaseGroupDao diseaseGroupDao;

    private SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    @Test
    public void testAddGetMedicine() throws Exception {
        Medicine medicine = getTestObject();

        medicineDao.save(medicine);

        assertTrue("Invalid id for new medicine", medicine.getId() > 0);

        Medicine checkMedicine = medicineDao.get(medicine.getId());

        assertNotNull(checkMedicine);
        assertEquals("Nhs no not persisted", checkMedicine.getNhsNo(), medicine.getNhsNo());
        assertEquals("Name not persisted", checkMedicine.getName(), medicine.getName());
        assertEquals("Dose not persisted", checkMedicine.getDose(), medicine.getDose());
        assertEquals("Unit code not persisted", checkMedicine.getDiseaseGroup().getId(),
                medicine.getDiseaseGroup().getId());
        assertEquals("Start date not persisted", DATE_FORMAT.format(checkMedicine.getStartDate()),
                DATE_FORMAT.format(medicine.getStartDate()));
        assertEquals("End date not persisted", DATE_FORMAT.format(checkMedicine.getEndDate()),
                DATE_FORMAT.format(medicine.getEndDate()));
    }

    @Test
    public void testAddGetMedicinesByNhsNo() throws Exception {
        Medicine medicine1 = getTestObject();
        medicineDao.save(medicine1);
        assertTrue("Invalid id for new medicine1", medicine1.getId() > 0);

        Medicine medicine2 = getTestObject();
        medicineDao.save(medicine2);
        assertTrue("Invalid id for new medicine2", medicine2.getId() > 0);

        // now try to pull back all medicines for this user and should see 2
        List<Medicine> checkMedicines = medicineDao.getMedicinesByNhsNo("123456789");

        assertTrue("No medicines found", !checkMedicines.isEmpty() && checkMedicines.size() > 0);
        assertTrue("To many medicines found", checkMedicines.size() == 2);
    }

    @Test
    public void testAddGetMedicinesByNhsNoAndDiseaseGroup() throws Exception {
        DiseaseGroup diseaseGroup1 = diseaseGroupDao.getById("1");

        Medicine medicine1 = getTestObject();
        medicineDao.save(medicine1);
        assertTrue("Invalid id for new medicine1", medicine1.getId() > 0);

        Medicine medicine2 = getTestObject();
        medicine2.setDiseaseGroup(diseaseGroupDao.getById("2"));
        medicineDao.save(medicine2);
        assertTrue("Invalid id for new medicine2", medicine2.getId() > 0);

        // now try to pull back all medicines for this user for disease group 1 - should get back the first only
        List<Medicine> checkMedicines = medicineDao.getMedicinesByNhsNoAndDiseaseGroup("123456789", diseaseGroup1);

        assertTrue("No medicines found", !checkMedicines.isEmpty() && checkMedicines.size() > 0);
        assertTrue("To many medicines found", checkMedicines.size() == 1);
    }

    @Test
    public void testAddDeleteMedicine() throws Exception {
        Medicine medicine = getTestObject();
        medicineDao.save(medicine);
        assertTrue("Invalid id for new medicine", medicine.getId() > 0);

        // delete and try and pull back again
        medicineDao.delete(medicine);

        Medicine checkMedicine = medicineDao.get(medicine.getId());

        assertNull(checkMedicine);
    }

    private Medicine getTestObject() {
        Medicine medicine = new Medicine();
        medicine.setNhsNo("123456789");
        medicine.setName("Test medicine name");
        medicine.setDose("Test medicine dose");
        medicine.setDiseaseGroup(diseaseGroupDao.getById("1"));
        medicine.setStartDate(new Date());
        medicine.setEndDate(new Date());
        return medicine;
    }
}
