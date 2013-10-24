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

import org.patientview.patientview.model.Diagnosis;
import org.patientview.repository.DiagnosisDao;
import org.junit.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DiagnosisDaoTest extends BaseDaoTest {

    @Inject
    private DiagnosisDao diagnosisDao;

    @Test
    public void testAddGetDiagnosis() throws Exception {
        Diagnosis diagnosis = getTestObject();

        /**
         * add
         */
        diagnosisDao.save(diagnosis);
        assertTrue("Invalid id for new diagnosis", diagnosis.getId() > 0);

        /**
         * get
         */
        Diagnosis savedDiagnosis = diagnosisDao.get(diagnosis.getId());

        assertNotNull(savedDiagnosis);
        assertEquals("Diagnosis diagnosis not persisted", diagnosis.getDiagnosis(), savedDiagnosis.getDiagnosis());
        assertEquals("Diagnosis display order not persisted", diagnosis.getDisplayorder(),
                savedDiagnosis.getDisplayorder());
        assertEquals("Diagnosis unit code not persisted", diagnosis.getUnitcode(), savedDiagnosis.getUnitcode());
        assertEquals("Diagnosis nhs no not persisted", diagnosis.getNhsno(), savedDiagnosis.getNhsno());

        /**
         * delete
         */
        diagnosisDao.deleteOtherDiagnoses(savedDiagnosis.getNhsno(), savedDiagnosis.getUnitcode());

        List<Diagnosis> savedDiagnoses = diagnosisDao.getOtherDiagnoses(savedDiagnosis.getNhsno(),
                savedDiagnosis.getUnitcode());
        assertTrue("Can't delete diagnoses", savedDiagnoses.size() == 0);
    }

    @Test
    public void testDeleteDiagnosis() throws Exception {
        Diagnosis diagnosis = getTestObject();

        /**
         * add
         */
        diagnosisDao.save(diagnosis);
        assertTrue("Invalid id for new diagnosis", diagnosis.getId() > 0);

        /**
         * delete
         */
        diagnosisDao.deleteOtherDiagnoses(diagnosis.getNhsno(), diagnosis.getUnitcode());

        List<Diagnosis> savedDiagnoses = diagnosisDao.getOtherDiagnoses(diagnosis.getNhsno(), diagnosis.getUnitcode());
        assertTrue("Can't delete diagnoses", savedDiagnoses.size() == 0);
    }


    @Test
    public void testGetOtherDiagnosis() throws Exception {
        Diagnosis diagnosis = getTestObject();

        diagnosisDao.save(diagnosis);
        assertTrue("Invalid id for new diagnosis 1", diagnosis.getId() > 0);

        Diagnosis diagnosis2 = getSecondTestObject();

        diagnosisDao.save(diagnosis2);
        assertTrue("Invalid id for new diagnosis 2", diagnosis2.getId() > 0);

        List<Diagnosis> checkDiagnoses = diagnosisDao.getOtherDiagnoses("123456789", "unitcode1");

        assertNotNull(checkDiagnoses);
        assertTrue("No diagnosis found found", !checkDiagnoses.isEmpty() && checkDiagnoses.size() > 0);
    }

    private Diagnosis getTestObject() throws Exception {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDiagnosis("Test diagnosis");
        diagnosis.setDisplayorder("1");
        diagnosis.setUnitcode("unitcode1");
        diagnosis.setNhsno("123456789");

        return diagnosis;
    }

    private Diagnosis getSecondTestObject() throws Exception {
        Diagnosis diagnosis2 = new Diagnosis();
        diagnosis2.setDiagnosis("Test diagnosis 2");
        diagnosis2.setDisplayorder("2");
        diagnosis2.setUnitcode("unitcode1");
        diagnosis2.setNhsno("123456789");

        return diagnosis2;
    }
}
