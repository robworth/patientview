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

import org.patientview.patientview.medicine.MedicineWithShortName;
import org.patientview.patientview.model.*;
import org.patientview.repository.MedicineDao;
import org.junit.Test;
import org.patientview.repository.UnitDao;
import org.patientview.test.helpers.RepositoryHelpers;

import javax.inject.Inject;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MedicineDaoTest extends BaseDaoTest {

    @Inject
    private MedicineDao medicineDao;

    @Inject
    private UnitDao unitDao;

    @Inject
    private  RepositoryHelpers repositoryHelpers;

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

    @Test
    public void testGet() throws Exception {

        Set<String> nhsnos = new HashSet<String>();
        for(int i=0;i<10;i++){
            Medicine testResult = getTestObject();
            testResult.setNhsno(i+"");
            nhsnos.add(i+"");
            medicineDao.save(testResult);
            assertTrue("Can't save testResult", testResult.getId() > 0);
        }

        List<MedicineWithShortName> savedTestResults = medicineDao.get(nhsnos, 1, 4);
        assertTrue("Can't get testResults: ", savedTestResults.size() == 4);

        Set<String> nhsnos_2 = new HashSet<String>();
        nhsnos_2.add(1+"");
        savedTestResults = medicineDao.get(nhsnos_2, 1, 4);
        assertTrue("Can't get testResults", savedTestResults.size() == 1);

        savedTestResults = medicineDao.get(nhsnos, 2, 20);
        assertTrue("Can't get testResults", savedTestResults.size() == 0);
    }

    @Test
    public void testGetCount() throws Exception {

        Set<String> nhsnos = new HashSet<String>();
        for(int i=0;i<10;i++){
            Medicine testResult = getTestObject();
            testResult.setNhsno(i+"");
            nhsnos.add(i+"");
            medicineDao.save(testResult);
            assertTrue("Can't save testResult", testResult.getId() > 0);
        }

        Long count = medicineDao.getCount(nhsnos);
        assertTrue("Can't get testResults: ", count == 10);

    }

}
