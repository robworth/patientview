package org.patientview.test.quartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.patientview.model.ibd.Allergy;
import org.patientview.model.ibd.MyIbd;
import org.patientview.model.ibd.Procedure;
import org.patientview.model.Patient;
import org.patientview.model.patientview.Centre;
import org.patientview.model.patientview.Diagnostic;
import org.patientview.model.patientview.Letter;
import org.patientview.model.patientview.Medicine;
import org.patientview.model.patientview.TestResult;
import org.patientview.patientview.FindXmlFiles;
import org.patientview.quartz.XmlImportTask;
import org.patientview.repository.radar.PatientDao;
import org.patientview.service.*;
import org.patientview.service.ibd.IbdManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import javax.inject.Inject;
import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml", "classpath:test-context.xml"})
@Transactional
public class XmlImportTaskTest {

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

    public void setXmlDirectory(String xmlDirectory) {
        this.xmlDirectory = xmlDirectory;
    }

}
