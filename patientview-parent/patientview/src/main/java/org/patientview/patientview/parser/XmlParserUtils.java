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

package org.patientview.patientview.parser;

import org.patientview.patientview.XmlImportUtils;
import org.patientview.patientview.logging.AddLog;
import org.patientview.utils.LegacySpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.io.File;

public final class XmlParserUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlParserUtils.class);

    private XmlParserUtils() {
    }

    public static void updateXmlData(ServletContext context, File xmlFile) {

        try {
            LegacySpringUtils.getImportManager().update(context, xmlFile);
        } catch (Exception e) {

            // these exceptions can occur because of corrupt/invalid data in xml file
            LOGGER.error("Importer failed to import file {} {}", xmlFile, e.getMessage());

            AddLog.addLog(AddLog.ACTOR_SYSTEM, AddLog.PATIENT_DATA_FAIL, "",
                    XmlImportUtils.extractFromXMLFileNameNhsno(xmlFile.getName()),
                    XmlImportUtils.extractFromXMLFileNameUnitcode(xmlFile.getName()),
                    xmlFile.getName() + " : " + XmlImportUtils.extractErrorsFromException(e));

            XmlImportUtils.sendEmailOfExpectionStackTraceToUnitAdmin(e, xmlFile, context);
        }

        // always move the file, so it is not processed multiple times
        LegacySpringUtils.getImportManager().renameDirectory(context, xmlFile);
    }
}
