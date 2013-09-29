package org.patientview.test.quartz;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.patientview.batch.XmlImportReader;
import org.patientview.ibd.model.Allergy;
import org.patientview.ibd.model.MyIbd;
import org.patientview.ibd.model.Procedure;
import org.patientview.model.Patient;
import org.patientview.patientview.FindXmlFiles;
import org.patientview.patientview.model.*;
import org.patientview.quartz.XmlImportJobQuartzScheduler;
import org.patientview.repository.PatientDao;
import org.patientview.service.*;
import org.patientview.service.ibd.IbdManager;
import org.patientview.test.helpers.ServiceHelpers;
import org.springframework.beans.factory.annotation.Autowired;
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

    private String uktDirectory;

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
    private UKTransplantManager ukTransplantManager;

    @Test
    public void testRead() throws Exception {

        int uktFilesSize = 0;
        int xmlFilesSize = 0;

        String parentDir = ResourceUtils.getFile("classpath:schedule/test-uktstatus.gpg.txt").getParent();

        setUktDirectory(parentDir);
        setXmlDirectory(parentDir);

        File uktDir = new File(uktDirectory);
        File[] uktFiles = uktDir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith("uktstatus.gpg.txt");
            }
        });

        if (uktFiles != null) {
            uktFilesSize = uktFiles.length;
        }

        assertTrue("Can not read UKT files", uktFilesSize != 0);

        File[] xmlFiles = FindXmlFiles.findXmlFiles(xmlDirectory, fileEndings);
        if (xmlFiles != null) {
            xmlFilesSize = xmlFiles.length;
        }

        assertTrue("Can not read XML files", xmlFilesSize != 0);

        xmlImportJobQuartzScheduler.setXmlDirectory(parentDir);
        xmlImportJobQuartzScheduler.setUktDirectory(parentDir);
        xmlImportJobQuartzScheduler.setUktExportDirectory(parentDir);
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
