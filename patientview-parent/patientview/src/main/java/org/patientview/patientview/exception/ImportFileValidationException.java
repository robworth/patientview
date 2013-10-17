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
package org.patientview.patientview.exception;


import org.patientview.patientview.XmlImportUtils;
import org.patientview.patientview.logging.AddLog;
import org.xml.sax.SAXParseException;

import javax.inject.Inject;
import java.io.File;
import java.util.List;

public class ImportFileValidationException extends PatientViewJobException {

    @Inject
    private XmlImportUtils xmlImportUtils;

    public ImportFileValidationException() {
        super();
    }

    public ImportFileValidationException(File xmlFile, File xsdFile, List<SAXParseException> exceptions) {
        super();
        AddLog.addLog(AddLog.ACTOR_SYSTEM, AddLog.PATIENT_DATA_CORRUPT, "",
                xmlImportUtils.extractFromXMLFileNameNhsno(xmlFile.getName()),
                xmlImportUtils.extractFromXMLFileNameUnitcode(xmlFile.getName()), xmlFile.getName());

        // send email, then continue importing
        xmlImportUtils.sendXMLValidationErrors(xmlFile, xsdFile, exceptions);
    }
}
