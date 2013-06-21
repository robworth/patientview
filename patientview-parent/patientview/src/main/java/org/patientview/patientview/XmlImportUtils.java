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

import org.patientview.patientview.model.enums.XmlImportNotification;
import org.patientview.patientview.exception.XmlImportException;
import org.patientview.patientview.model.CorruptNode;
import org.patientview.patientview.model.Unit;
import org.patientview.patientview.unit.UnitUtils;
import org.patientview.utils.LegacySpringUtils;
import org.w3c.dom.Node;
import org.xml.sax.SAXParseException;

import javax.servlet.ServletContext;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.List;

public final class XmlImportUtils {

    private XmlImportUtils() {
    }

    private static final int MAX_NUM_ERRORS_TO_LIST = 20;

    public static void sendEmptyFileEmailToUnitAdmin(File file, ServletContext context) {

        String fileName = file.getName();

        String unitcode = fileName.substring(0, fileName.indexOf("_"));

        Unit unit = UnitUtils.retrieveUnit(unitcode);

        String emailBody = createEmailBodyForEmptyXML(fileName);

        String toAddress = EmailUtils.getUnitOrSystemAdminEmailAddress(context, unit);

        List<String> ccAddresses = LegacySpringUtils.getAdminNotificationManager().getEmailAddresses(
                XmlImportNotification.FAILED_IMPORT);

        EmailUtils.sendEmail(context.getInitParameter("noreply.email"), new String[]{toAddress},
                ccAddresses.toArray(new String[ccAddresses.size()]),
                "[PatientView] File import failed: " + fileName, emailBody);
    }

    public static void sendXMLValidationErrors(File xmlFile, File xsdFile, List<SAXParseException> exceptions,
                                               ServletContext context) {
        String xmlFileName = xmlFile.getName();
        String xsdFileName = xsdFile.getName();

        String unitcode = xmlFileName.substring(0, xmlFileName.indexOf("_"));
        Unit unit = UnitUtils.retrieveUnit(unitcode);
        String rpvAdminEmailAddress = EmailUtils.getUnitOrSystemAdminEmailAddress(context, unit);

        String[] toAddresses = new String[]{context.getInitParameter("warning.email"), rpvAdminEmailAddress};

        List<String> ccAddresses = LegacySpringUtils.getAdminNotificationManager().getEmailAddresses(
                XmlImportNotification.FAILED_IMPORT);

        String emailBody = createEmailBodyForXMLValidationErrors(exceptions, xmlFileName, xsdFileName, context);

        for (String toAddress : toAddresses) {
            EmailUtils.sendEmail(context.getInitParameter("noreply.email"), new String[]{toAddress},
                    ccAddresses.toArray(new String[ccAddresses.size()]),
                    "[PatientView] File import failed: " + xmlFileName, emailBody);
        }
    }

    private static String createEmailBodyForEmptyXML(String xmlFileName) {
        String newLine = System.getProperty("line.separator");

        String emailBody = "";
        emailBody += "[This is an automated email from Renal PatientView - do not reply to this email]";
        emailBody += newLine;
        emailBody += newLine + "The file <" + xmlFileName + "> has not imported to RPV correctly. ";
        emailBody += "This is because the file was empty. The most likely cause of that is "
                + "that it has not been encrypted properly at the unit before sending. ";
        emailBody += newLine;
        emailBody += newLine + "Please contact your IT department to ask them to check the encryption.";
        emailBody += newLine;

        return emailBody;
    }

    private static String createEmailBodyForXMLValidationErrors(List<SAXParseException> exceptions, String xmlFileName,
                                                                String xsdFileName, ServletContext context) {
        String newLine = System.getProperty("line.separator");

        String emailBody = "";
        emailBody += "[This is an automated email from Renal PatientView - do not reply to this email]";
        emailBody += newLine;
        emailBody += newLine + "The file <" + xmlFileName + "> has failed to import.";
        emailBody += newLine;
        emailBody += newLine + "It did not match the schema file named: ";
        emailBody += newLine;
        emailBody += newLine + xsdFileName;
        emailBody += newLine;
        emailBody += newLine + "Before contacting the email address below please ensure that:";
        emailBody += newLine + " - The file is not empty.";
        emailBody += newLine + " - The file is well formed XML.";
        emailBody += newLine + " - The file matches the RPV XML schema.";
        emailBody += newLine + " - There are no missing values.";
        emailBody += newLine + " - There are no empty tags in letters, medicines, results etc.";
        emailBody += newLine;
        emailBody += newLine
                + "Please carefully read the stack trace below, there is often a good hint in there as to why your "
                + "file failed:";
        emailBody += newLine;
        emailBody += newLine + "Validation errors:";
        emailBody += newLine;

        for (SAXParseException exception : exceptions) {
            emailBody += newLine + exception.getLocalizedMessage();
            emailBody += newLine;
        }

        emailBody += newLine;
        emailBody += newLine;
        emailBody += newLine + "For further help, please contact " + context.getInitParameter("support.email");
        emailBody += newLine;

        return emailBody;
    }

    public static void sendEmailOfExpectionStackTraceToUnitAdmin(Exception e, File xmlFile, ServletContext context) {
        try {
            String stacktrace = extractErrorsFromException(e);

            String fileName = xmlFile.getName();
            String unitcode = fileName.substring(0, fileName.indexOf("_"));

            Unit unit = UnitUtils.retrieveUnit(unitcode);
            String toAddress = EmailUtils.getUnitOrSystemAdminEmailAddress(context, unit);

            List<String> ccAddresses = LegacySpringUtils.getAdminNotificationManager().getEmailAddresses(
                    XmlImportNotification.FAILED_IMPORT);

            String emailBody = createEmailBody(context, stacktrace, fileName);

            EmailUtils.sendEmail(context.getInitParameter("noreply.email"), new String[]{toAddress},
                    ccAddresses.toArray(new String[ccAddresses.size()]),
                    "[PatientView] File import failed: " + fileName, emailBody);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private static String createEmailBody(ServletContext context, String errors, String fileName) {
        String newLine = System.getProperty("line.separator");

        String emailBody = "";
        emailBody += "[This is an automated email from Renal PatientView - do not reply to this email]";
        emailBody += newLine;
        emailBody += newLine + "The file <" + fileName + "> has failed to import.";
        emailBody += newLine;
        emailBody += newLine
                + "This means that the file has been received by RPV but there is something wrong with the file that "
                + "prevents it being imported properly.";
        emailBody += newLine;
        emailBody += newLine
                + "You will most likely need to correct the data in your local system before the file is resent to "
                + "PatientView.";
        emailBody += newLine;
        emailBody += newLine + errors;
        emailBody += newLine;
        emailBody += newLine
                + "Otherwise, it might be that there is an XML tag missing or an empty result value or something "
                + "similar.";
        emailBody += newLine;
        emailBody += newLine + "Before contacting the email address below please ensure that:";
        emailBody += newLine + " - The file is not empty.";
        emailBody += newLine + " - The file is well formed XML.";
        emailBody += newLine + " - The file matches the RPV XML schema.";
        emailBody += newLine + " - There are no missing values.";
        emailBody += newLine + " - There are no empty tags in letters, medicines, results etc.";
        emailBody += newLine + "For further help, please contact " + context.getInitParameter("support.email");
        emailBody += newLine;
        return emailBody;
    }

    public static String extractErrorsFromException(Exception e) {
        String errors = "";
        String newLine = System.getProperty("line.separator");

        if (e instanceof XmlImportException) {

            Collections.sort(((XmlImportException) e).getNodeList());

            errors += newLine + "These are the error(s) that have been found in this xml:";
            errors += newLine;

            int numberOfErrors = 0;

            for (CorruptNode corruptNode : ((XmlImportException) e).getNodeList()) {
                numberOfErrors++;

                if (numberOfErrors > MAX_NUM_ERRORS_TO_LIST) {
                    // truncate this information
                    errors += newLine + "There are further errors.  Truncating email!";
                    break;
                }

                switch (corruptNode.getError()) {
                    case FUTURE_RESULT:
                        errors += newLine + "This result has a date in the future:";
                        errors += newLine;
                        errors += getNodeAsString(corruptNode.getNode());
                        errors += newLine;

                        break;
                    case MISSING_VALUE:
                        errors += newLine + "This result has an empty value:";
                        errors += newLine;
                        errors += getNodeAsString(corruptNode.getNode());
                        errors += newLine;

                        break;
                    case WRONG_DATE_RANGE:
                        errors += newLine + "This result has a date that's not within the date range specified:";
                        errors += newLine;
                        errors += getNodeAsString(corruptNode.getNode());
                        errors += newLine;

                        break;
                    default:
                        errors += newLine + "Found an unknown error:";
                        errors += newLine;

                        break;
                }
            }
        } else {
            errors += newLine
                    + "Please carefully read the stack trace below, there is often a good hint in there as to why "
                    + "your file failed:";
            errors += newLine;
            errors += newLine + "Stack Trace:";
            errors += newLine;

            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            errors += sw.toString();
        }

        return errors;
    }

    private static String getNodeAsString(Node node) {
        String nodeAsString = "";
        try {
            // Set up the output transformer
            TransformerFactory transfac = TransformerFactory.newInstance();
            Transformer trans = transfac.newTransformer();
            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");

            // Print the DOM node

            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            DOMSource source = new DOMSource(node);
            trans.transform(source, result);
            String xmlString = sw.toString();

            nodeAsString += xmlString;
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return nodeAsString;
    }

    public static String extractFromXMLFileNameNhsno(String filename) {
        try {
            int firstUnderscore = filename.indexOf("_");
            int secondUnderscore = filename.indexOf("_", firstUnderscore + 1);
            int firstPeriod = filename.indexOf(".", secondUnderscore + 1);

            return filename.substring(secondUnderscore + 1, firstPeriod);
        } catch (Exception e) {
            return "";
        }
    }

    public static String extractFromXMLFileNameUnitcode(String filename) {
        try {
            return filename.substring(0, filename.indexOf("_")).toUpperCase();
        } catch (Exception e) {
            return "";
        }
    }
}
