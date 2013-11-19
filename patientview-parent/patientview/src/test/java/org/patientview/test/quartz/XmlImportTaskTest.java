package org.patientview.test.quartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.patientview.common.test.BaseTestPvDbSchema;
import org.patientview.ibd.model.Allergy;
import org.patientview.ibd.model.MyIbd;
import org.patientview.ibd.model.Procedure;
import org.patientview.model.Patient;
import org.patientview.patientview.FindXmlFiles;
import org.patientview.patientview.model.Centre;
import org.patientview.patientview.model.Diagnostic;
import org.patientview.patientview.model.Letter;
import org.patientview.patientview.model.Medicine;
import org.patientview.patientview.model.Specialty;
import org.patientview.patientview.model.TestResult;
import org.patientview.patientview.model.Unit;
import org.patientview.quartz.XmlImportTask;
import org.patientview.quartz.exception.ProcessException;
import org.patientview.repository.PatientDao;
import org.patientview.repository.UnitDao;
import org.patientview.service.CentreManager;
import org.patientview.service.DiagnosticManager;
import org.patientview.service.ImportManager;
import org.patientview.service.LetterManager;
import org.patientview.service.MedicineManager;
import org.patientview.service.TestResultManager;
import org.patientview.service.ibd.IbdManager;
import org.patientview.test.helpers.ServiceHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml", "classpath*:test-context.xml"})
@Transactional
public class XmlImportTaskTest extends BaseTestPvDbSchema {

    @Autowired
    private XmlImportTask xmlImport;

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
    private ServiceHelpers serviceHelpers;

    @Inject
    private UnitDao unitDao;

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

        xmlImport.setXmlDirectory(parentDir);
        xmlImport.execute();

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

    @Test
    public void testImport() throws Exception {

        int xmlFilesSize = 0;

        String parentDir
                = ResourceUtils.getFile("classpath:schedule/" +
                "TestUnitA_1244_9876543210/TestUnitA_1244_9876543210.xml").getParent();

        setXmlDirectory(parentDir);

        File[] xmlFiles = FindXmlFiles.findXmlFiles(xmlDirectory, fileEndings);
        if (xmlFiles != null) {
            xmlFilesSize = xmlFiles.length;
        }

        assertTrue("Can not read XML files", xmlFilesSize != 0);

        Specialty specialty1 = serviceHelpers.createSpecialty("Specialty 1", "Specialty1", "Test description");
        Unit unit = new Unit();
        unit.setUnitcode("TestUnitA");
        unit.setName("Test Data For Unit");
        unit.setShortname("Test Data");
        unit.setSpecialty(specialty1);

        unitDao.save(unit);
        assertNull("Unit last import date is not null", unit.getLastImportDate());

        xmlImport.setXmlDirectory(parentDir);
        xmlImport.execute();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        Unit checkUnit = unitDao.get("TestUnitA", specialty1);
        assertNotNull("Unit last import date is null", checkUnit.getLastImportDate());
        assertEquals("Unit last import date updated not correct",
                simpleDateFormat.format(date), simpleDateFormat.format(checkUnit.getLastImportDate()));

    }

    /**
     * This test is to create a data exception on the patient record. The exception is the lack of a
     * nhs no.
     *
     * @throws Exception
     */
    @Test (expected = ProcessException.class)
    public void testLoadXmlFileContainingNoNhsNumber() throws ProcessException, IOException {

        File  file = ResourceUtils.getFile("classpath:RENALB_nhsnomissing_TEST111111.gpg.xml");

        importManager.process(file);

    }

    public void setXmlDirectory(String xmlDirectory) {
        this.xmlDirectory = xmlDirectory;
    }

}
