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

package org.patientview.patientview;

import org.patientview.model.enums.XmlImportNotification;
import org.patientview.patientview.model.Unit;
import org.patientview.patientview.parser.ResultParser;
import org.patientview.utils.LegacySpringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXParseException;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.List;

@Component(value = "xmlImportUtils")
public final class XmlImportUtils {

    @Value("${noreply.email}")
    private String noReplyEmail;

    @Value("${warning.email}")
    private String warningEmail;

    @Value("${support.email}")
    private String supportEmail;

    private XmlImportUtils() {

    }


    public void sendEmptyFileEmailToUnitAdmin(File file, ServletContext context) {

        String fileName = file.getName();
        String unitCode = fileName.substring(0, fileName.indexOf("_"));
        Unit unit = LegacySpringUtils.getImportManager().retrieveUnit(unitCode);
        String emailBody = EmailUtils.createEmailBodyForEmptyXML(fileName);
        String toAddress = EmailUtils.getUnitOrSystemAdminEmailAddress(context, unit);

        List<String> ccAddresses = LegacySpringUtils.getAdminNotificationManager().getEmailAddresses(
                XmlImportNotification.FAILED_IMPORT);

        EmailUtils.sendEmail(LegacySpringUtils.getContextProperties().getProperty("noreply.email"),
                new String[]{toAddress}, ccAddresses.toArray(new String[ccAddresses.size()]),
                "[PatientView] File import failed: " + fileName, emailBody);
    }

    public void sendEmptyFileEmailToUnitAdmin(String filename) {

        String unitCode = getUnitCode(filename);
        Unit unit = LegacySpringUtils.getImportManager().retrieveUnit(unitCode);
        String emailBody = EmailUtils.createEmailBodyForEmptyXML(filename);
        String toAddress = EmailUtils.getUnitOrSystemAdminEmailAddress(unit);

        List<String> ccAddresses = LegacySpringUtils.getAdminNotificationManager().getEmailAddresses(
                XmlImportNotification.FAILED_IMPORT);

        EmailUtils.sendEmail(noReplyEmail, new String[]{toAddress},
                ccAddresses.toArray(new String[ccAddresses.size()]),
                "[PatientView] File import failed: " + filename, emailBody);
    }

    public void sendXMLValidationErrors(File xmlFile, File xsdFile, List<SAXParseException> exceptions,
                                               ServletContext context) {
        String xmlFileName = xmlFile.getName();
        String xsdFileName = xsdFile.getName();
        String unitCode = xmlFileName.substring(0, xmlFileName.indexOf("_"));
        Unit unit = LegacySpringUtils.getImportManager().retrieveUnit(unitCode);
        String rpvAdminEmailAddress = EmailUtils.getUnitOrSystemAdminEmailAddress(context, unit);

        String[] toAddresses = new String[]{LegacySpringUtils.getContextProperties().getProperty("warning.email"),
                rpvAdminEmailAddress};

        List<String> ccAddresses = LegacySpringUtils.getAdminNotificationManager().getEmailAddresses(
                XmlImportNotification.FAILED_IMPORT);

        String emailBody = EmailUtils.createEmailBodyForXMLValidationErrors(exceptions, xmlFileName, xsdFileName,
                context);

        for (String toAddress : toAddresses) {
            EmailUtils.sendEmail(LegacySpringUtils.getContextProperties().getProperty("noreply.email"),
                    new String[]{toAddress},
                    ccAddresses.toArray(new String[ccAddresses.size()]),
                    "[PatientView] File import failed: " + xmlFileName, emailBody);
        }
    }

    public void sendXMLValidationErrors(File xmlFile, File xsdFile, List<SAXParseException> exceptions) {
        String xmlFileName = xmlFile.getName();
        String xsdFileName = xsdFile.getName();

        String unitCode = xmlFileName.substring(0, xmlFileName.indexOf("_"));
        Unit unit = LegacySpringUtils.getImportManager().retrieveUnit(unitCode);
        String rpvAdminEmailAddress = EmailUtils.getUnitOrSystemAdminEmailAddress(unit);

        String[] toAddresses = new String[]{warningEmail, rpvAdminEmailAddress};

        List<String> ccAddresses = LegacySpringUtils.getAdminNotificationManager().getEmailAddresses(
                XmlImportNotification.FAILED_IMPORT);

        String emailBody = EmailUtils.createEmailBodyForXMLValidationErrors(exceptions, xmlFileName, xsdFileName,
                supportEmail);

        for (String toAddress : toAddresses) {
            EmailUtils.sendEmail(noReplyEmail, new String[]{toAddress},
                    ccAddresses.toArray(new String[ccAddresses.size()]),
                    "[PatientView] File import failed: " + xmlFileName, emailBody);
        }
    }



    public void sendCorruptDataEmail(ResultParser resultParser) {
        String stackTrace = EmailUtils.createCorruptNodeEmailTest(resultParser.getCorruptNodes());
        String fileName = resultParser.getFilename();
        String unitCode = fileName.substring(0, fileName.indexOf("_"));

        Unit unit = LegacySpringUtils.getImportManager().retrieveUnit(unitCode);
        String toAddress = EmailUtils.getUnitOrSystemAdminEmailAddress(unit);

        List<String> ccAddresses = LegacySpringUtils.getAdminNotificationManager().getEmailAddresses(
                XmlImportNotification.FAILED_IMPORT);

        String emailBody = EmailUtils.createEmailBody(stackTrace, fileName, supportEmail);

        EmailUtils.sendEmail(noReplyEmail, new String[]{toAddress},
                ccAddresses.toArray(new String[ccAddresses.size()]),
                "[PatientView] File import failed: " + fileName, emailBody);
    }

    public void sendEmailOfExpectionStackTraceToUnitAdmin(Exception e, File xmlFile) {

        String stackTrace = EmailUtils.extractErrorsFromException(e);
        String fileName = xmlFile.getName();
        String unitCode = fileName.substring(0, fileName.indexOf("_"));

        Unit unit = LegacySpringUtils.getImportManager().retrieveUnit(unitCode);
        String toAddress = EmailUtils.getUnitOrSystemAdminEmailAddress(unit);

        List<String> ccAddresses = LegacySpringUtils.getAdminNotificationManager().getEmailAddresses(
                XmlImportNotification.FAILED_IMPORT);

        String emailBody = EmailUtils.createEmailBody(stackTrace, fileName, supportEmail);

        EmailUtils.sendEmail(noReplyEmail, new String[]{toAddress},
                ccAddresses.toArray(new String[ccAddresses.size()]),
                "[PatientView] File import failed: " + fileName, emailBody);

    }


    public String getNhsNumber(String filename) {
        try {
            int firstUnderscore = filename.indexOf("_");
            int secondUnderscore = filename.indexOf("_", firstUnderscore + 1);
            int firstPeriod = filename.indexOf(".", secondUnderscore + 1);

            return filename.substring(secondUnderscore + 1, firstPeriod);
        } catch (Exception e) {
            return "";
        }
    }

    public String getUnitCode(String filename) {
        try {
            return filename.substring(0, filename.indexOf("_")).toUpperCase();
        } catch (Exception e) {
            return "";
        }
    }
}
