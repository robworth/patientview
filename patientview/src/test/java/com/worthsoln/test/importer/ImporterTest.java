package com.worthsoln.test.importer;

import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.ibd.model.Allergy;
import com.worthsoln.ibd.model.MyIbd;
import com.worthsoln.ibd.model.Procedure;
import com.worthsoln.patientview.model.Centre;
import com.worthsoln.patientview.model.Diagnostic;
import com.worthsoln.patientview.model.Letter;
import com.worthsoln.patientview.model.Medicine;
import com.worthsoln.patientview.model.Patient;
import com.worthsoln.patientview.model.TestResult;
import com.worthsoln.service.CentreManager;
import com.worthsoln.service.DiagnosticManager;
import com.worthsoln.service.LetterManager;
import com.worthsoln.service.MedicineManager;
import com.worthsoln.service.PatientManager;
import com.worthsoln.service.TestResultManager;
import com.worthsoln.service.ibd.IbdManager;
import com.worthsoln.service.impl.SpringApplicationContextBean;
import com.worthsoln.test.helpers.impl.TestableResultsUpdater;
import com.worthsoln.test.service.BaseServiceTest;
import org.junit.Test;
import org.springframework.core.io.Resource;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 *      The importer is kicked off from ParserMonitorServlet.
 *
 *      There are 3 threads - XmlParserThread, UktParserThread & UktExportThread.
 *
 *      There are 2 versions of the patient view xml schema in the examples directory.
 *
 *      - pv_schema_1.0.xml - used by rpv
 *      - pv_schema_2.0.xml - used by ibd
 */
public class ImporterTest extends BaseServiceTest {

    @Inject
    private SpringApplicationContextBean springApplicationContextBean;

    @Inject
    private CentreManager centreManager;

    @Inject
    private LetterManager letterManager;

    @Inject
    private DiagnosticManager diagnosticManager;

    @Inject
    private MedicineManager medicineManager;

    @Inject
    private PatientManager patientManager;

    @Inject
    private TestResultManager testResultManager;

    @Inject
    private IbdManager ibdManager;

    @Test
    /**
     *  Calls XmlParserUtils.updateXmlData with files and a dao ref
     *
     *      - ResultParser
     *          - parseResults
     *          - removePatientFromSystem
     *          - updatePatientData
     *              - updatePatientDetails
     *              - updateCentreDetails
     *              - deleteDateRanges
     *              - insertResults
     *              - deleteLetters
     *              - insertLetters
     *              - deleteOtherDiagnoses
     *              - insertOtherDiagnoses
     *              - deleteMedicines
     *              - insertMedicines
     */
    public void testXmlParserUsingRpvFile() throws IOException {

        Resource xmlFileResource = springApplicationContextBean.getApplicationContext()
                .getResource("classpath:A_00794_1234567890.gpg.xml");

        DatabaseDAO dao = new DatabaseDAO("patientview");
        TestableResultsUpdater testableResultsUpdater = new TestableResultsUpdater(dao);

        testableResultsUpdater.update(null, xmlFileResource.getFile());

        List<Centre> centres = centreManager.getAll();

        assertEquals("Incorrect number of centres", 1, centres.size());
        assertEquals("Incorrect centre", "A", centres.get(0).getCentreCode());

        List<Patient> patients = patientManager.get("A");

        assertEquals("Incorrect number of patients", 1, patients.size());
        assertEquals("Incorrect patient", "1234567890", patients.get(0).getNhsno());

        List<TestResult> results = testResultManager.get("1234567890", "A");

        assertEquals("Incorrect number of results", 316, results.size());

        List<Medicine> medicines = medicineManager.getAll();

        assertEquals("Incorrect number of medicines", 8, medicines.size());

        List<Letter> letters = letterManager.getAll();

        assertEquals("Incorrect number of letters", 2, letters.size());
    }

    @Test
    public void testXmlParserUsingIBDFile() throws IOException {
        Resource xmlFileResource = springApplicationContextBean.getApplicationContext()
                        .getResource("classpath:rm301_1244_9876543210.xml");

        DatabaseDAO dao = new DatabaseDAO("patientview");
        TestableResultsUpdater testableResultsUpdater = new TestableResultsUpdater(dao);

        testableResultsUpdater.update(null, xmlFileResource.getFile());

        checkIbdImportConstantData();

        List<TestResult> results = testResultManager.get("9876543210", "RM301");

        assertEquals("Incorrect number of results", 3, results.size());
    }

    /**
     *  If you run the import twice for the same file we still have the same data set
     */
    @Test
    public void testXmlParserUsingIBDFileMultipleRuns() throws IOException {
        Resource xmlFileResource = springApplicationContextBean.getApplicationContext()
                .getResource("classpath:rm301_1244_9876543210.xml");

        DatabaseDAO dao = new DatabaseDAO("patientview");
        TestableResultsUpdater testableResultsUpdater = new TestableResultsUpdater(dao);

        // run twice
        testableResultsUpdater.update(null, xmlFileResource.getFile());
        testableResultsUpdater.update(null, xmlFileResource.getFile());

        checkIbdImportConstantData();

        // Note the results get deleted each run on date range
    }

    private void checkIbdImportConstantData() {

        // test the stuff that should be the same regardless of how many imports of the file are done

        List<Centre> centres = centreManager.getAll();

        assertEquals("Incorrect number of centres", 1, centres.size());
        assertEquals("Incorrect centre", "RM301", centres.get(0).getCentreCode());

        List<Patient> patients = patientManager.get("RM301");

        assertEquals("Incorrect number of patients", 1, patients.size());
        assertEquals("Incorrect patient", "9876543210", patients.get(0).getNhsno());

        List<Letter> letters = letterManager.getAll();

        assertEquals("Incorrect number of letters", 2, letters.size());

        MyIbd myIbd = ibdManager.getMyIbd("9876543210");
        assertNotNull("No MyIbd information was parsed", myIbd);

        Diagnostic diagnostic = diagnosticManager.get("9876543210");
        assertNotNull("No diagnostic information was parsed", diagnostic);

        Procedure procedure = ibdManager.getProcedure("9876543210");
        assertNotNull("No procedure information was parsed", procedure);

        Allergy allergy = ibdManager.getAllergy("9876543210");
        assertNotNull("No allergy information was parsed", allergy);
    }
}
