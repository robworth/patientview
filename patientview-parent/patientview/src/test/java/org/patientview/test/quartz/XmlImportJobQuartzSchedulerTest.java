package org.patientview.test.quartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.patientview.ibd.model.Allergy;
import org.patientview.ibd.model.MyIbd;
import org.patientview.ibd.model.Procedure;
import org.patientview.model.Patient;
import org.patientview.patientview.FindXmlFiles;
import org.patientview.patientview.XmlImportUtils;
import org.patientview.patientview.exception.XmlImportException;
import org.patientview.patientview.logging.AddLog;
import org.patientview.patientview.model.*;
import org.patientview.quartz.XmlImportJobQuartzScheduler;
import org.patientview.repository.PatientDao;
import org.patientview.service.*;
import org.patientview.service.ibd.IbdManager;
import org.patientview.service.impl.SpringApplicationContextBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import javax.inject.Inject;
import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml", "classpath:test-context.xml"})
@Transactional
public class XmlImportJobQuartzSchedulerTest {

    @Autowired
    private XmlImportJobQuartzScheduler xmlImportJobQuartzScheduler;

    private String xmlDirectory;

    private String[] fileEndings = {".xml", };

    @Inject
    private PatientDao patientDao;

    @Inject
    private CentreManager centreManager;

    @Inject
    private MedicineManager medicineManager;

    @Inject
    private LetterManager letterManager;

    @Inject
    private TestResultManager testResultManager;

    @Inject
    private IbdManager ibdManager;

    @Inject
    private DiagnosticManager diagnosticManager;

    @Inject
    private ImportManager importManager;

    @Inject
    private UnitManager unitManager;

    @Inject
    private LogEntryManager logEntryManager;

    @Inject
    private XmlImportUtils xmlImportUtils;

    @Inject
    private SpringApplicationContextBean springApplicationContextBean;

    @Test
    public void testRead() throws Exception {

        int xmlFilesSize = 0;

        String parentDir = ResourceUtils.getFile("classpath:schedule/rm301_1244_9876543210.xml").getParent();

        setXmlDirectory(parentDir);

        File[] xmlFiles = FindXmlFiles.findXmlFiles(xmlDirectory, fileEndings);
        if (xmlFiles != null) {
            xmlFilesSize = xmlFiles.length;
        }

        assertTrue("Can not read XML files", xmlFilesSize != 0);

        xmlImportJobQuartzScheduler.setXmlDirectory(parentDir);
        xmlImportJobQuartzScheduler.execute();

        List<Centre> centres = centreManager.getAll();

        assertEquals("Incorrect number of centres", 1, centres.size());
        assertEquals("Incorrect centre", "RM301", centres.get(0).getCentreCode());

        List<Patient> patients = patientDao.get("RM301");

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

        List<TestResult> results = testResultManager.get("9876543210", "RM301");

        assertEquals("Incorrect number of results", 3, results.size());

        List<Medicine> medicines = medicineManager.getAll();

        assertEquals("Incorrect number of medicines", 0, medicines.size());

    }

    /**
     * Test if importer handles test results outside date ranges specified
     *
     * Whole file needs to be rejected, and an email needs to be sent to RPV admin email
     *
     * @throws java.io.IOException
     */
    @Test
    public void testXmlParserCheckTestResultOutsideDataRangeInIBDFile() throws Exception {
        Resource xmlFileResource = springApplicationContextBean.getApplicationContext()
                .getResource("classpath:schedule/rm301_resultWithOutsideDaterange_9876543210/" +
                        "rm301_resultWithOutsideDaterange_9876543210.xml");

        xmlImportJobQuartzScheduler.setXmlDirectory(xmlFileResource.getFile().getParent());
        xmlImportJobQuartzScheduler.execute();

        checkNoDataHasBeenImportedFromIBDImportFile();

        checkLogEntry(xmlImportUtils.extractFromXMLFileNameNhsno(xmlFileResource.getFile().getName()),
                AddLog.PATIENT_DATA_FAIL);
    }

    /**
     * Test if importer handles empty test file. This probably means that the encryption did not work.
     *
     * An email should be sent to RPV admin email address and an entry should be created in log table
     *
     * @throws java.io.IOException
     */
    @Test
    public void testXmlParserUsingEmptyIBDFile() throws Exception {
        Resource xmlFileResource = springApplicationContextBean.getApplicationContext()
                .getResource("classpath:schedule/rm301_empty_9876543210/rm301_empty_9876543210.xml");

        xmlImportJobQuartzScheduler.setXmlDirectory(xmlFileResource.getFile().getParent());
        xmlImportJobQuartzScheduler.execute();

        checkNoDataHasBeenImportedFromIBDImportFile();

        checkLogEntry(xmlImportUtils.extractFromXMLFileNameNhsno(xmlFileResource.getFile().getName()),
                AddLog.PATIENT_DATA_FAIL);
    }

    /**
     * Test if importer handles test results with future date
     *
     * The whole file should be rejected, an email should be sent to RPV admin email, and a "patient data fail"
     *      entry should be added to the log table
     *
     * @throws java.io.IOException
     */
    @Test
    public void testXmlParserCheckFutureTestResultDateInIBDFile() throws Exception {
        Resource xmlFileResource = springApplicationContextBean.getApplicationContext()
                .getResource("classpath:schedule/rm301_resultWithFutureDate_9876543210/" +
                        "rm301_resultWithFutureDate_9876543210.xml");

        xmlImportJobQuartzScheduler.setXmlDirectory(xmlFileResource.getFile().getParent());
        xmlImportJobQuartzScheduler.execute();

        checkNoDataHasBeenImportedFromIBDImportFile();

        checkLogEntry(xmlImportUtils.extractFromXMLFileNameNhsno(xmlFileResource.getFile().getName()),
                AddLog.PATIENT_DATA_FAIL);
    }

    /**
     * Test if importer handles test results with empty values
     *
     * Whole file needs to be rejected, n email should be sent to RPV admin and the error should be logged.
     *
     * @throws java.io.IOException
     */
    @Test
    public void testXmlParserCheckTestResultWithEmptyValueInIBDFile() throws Exception {
        Resource xmlFileResource = springApplicationContextBean.getApplicationContext()
                .getResource("classpath:schedule/rm301_resultWithEmptyValue_9876543210/" +
                        "rm301_resultWithEmptyValue_9876543210.xml");

        xmlImportJobQuartzScheduler.setXmlDirectory(xmlFileResource.getFile().getParent());
        xmlImportJobQuartzScheduler.execute();

        checkNoDataHasBeenImportedFromIBDImportFile();

        checkLogEntry(xmlImportUtils.extractFromXMLFileNameNhsno(xmlFileResource.getFile().getName()),
                AddLog.PATIENT_DATA_FAIL);
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
     * Check if no data was imported
     */
    private void checkNoDataHasBeenImportedFromIBDImportFile() {
        List<Centre> centres = centreManager.getAll();
        assertEquals("Centres were imported although data file was supposed to be empty", 0, centres.size());
    }

    public void setXmlDirectory(String xmlDirectory) {
        this.xmlDirectory = xmlDirectory;
    }
}
