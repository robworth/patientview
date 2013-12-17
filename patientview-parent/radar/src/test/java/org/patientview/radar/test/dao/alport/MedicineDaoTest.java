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

package org.patientview.radar.test.dao.alport;

import org.junit.Before;
import org.patientview.model.generic.DiseaseGroup;
import org.patientview.radar.dao.alport.MedicineDao;
import org.patientview.radar.dao.generic.DiseaseGroupDao;
import org.patientview.radar.model.alport.Medicine;
import org.patientview.radar.test.TestDataHelper;
import org.patientview.radar.test.dao.BaseDaoTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
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

    @Inject
    private TestDataHelper testDataHelper;

    @Before
    public void setUp() {
        testDataHelper.createUnit();
    }

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
