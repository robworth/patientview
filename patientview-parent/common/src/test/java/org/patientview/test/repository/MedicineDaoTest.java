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

package org.patientview.test.repository;

import org.patientview.patientview.model.Medicine;
import org.patientview.repository.MedicineDao;
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
