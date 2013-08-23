package org.patientview.test.batch;

import org.junit.Test;
import org.patientview.batch.XmlImportReader;
import org.patientview.ibd.model.Allergy;
import org.patientview.ibd.model.MyIbd;
import org.patientview.ibd.model.Procedure;
import org.patientview.model.Patient;
import org.patientview.patientview.FindXmlFiles;
import org.patientview.patientview.model.*;
import org.patientview.repository.PatientDao;
import org.patientview.service.*;
import org.patientview.service.ibd.IbdManager;
import org.patientview.test.helpers.ServiceHelpers;
import org.patientview.test.service.BaseServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import javax.inject.Inject;
import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 *  Use the 'test/resources/rm301_1244_9876543210.xml' and "test/resources/test.uktstatus.gpg.txt" to do this test,
 *  The directory that mentioned in properties should be existed before run the test.
 *  copy these files to directory that config in .properties
 *
 *  e.x. in properties: "home.rpv.documents.work.rpv.app=E:/file"
 *  copy the test/resources/rm301_1244_9876543210.xml to E:/file
 *  copy the test/resources/test.uktstatus.gpg.txt to E:/file/ukt_import
 */
public class XmlImportReaderTest extends BaseBatchTest {

    private String xmlDirectory;

    private String uktDirectory;

    private String[] fileEndings = {".xml", };

    @Autowired
    private XmlImportReader xmlImportReader;

    @Inject
    private ServiceHelpers serviceHelpers;

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
    private UKTransplantManager ukTransplantManager;

    @Test
    public void testRead() throws Exception {

        int uktFilesSize = 0;
        int xmlFilesSize = 0;
        String parentDir = ResourceUtils.getFile("classpath:schedule/test-uktstatus.gpg.txt").getParent();
        setUktDirectory(parentDir);
        setXmlDirectory(parentDir);

        Specialty specialty = serviceHelpers.createSpecialty("Specialty 1", "Specialty1", "Test description");
        User user1 = serviceHelpers.createUserWithMapping("testuser1", "paul@test.com", "p", "Testuser1", "RM301",
                "9876543210", specialty);

        File uktDir = new File(uktDirectory);
        File[] uktFiles = uktDir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith("uktstatus.gpg.txt");
            }
        });

        if (uktFiles != null) {
            uktFilesSize = uktFiles.length;
        }

        File[] xmlFiles = FindXmlFiles.findXmlFiles(xmlDirectory, fileEndings);
        if (xmlFiles != null) {
            xmlFilesSize = xmlFiles.length;
        }

        xmlImportReader.setXmlDirectory(parentDir);
        xmlImportReader.setUktDirectory(parentDir);
        xmlImportReader.setUktExportDirectory(parentDir);
        xmlImportReader.refresh();

        if (xmlFilesSize > 0) {
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
        } else {
            List<Centre> centres = centreManager.getAll();

            assertEquals("Incorrect number of centres", 0, centres.size());

            List<Patient> patients = patientDao.get("RM301");

            assertEquals("Incorrect number of patients", 0, patients.size());

            List<Letter> letters = letterManager.getAll();

            assertEquals("Incorrect number of letters", 0, letters.size());

            MyIbd myIbd = ibdManager.getMyIbd("9876543210");
            assertNull("No MyIbd information was parsed", myIbd);

            Diagnostic diagnostic = diagnosticManager.get("9876543210");
            assertNull("No diagnostic information was parsed", diagnostic);

            Procedure procedure = ibdManager.getProcedure("9876543210");
            assertNull("No procedure information was parsed", procedure);

            Allergy allergy = ibdManager.getAllergy("9876543210");
            assertNull("No allergy information was parsed", allergy);

            List<TestResult> results = testResultManager.get("9876543210", "RM301");

            assertEquals("Incorrect number of results", 0, results.size());

            List<Medicine> medicines = medicineManager.getAll();

            assertEquals("Incorrect number of medicines", 0, medicines.size());
        }

        UktStatus uktStatus = ukTransplantManager.getUktStatus("9876543210");
        if (uktFilesSize > 0) {
            assertNotNull("UktStatus not be saved", uktStatus);
        } else {
            assertNull("Wrong entity exists.", uktStatus);
        }


    }

    public void setXmlDirectory(String xmlDirectory) {
        this.xmlDirectory = xmlDirectory;
    }

    public void setUktDirectory(String uktDirectory) {
        this.uktDirectory = uktDirectory;
    }
}
