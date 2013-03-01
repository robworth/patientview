package com.worthsoln.test.repository;

import com.worthsoln.patientview.model.Diagnostic;
import com.worthsoln.patientview.model.enums.DiagnosticType;
import com.worthsoln.repository.DiagnosticDao;
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
    public void testGetSave() {

        Diagnostic diagnostic = new Diagnostic();
        diagnostic.setDatestamp(Calendar.getInstance());
        diagnostic.setDescription("description");
        diagnostic.setDiagnosticType(DiagnosticType.IMAGING);
        diagnostic.setNhsno("1234567890");
        diagnostic.setUnitcode("UNITCODE1");

        diagnosticDao.save(diagnostic);

        assertTrue("Invalid id after save", diagnostic.getId() > 0);

        Diagnostic check = diagnosticDao.get(diagnostic.getId());

        assertEquals("Incorrect datestamp", diagnostic.getDatestamp(), check.getDatestamp());
        assertEquals("Incorrect description", diagnostic.getDescription(), check.getDescription());
        assertEquals("Incorrect type", diagnostic.getDiagnosticType(), check.getDiagnosticType());
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
