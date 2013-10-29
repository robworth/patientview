package org.patientview.test.quartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.patientview.model.patientview.UktStatus;
import org.patientview.quartz.UktImportExportScheduler;
import org.patientview.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import javax.inject.Inject;
import java.io.File;
import java.io.FilenameFilter;

import static org.junit.Assert.*;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
@Transactional
public class UktImportExportSchedulerTest {

    @Autowired
    private UktImportExportScheduler uktImportExportScheduler;

    private String uktDirectory;

    @Inject
    private UKTransplantManager ukTransplantManager;

    @Test
    public void testExecute() throws Exception {

        int uktFilesSize = 0;

        String parentDir = ResourceUtils.getFile("classpath:schedule/test-uktstatus.gpg.txt").getParent();

        setUktDirectory(parentDir);

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

        uktImportExportScheduler.setUktDirectory(parentDir);
        uktImportExportScheduler.execute();

        UktStatus uktStatus = ukTransplantManager.getUktStatus("9876543210");

        if (uktFilesSize > 0) {
            assertNotNull("UktStatus not be saved", uktStatus);
        } else {
            assertNull("Wrong entity exists.", uktStatus);
        }
    }

    public void setUktDirectory(String uktDirectory) {
        this.uktDirectory = uktDirectory;
    }

}
