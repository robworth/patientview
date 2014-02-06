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

package org.patientview.quartz;

import com.Ostermiller.util.CSVPrinter;
import org.patientview.model.Patient;
import org.patientview.service.UKTransplantManager;
import org.patientview.utils.LegacySpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Inject;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *  Handle the import and export of UK transplant information for patients
 */
public class UktImportExportScheduler {

    private static final SimpleDateFormat EXPORT_DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd");

    @Value("${run.ukt.import.export}")
    private String runUkt;

    @Value("${ukt.directory}")
    private String uktDirectory;

    @Value("${uktexport.directory}")
    private String uktExportDirectory;

    @Inject
    private UKTransplantManager ukTransplantManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(UktImportExportScheduler.class);

    public void execute() {
        if ((runUkt != null) && runUkt.equalsIgnoreCase("true")) {
            importUktData();
            exportUktData();
            exportPatientData();
        }
    }

    private void exportPatientData() {

        try {
            ukTransplantManager.exportPatientData();
        } catch (Exception e) {
            LOGGER.error("Failed to exportPatientData: {}", e.getMessage());
            LOGGER.debug(e.getMessage(), e);
        }

        LOGGER.info("Completed exportPatientData()");
    }

    private void importUktData() {
        int numFilesImported = 0;
        try {
            File uktDir = new File(uktDirectory);
            File[] uktFiles = uktDir.listFiles(new UktFileFilter());
            if (uktFiles != null && uktFiles.length > 0) {
                // this is legacy logic - but there should only be one file, only the most recent matters
                for (File uktFile : uktFiles) {
                    ukTransplantManager.importUktStatusData(uktFile);
                    numFilesImported++;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Failed to importUktData: {}", e.getMessage());
            LOGGER.debug(e.getMessage(), e);
        }

        LOGGER.info("Completed importUktData, imported {} files", numFilesImported);
    }

    private void exportUktData() {
        try {
            File uktExportDir = new File(uktExportDirectory);
            File uktExportFile = new File(uktExportDir, "ukt_rpv_export.txt");
            if (!uktExportFile.isFile()) {
                if (!uktExportFile.createNewFile()) {
                    LOGGER.error("Failed to exportUktData: uktExportFile is not a file");
                    return;
                }
            }

            CSVPrinter csv = new CSVPrinter(new FileWriter(uktExportFile));
            csv.setAlwaysQuote(true);
            csv.writeln(getPatients());
            csv.flush();
            csv.close();
            LOGGER.info("Completed exportUktData()");
        } catch (Exception e) {
            LOGGER.error("Failed to exportUktData: {}", e.getMessage());
            LOGGER.debug(e.getMessage(), e);
        }
    }

    // todo this should be moved out of here
    private static final int NUMBER_OF_COLUMNS = 5;
    private static final int THREE = 3;
    private static final int FOUR = 4;

    private String[][] getPatients() {
        List patientList = LegacySpringUtils.getPatientManager().getUktPatients();
        String[][] patientArray = new String[patientList.size()][NUMBER_OF_COLUMNS];
        for (int i = 0; i < patientList.size(); i++) {
            Patient patient = (Patient) patientList.get(i);
            patientArray[i][0] = (patient.getNhsno() == null) ? "" : patient.getNhsno();
            patientArray[i][1] = cleanName(patient.getSurname());
            patientArray[i][2] = cleanName(patient.getForename());
            patientArray[i][THREE] = (patient.getDob() == null) ? "" : EXPORT_DATE_FORMAT.format(patient.getDob());
            patientArray[i][FOUR] = (patient.getPostcode() == null) ? "" : patient.getPostcode();
        }
        return patientArray;
    }

    private String cleanName(String name) {
        if (name == null) {
            name = "";
        } else {
            name = name.replaceAll("\"", "");
            name = name.replaceAll("&quot;", "");
        }
        return name;
    }

    private class UktFileFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return name.endsWith("uktstatus.gpg.txt");
        }
    }

    public void setUktDirectory(String uktDirectory) {
        this.uktDirectory = uktDirectory;
    }

    public void setUktExportDirectory(String uktExportDirectory) {
        this.uktExportDirectory = uktExportDirectory;
    }
}


