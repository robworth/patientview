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

import org.patientview.patientview.model.Diagnostic;
import org.patientview.patientview.model.enums.DiagnosticType;
import org.patientview.repository.DiagnosticDao;
import org.junit.Test;

import javax.inject.Inject;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 *
 */
public class DiagnosticDaoTest extends BaseDaoTest {

    @Inject
    private DiagnosticDao diagnosticDao;

    @Test
    public void testSaveGetDiagnostic() {
        Diagnostic diagnostic = new Diagnostic();
        diagnostic.setDatestamp(Calendar.getInstance());
        diagnostic.setDescription("description");
        diagnostic.setDiagnosticType(DiagnosticType.IMAGING);
        diagnostic.setNhsno("1234567890");
        diagnostic.setUnitcode("UNITCODE1");

        /**
         * add
         */
        diagnosticDao.save(diagnostic);

        assertTrue("Invalid id after save", diagnostic.getId() > 0);

        /**
         * get
         */
        Diagnostic savedDiagnostic = diagnosticDao.get(diagnostic.getId());

        assertEquals("Incorrect datestamp", diagnostic.getDatestamp(), savedDiagnostic.getDatestamp());
        assertEquals("Incorrect description", diagnostic.getDescription(), savedDiagnostic.getDescription());
        assertEquals("Incorrect type", diagnostic.getDiagnosticType(), savedDiagnostic.getDiagnosticType());

        /**
         * delete
         */
        diagnosticDao.delete(savedDiagnostic.getNhsno(), savedDiagnostic.getUnitcode());

        Diagnostic deletedDiagnostic = diagnosticDao.get(savedDiagnostic.getNhsno());
        assertNull("Can't delete diagnostic", deletedDiagnostic);
    }

    @Test
    public void testDeleteDiagnostic() {
        Diagnostic diagnostic = new Diagnostic();
        diagnostic.setDatestamp(Calendar.getInstance());
        diagnostic.setDescription("description");
        diagnostic.setDiagnosticType(DiagnosticType.IMAGING);
        diagnostic.setNhsno("1234567890");
        diagnostic.setUnitcode("UNITCODE1");

        /**
         * add
         */
        diagnosticDao.save(diagnostic);
        assertTrue("Invalid id after save", diagnostic.getId() > 0);

        /**
         * delete
         */
        diagnosticDao.delete(diagnostic.getNhsno(), diagnostic.getUnitcode());

        Diagnostic deletedDiagnostic = diagnosticDao.get(diagnostic.getNhsno());
        assertNull("Can't delete diagnostic", deletedDiagnostic);
    }

    @Test
    public void testGetList() {

        // create 6 records from 3 nhs nos, half imaging half endoscopy

        Diagnostic diagnostic = new Diagnostic();
        diagnostic.setDatestamp(Calendar.getInstance());
        diagnostic.setDescription("description1");
        diagnostic.setDiagnosticType(DiagnosticType.IMAGING);
        diagnostic.setNhsno("1234567890");
        diagnostic.setUnitcode("UNITCODE1");
        diagnosticDao.save(diagnostic);

        diagnostic = new Diagnostic();
        diagnostic.setDatestamp(Calendar.getInstance());
        diagnostic.setDescription("description2");
        diagnostic.setDiagnosticType(DiagnosticType.IMAGING);
        diagnostic.setNhsno("1234567891");
        diagnostic.setUnitcode("UNITCODE1");
        diagnosticDao.save(diagnostic);

        diagnostic = new Diagnostic();
        diagnostic.setDatestamp(Calendar.getInstance());
        diagnostic.setDescription("description3");
        diagnostic.setDiagnosticType(DiagnosticType.IMAGING);
        diagnostic.setNhsno("1234567890");
        diagnostic.setUnitcode("UNITCODE1");
        diagnosticDao.save(diagnostic);

        diagnostic = new Diagnostic();
        diagnostic.setDatestamp(Calendar.getInstance());
        diagnostic.setDescription("description4");
        diagnostic.setDiagnosticType(DiagnosticType.ENDOSCOPY);
        diagnostic.setNhsno("1234567891");
        diagnostic.setUnitcode("UNITCODE1");
        diagnosticDao.save(diagnostic);

        diagnostic = new Diagnostic();
        diagnostic.setDatestamp(Calendar.getInstance());
        diagnostic.setDescription("description5");
        diagnostic.setDiagnosticType(DiagnosticType.ENDOSCOPY);
        diagnostic.setNhsno("1234567890");
        diagnostic.setUnitcode("UNITCODE1");
        diagnosticDao.save(diagnostic);

        diagnostic = new Diagnostic();
        diagnostic.setDatestamp(Calendar.getInstance());
        diagnostic.setDescription("description6");
        diagnostic.setDiagnosticType(DiagnosticType.ENDOSCOPY);
        diagnostic.setNhsno("1234567891");
        diagnostic.setUnitcode("UNITCODE1");
        diagnosticDao.save(diagnostic);

        Set<String> nhsNos = new HashSet<String>();
        nhsNos.add("1234567890");

        List<Diagnostic> results = diagnosticDao.get(nhsNos, DiagnosticType.IMAGING);

        assertEquals("Incorrect number of results", 2, results.size());

        nhsNos.add("1234567891");

        results = diagnosticDao.get(nhsNos, DiagnosticType.IMAGING);

        assertEquals("Incorrect number of results", 3, results.size());

        diagnostic = new Diagnostic();
        diagnostic.setDatestamp(Calendar.getInstance());
        diagnostic.setDescription("description7");
        diagnostic.setDiagnosticType(DiagnosticType.ENDOSCOPY);
        diagnostic.setNhsno("1234567891");
        diagnostic.setUnitcode("UNITCODE1");
        diagnosticDao.save(diagnostic);

        results = diagnosticDao.get(nhsNos, DiagnosticType.ENDOSCOPY);

        assertEquals("Incorrect number of results", 4, results.size());
    }

    @Test
    public void testGetWithNullNhs() {
        List<Diagnostic> results = diagnosticDao.get(null, DiagnosticType.IMAGING);

        assertEquals("Incorrect number of results", 0, results.size());
    }

}
