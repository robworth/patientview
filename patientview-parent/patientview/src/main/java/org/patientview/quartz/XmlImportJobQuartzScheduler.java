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

import org.patientview.patientview.exception.EmptyImportFileException;
import org.patientview.patientview.exception.ImportFileValidationException;
import org.patientview.patientview.FindXmlFiles;
import org.patientview.patientview.XmlImportUtils;
import org.patientview.patientview.logging.AddLog;
import org.patientview.utils.LegacySpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Inject;
import java.io.File;

/**
 * Quartz XmlImportJobQuartzScheduler Job
 */
public class XmlImportJobQuartzScheduler {

    @Value("${run.xml.import}")
    private String runXMLImport;

    @Value("${xml.directory}")
    private String xmlDirectory;

    private String[] fileEndings = {".xml", };

    @Inject
    private XmlImportUtils xmlImportUtils;

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlImportJobQuartzScheduler.class);

    public void execute() {
        try {
            updateXmlFiles();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.debug(e.getMessage(), e);
        }
    }

    private void updateXmlFiles() {
        int numFilesProcessed = 0;
        if ((runXMLImport == null) || !"false".equals(runXMLImport)) {
            File[] xmlFiles = FindXmlFiles.findXmlFiles(xmlDirectory, fileEndings);
            if (xmlFiles != null && xmlFiles.length > 0) {
                LOGGER.debug("Starting updateXmlFiles for {} files", xmlFiles.length);
                for (File xmlFile : xmlFiles) {
                    LOGGER.debug("Starting updateXmlFiles for {} file", xmlFile.getAbsolutePath());

                    try {
                        LegacySpringUtils.getImportManager().update(xmlFile);
                        numFilesProcessed++;
                    } catch (EmptyImportFileException e) {
                        LOGGER.error("Importer failed : {} is empty.", xmlFile.getName());
                    } catch (ImportFileValidationException e) {
                        LOGGER.error("Importer failed : {} is not valid - {}", xmlFile.getName(), e.getMessage());
                    } catch (Exception e) {
                        // these exceptions can occur because of corrupt/invalid data in xml file
                        LOGGER.error("Importer failed to import file {} {}", xmlFile, e.getMessage());

                        AddLog.addLog(AddLog.ACTOR_SYSTEM, AddLog.PATIENT_DATA_FAIL, "",
                                xmlImportUtils.extractFromXMLFileNameNhsno(xmlFile.getName()),
                                xmlImportUtils.extractFromXMLFileNameUnitcode(xmlFile.getName()),
                                xmlFile.getName() + " : " + xmlImportUtils.extractErrorsFromException(e));

                        xmlImportUtils.sendEmailOfExpectionStackTraceToUnitAdmin(e, xmlFile);
                    } finally {
                        // always move the file, so it is not processed multiple times
                        LegacySpringUtils.getImportManager().archiveFileAfterProcessing(xmlFile);
                    }
                }
            }
            LOGGER.info("Patient data importer finished for {} files", numFilesProcessed);
        }
    }

    public String getRunXMLImport() {
        return runXMLImport;
    }

    public void setRunXMLImport(String runXMLImport) {
        this.runXMLImport = runXMLImport;
    }

    public String getXmlDirectory() {
        return xmlDirectory;
    }

    public void setXmlDirectory(String xmlDirectory) {
        this.xmlDirectory = xmlDirectory;
    }
}
