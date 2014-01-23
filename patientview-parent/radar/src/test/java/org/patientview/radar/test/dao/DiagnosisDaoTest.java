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

package org.patientview.radar.test.dao;

import org.junit.Before;
import org.junit.Ignore;
import org.patientview.radar.dao.DiagnosisDao;
import org.patientview.radar.model.Diagnosis;
import org.patientview.radar.model.DiagnosisCode;
import org.patientview.radar.model.Karotype;
import org.junit.Test;
import org.patientview.radar.test.TestDataHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DiagnosisDaoTest extends BaseDaoTest {

    @Autowired
    private DiagnosisDao diagnosisDao;

    @Inject
    private TestDataHelper testDataHelper;

    @Before
    public void setUp() {
        testDataHelper.createDiagCode();
        testDataHelper.createKarotype();
        testDataHelper.createDiagnosis();
    }

    @Test
    public void testSaveDiagnosis() {
        // test save
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setText("Testing");
        diagnosisDao.saveDiagnosis(diagnosis);
        assertNotNull(diagnosis.getId());

        // test update
        Diagnosis diagnosis2 = new Diagnosis();
        diagnosis2.setId(new Long(139));
        diagnosis2.setText("Testing");
        diagnosisDao.saveDiagnosis(diagnosis2);
    }

    @Test
    public void testGetDiagnosis() {
        Diagnosis diagnosis = diagnosisDao.getDiagnosis(117L);
        assertNotNull("Diagnosis is null", diagnosis);
        assertEquals("ID is wrong on diagnosis", new Long(117), diagnosis.getId());
    }

    @Test
    public void testGetDiagnosisUnknown() {
        Diagnosis diagnosis = diagnosisDao.getDiagnosis(1172323L);
        assertNull("Diagnosis is not null", diagnosis);
    }

    @Test
    public void testGetDiagnosisByRadar() {
        Diagnosis diagnosis = diagnosisDao.getDiagnosisByRadarNumber(239);
        assertNotNull("Diagnosis is null", diagnosis);
        assertEquals("ID is wrong on diagnosis", new Long(114), diagnosis.getId());
        assertEquals("Radar number is wrong", new Long(239), diagnosis.getRadarNumber());
    }

    @Test
    public void testGetDiagnosisByRadarUnknown() {
        Diagnosis diagnosis = diagnosisDao.getDiagnosisByRadarNumber(1172323L);
        assertNull("Diagnosis is not null", diagnosis);
    }

    @Test
    public void testGetDiagnosisCode() {
        DiagnosisCode diagnosisCode = diagnosisDao.getDiagnosisCode(1L);
        assertNotNull("Diagnosis code was null", diagnosisCode);
        assertEquals("Wrong ID", new Long(1), diagnosisCode.getId());
        assertEquals("Wrong desc", "Steroid Resistant Nephrotic Syndrome ", diagnosisCode.getDescription());
        assertEquals("Wrong abbr", "SRNS", diagnosisCode.getAbbreviation());
    }

    @Test
    public void testGetDiagnosisCodeUnknown() {
        DiagnosisCode diagnosisCode = diagnosisDao.getDiagnosisCode(1231L);
        assertNull("Diagnosis code was not null", diagnosisCode);
    }

    @Test
    public void testGetDiagnosisCodes() {
        List<DiagnosisCode> diagnosisCodes = diagnosisDao.getDiagnosisCodes();
        assertNotNull("Diagnosis code list was null", diagnosisCodes);
        assertEquals("Wrong size", 2, diagnosisCodes.size());
    }

    @Test
    public void testGetKarotype() {
        Karotype karotype = diagnosisDao.getKarotype(1L);
        assertNotNull("Karotype is null", karotype);
        assertEquals("Wrong ID", new Long(1), karotype.getId());
        assertEquals("Wrong description", "XX", karotype.getDescription());
    }

    @Test
    public void testGetKarotypeUnknown() {
        Karotype karotype = diagnosisDao.getKarotype(123L);
        assertNull("Karotype is not null", karotype);
    }

    @Test
    public void testGetKarotypes() {
        List<Karotype> karotypes = diagnosisDao.getKarotypes();
        assertNotNull("Karotype list is null", karotypes);
        assertEquals("List is wrong size", 4, karotypes.size());
    }
}
