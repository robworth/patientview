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

package org.patientview.test.importer;

import org.junit.Before;
import org.junit.Test;
import org.patientview.ibd.model.Allergy;
import org.patientview.ibd.model.MyIbd;
import org.patientview.ibd.model.Procedure;
import org.patientview.patientview.XmlImportUtils;
import org.patientview.patientview.logging.AddLog;
import org.patientview.patientview.model.Centre;
import org.patientview.patientview.model.Diagnostic;
import org.patientview.patientview.model.Letter;
import org.patientview.patientview.model.Medicine;
import org.patientview.patientview.model.Patient;
import org.patientview.patientview.model.Specialty;
import org.patientview.patientview.model.TestResult;
import org.patientview.patientview.model.Unit;
import org.patientview.patientview.parser.XmlParserUtils;
import org.patientview.service.CentreManager;
import org.patientview.service.DiagnosticManager;
import org.patientview.service.ImportManager;
import org.patientview.service.LetterManager;
import org.patientview.service.LogEntryManager;
import org.patientview.service.MedicineManager;
import org.patientview.service.PatientManager;
import org.patientview.service.TestResultManager;
import org.patientview.service.TimeManager;
import org.patientview.service.UnitManager;
import org.patientview.service.ibd.IbdManager;
import org.patientview.service.impl.SpringApplicationContextBean;
import org.patientview.test.helpers.ServiceHelpers;
import org.patientview.test.service.BaseServiceTest;
import org.patientview.utils.LegacySpringUtils;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockHttpSession;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * The importer is kicked off from ParserMonitorServlet.
 *
 * There are 3 threads - XmlParserThread, UktParserThread & UktExportThread.
 *
 * There are 2 versions of the patient view xml schema in the examples directory.
 *
 * - pv_schema_1.0.xml - used by rpv
 * - pv_schema_2.0.xml - used by ibd
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
    private ImportManager importManager;

    @Inject
    private MedicineManager medicineManager;

    @Inject
    private PatientManager patientManager;

    @Inject
    private TestResultManager testResultManager;

    @Inject
    private IbdManager ibdManager;

    @Inject
    private UnitManager unitManager;

    @Inject
    private ServiceHelpers serviceHelpers;

    @Inject
    private LogEntryManager logEntryManager;

    @Before
    public void setupSystem() {
        Unit mockUnit = new Unit();
        mockUnit.setUnitcode("RM301");
        mockUnit.setName("RM301: RUNNING MAN TEST UNIT");
        mockUnit.setShortname("RM301");
        mockUnit.setRenaladminemail("renaladmin@mailinator.com");

        Specialty mockSpecialty = new Specialty();
        mockSpecialty.setName("Renal Patient View");
        mockSpecialty.setContext("renal");
        mockSpecialty.setDescription("Renal Patient View");

        mockSpecialty = serviceHelpers.createSpecialty("Specialty1", "ten1", "A test specialty");

        mockUnit.setSpecialty(mockSpecialty);

        unitManager.save(mockUnit);
    }

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
    public void testXmlParserUsingRenalFile() throws Exception {

        Resource xmlFileResource = springApplicationContextBean.getApplicationContext()
                .getResource("classpath:A_00794_1234567890.gpg.xml");

        MockHttpSession mockHttpSession = new MockHttpSession();

        XmlParserUtils.updateXmlData(mockHttpSession.getServletContext(), xmlFileResource.getFile());

        checkLogEntry(XmlImportUtils.extractFromXMLFileNameNhsno(xmlFileResource.getFile().getName()),
                AddLog.PATIENT_DATA_FOLLOWUP);

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
    public void testTestResultIsNotDuplicatedIfDoubleRun() throws Exception {
        Resource xmlFileResource = springApplicationContextBean.getApplicationContext()
                .getResource("classpath:DUMMY_000002_9999999995.gpg.xml");

        MockHttpSession mockHttpSession = new MockHttpSession();

        XmlParserUtils.updateXmlData(mockHttpSession.getServletContext(), xmlFileResource.getFile());

        List<TestResult> results = testResultManager.get("9999999995", "DUMMY");

        assertEquals("Incorrect number of results after first import", 1, results.size());

        // double run
        XmlParserUtils.updateXmlData(mockHttpSession.getServletContext(), xmlFileResource.getFile());

        results = testResultManager.get("9999999995", "DUMMY");

        assertEquals("Incorrect number of results after double run import", 1, results.size());

        checkLogEntry(XmlImportUtils.extractFromXMLFileNameNhsno(xmlFileResource.getFile().getName()),
                AddLog.PATIENT_DATA_FOLLOWUP);
    }

    /**
     * Test if importer handles empty test file. This probably means that the encryption did not work.
     *
     * An email should be sent to RPV admin email address and an entry should be created in log table
     *
     * @throws IOException
     */
    @Test
    public void testXmlParserUsingEmptyIBDFile() throws Exception {
        Resource xmlFileResource = springApplicationContextBean.getApplicationContext()
                .getResource("classpath:rm301_empty_9876543210.xml");
        MockHttpSession mockHttpSession = new MockHttpSession();
        XmlParserUtils.updateXmlData(mockHttpSession.getServletContext(), xmlFileResource.getFile());

        checkNoDataHasBeenImportedFromIBDImportFile();
        checkLogEntry(XmlImportUtils.extractFromXMLFileNameNhsno(xmlFileResource.getFile().getName()),
                AddLog.PATIENT_DATA_FAIL);
    }

    /**
     * Check if no data was imported
     */
    private void checkNoDataHasBeenImportedFromIBDImportFile() {
        List<Centre> centres = centreManager.getAll();
        assertEquals("Centres were imported although data file was supposed to be empty", 0, centres.size());

        List<Unit> units = unitManager.getAll(false);
        /**
         * {@link #setupSystem()} creates one unit so its ok if we have one unit now
         */
        assertEquals("Units were imported although data file was supposed to be empty", 1, units.size());
    }

    /**
     * Check if log entry was created
     *
     * @param nhsNo  nhsNo of patient
     * @param action log type
     */
    private void checkLogEntry(String nhsNo, String action) {
        assertNotNull("Log entry was not created", logEntryManager.getLatestLogEntry(nhsNo, action));
    }

    /**
     * Test if importer handles test results with future date
     *
     * The whole file should be rejected, an email should be sent to RPV admin email, and a "patient data fail"
     *      entry should be added to the log table
     *
     * @throws IOException
     */
    @Test
    public void testXmlParserCheckFutureTestResultDateInIBDFile() throws Exception {
        Resource xmlFileResource = springApplicationContextBean.getApplicationContext()
                .getResource("classpath:rm301_resultWithFutureDate_9876543210.xml");

        MockHttpSession mockHttpSession = new MockHttpSession();

        XmlParserUtils.updateXmlData(mockHttpSession.getServletContext(), xmlFileResource.getFile());

        checkNoDataHasBeenImportedFromIBDImportFile();

        checkLogEntry(XmlImportUtils.extractFromXMLFileNameNhsno(xmlFileResource.getFile().getName()),
                AddLog.PATIENT_DATA_FAIL);
    }

    /**
     * Test if importer handles test results outside date ranges specified
     *
     * Whole file needs to be rejected, and an email needs to be sent to RPV admin email
     *
     * @throws IOException
     */
    @Test
    public void testXmlParserCheckTestResultOutsideDataRangeInIBDFile() throws Exception {
        Resource xmlFileResource = springApplicationContextBean.getApplicationContext()
                .getResource("classpath:rm301_resultWithOutsideDaterange_9876543210.xml");

        MockHttpSession mockHttpSession = new MockHttpSession();

        XmlParserUtils.updateXmlData(mockHttpSession.getServletContext(), xmlFileResource.getFile());

        checkNoDataHasBeenImportedFromIBDImportFile();

        checkLogEntry(XmlImportUtils.extractFromXMLFileNameNhsno(xmlFileResource.getFile().getName()),
                AddLog.PATIENT_DATA_FAIL);
    }

    /**
     * Test if importer handles test results with valid dates including edge cases.
     *
     * @throws IOException
     */
    @Test
    public void testXmlParserCheckTestResultWithValidDates() throws Exception {

        /**
         *  Fix the current date to always be the same.
         *
         *  Note: this only overrides the behaviour of the timeManager reference used by LegacySpringUtils
         *  If you need to change application wide, add a new implementation to the text-context.xml
         */
        LegacySpringUtils.setTimeManager(new TimeManager() {
            @Override
            public Date getCurrentDate() {
                Calendar calendar = Calendar.getInstance();
                calendar.set(2013, Calendar.MARCH, 29, 11, 23, 0);
                return calendar.getTime();
            }
        });

        Resource xmlFileResource = springApplicationContextBean.getApplicationContext()
                .getResource("classpath:rm301_resultWithValidDates_9876543210.xml");

        MockHttpSession mockHttpSession = new MockHttpSession();

        XmlParserUtils.updateXmlData(mockHttpSession.getServletContext(), xmlFileResource.getFile());

        checkLogEntry(XmlImportUtils.extractFromXMLFileNameNhsno(xmlFileResource.getFile().getName()),
                AddLog.PATIENT_DATA_FOLLOWUP);
    }

    /**
     * Test if importer handles test results with empty values
     *
     * Whole file needs to be rejected, n email should be sent to RPV admin and the error should be logged.
     *
     * @throws IOException
     */
    @Test
    public void testXmlParserCheckTestResultWithEmptyValueInIBDFile() throws Exception {
        Resource xmlFileResource = springApplicationContextBean.getApplicationContext()
                .getResource("classpath:rm301_resultWithEmptyValue_9876543210.xml");

        MockHttpSession mockHttpSession = new MockHttpSession();

        XmlParserUtils.updateXmlData(mockHttpSession.getServletContext(), xmlFileResource.getFile());

        checkNoDataHasBeenImportedFromIBDImportFile();

        checkLogEntry(XmlImportUtils.extractFromXMLFileNameNhsno(xmlFileResource.getFile().getName()),
                AddLog.PATIENT_DATA_FAIL);
    }

    /**
     * Test if the importer imports data
     *
     * @throws IOException
     */
    @Test
    public void testXmlParserUsingIBDFile() throws Exception {
        Resource xmlFileResource = springApplicationContextBean.getApplicationContext()
                .getResource("classpath:rm301_1244_9876543210.xml");

        MockHttpSession mockHttpSession = new MockHttpSession();

        XmlParserUtils.updateXmlData(mockHttpSession.getServletContext(), xmlFileResource.getFile());

        checkLogEntry(XmlImportUtils.extractFromXMLFileNameNhsno(xmlFileResource.getFile().getName()),
                AddLog.PATIENT_DATA_FOLLOWUP);

        checkIbdImportedData();

        List<TestResult> results = testResultManager.get("9876543210", "RM301");

        assertEquals("Incorrect number of results", 3, results.size());
    }

    /**
     * If you run the import twice for the same file we still have the same data set
     */
    @Test
    public void testXmlParserUsingIBDFileMultipleRuns() throws Exception {
        Resource xmlFileResource = springApplicationContextBean.getApplicationContext()
                .getResource("classpath:rm301_1244_9876543210.xml");

        MockHttpSession mockHttpSession = new MockHttpSession();

        // run twice
        XmlParserUtils.updateXmlData(mockHttpSession.getServletContext(), xmlFileResource.getFile());
        XmlParserUtils.updateXmlData(mockHttpSession.getServletContext(), xmlFileResource.getFile());

        checkLogEntry(XmlImportUtils.extractFromXMLFileNameNhsno(xmlFileResource.getFile().getName()),
                AddLog.PATIENT_DATA_FOLLOWUP);

        checkIbdImportedData();

        // Note the results get deleted each run on date range
    }

    /**
     * check the data importer should have imported.
     */
    private void checkIbdImportedData() {
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