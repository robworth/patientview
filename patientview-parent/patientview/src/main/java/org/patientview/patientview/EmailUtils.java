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

import org.apache.commons.lang.StringUtils;
import org.patientview.patientview.model.CorruptNode;
import org.patientview.model.Unit;
import org.patientview.utils.LegacySpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.xml.sax.SAXParseException;

import javax.servlet.ServletContext;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;


public final class EmailUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtils.class);

    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final int MAX_NUM_ERRORS_TO_LIST = 20;

    private EmailUtils() {

    }

    public static void sendEmail(ServletContext context, String emailText) {
        sendEmail(context, "Intranet Feedback", emailText);
    }

    public static void sendEmail(ServletContext context, String subject, String emailText) {
        String to = LegacySpringUtils.getContextProperties().getProperty("admin.email.to");
        sendEmail(context, to, subject, emailText);
    }

    public static void sendEmail(ServletContext context, String toAddress, String subject, String emailText) {
        String from = LegacySpringUtils.getContextProperties().getProperty("noreply.email");
        sendEmail(context, from, toAddress, subject, emailText);
    }

    public static void sendEmail(ServletContext context, String fromAddress, String toAddress, String subject,
                                 String emailText) {
        sendEmail(context, fromAddress, toAddress, "", subject, emailText);
    }

    public static void sendEmail(ServletContext context, String fromAddress, String toAddress, String ccAddress,
                                 String subject, String emailText) {
        LegacySpringUtils.getEmailManager().sendEmail(context, fromAddress, toAddress, ccAddress, subject, emailText);
    }

    public static void sendEmail(String fromAddress, String[] toAddresses, String[] ccAddresses,
                                 String subject, String emailText) {
        LegacySpringUtils.getEmailManager().sendEmail(fromAddress, toAddresses, ccAddresses, subject, emailText);
    }

    public static void sendEmail(String fromAddress, String[] toAddresses, String subject, String emailText)
            throws Exception {
        LegacySpringUtils.getEmailManager().sendEmail(fromAddress, toAddresses, subject, emailText);
    }

    public static String getUnitOrSystemAdminEmailAddress(ServletContext servletContext, Unit unit) {
        String toAddress = null;

        if (unit == null || StringUtils.isBlank(unit.getRenaladminemail())) {
            toAddress = LegacySpringUtils.getAdminNotificationManager().getSupportEmailAddress();
        } else {
            toAddress = unit.getRenaladminemail();
        }

        return toAddress;
    }

    public static String getUnitOrSystemAdminEmailAddress(Unit unit) {
        String toAddress = null;

        if (unit == null || StringUtils.isBlank(unit.getRenaladminemail())) {
            toAddress = LegacySpringUtils.getAdminNotificationManager().getSupportEmailAddress();
        } else {
            toAddress = unit.getRenaladminemail();
        }

        return toAddress;
    }

    public static String createEmailBody(String errors, String fileName, String supportEmail) {

        StringBuilder emailBody = new StringBuilder();
        emailBody.append("[This is an automated email from PatientView - do not reply to this email]");
        emailBody.append(NEW_LINE);
        emailBody.append(NEW_LINE).append("The file <").append(fileName).append("> has failed to import.");
        emailBody.append(NEW_LINE);
        emailBody.append(NEW_LINE).append(
                "This means that the file has been received by RPV but there is something wrong with the file that ")
                .append("prevents it being imported properly.");
        emailBody.append(NEW_LINE);
        emailBody.append(NEW_LINE).append(
                "You will most likely need to correct the data in your local system before the file is resent to ")
                .append("PatientView.");
        emailBody.append(NEW_LINE);
        emailBody.append(NEW_LINE).append(errors);
        emailBody.append(NEW_LINE);
        emailBody.append(NEW_LINE).append(
                "Otherwise, it might be that there is an XML tag missing or an empty result value or something ")
                .append("similar.");
        emailBody.append(NEW_LINE);
        emailBody.append(NEW_LINE).append("Before contacting the email address below please ensure that:");
        emailBody.append(NEW_LINE).append(" - The file is not empty.");
        emailBody.append(NEW_LINE).append(" - The file is well formed XML.");
        emailBody.append(NEW_LINE).append(" - The file matches the RPV XML schema.");
        emailBody.append(NEW_LINE).append(" - There are no missing values.");
        emailBody.append(NEW_LINE).append(" - There are no empty tags in letters, medicines, results etc.");
        emailBody.append(NEW_LINE).append("For further help, please contact ").append(supportEmail);
        emailBody.append(NEW_LINE);
        return emailBody.toString();
    }

    public static String createCorruptNodeEmailTest(List<CorruptNode> corruptNodes) {

        StringBuilder text = new StringBuilder();

        text.append(NEW_LINE).append("These are the error(s) that have been found in this xml:");
        text.append(NEW_LINE);

        int numberOfErrors = 0;

        for (CorruptNode corruptNode : corruptNodes) {
            numberOfErrors++;

            if (numberOfErrors > MAX_NUM_ERRORS_TO_LIST) {
                // truncate this information
                text.append(NEW_LINE).append("There are further errors.  Truncating email!");
                break;
            }

            switch (corruptNode.getError()) {
                case FUTURE_RESULT:
                    text.append(NEW_LINE).append("This result has a date in the future:");
                    text.append(NEW_LINE);
                    text.append(getNodeAsString(corruptNode.getNode()));
                    text.append(NEW_LINE);

                    break;
                case MISSING_VALUE:
                    text.append(NEW_LINE).append("This result has an empty value:");
                    text.append(NEW_LINE);
                    text.append(getNodeAsString(corruptNode.getNode()));
                    text.append(NEW_LINE);

                    break;
                case WRONG_DATE_RANGE:
                    text.append(NEW_LINE).append(
                            "This result has a date that's not within the date range specified:");
                    text.append(NEW_LINE);
                    text.append(getNodeAsString(corruptNode.getNode()));
                    text.append(NEW_LINE);

                    break;
                default:
                    text.append(NEW_LINE).append("Found an unknown error:");
                    text.append(NEW_LINE);

                    break;
            }
        }

        return text.toString();
    }

    public static String extractErrorsFromException(Exception e) {

        StringBuilder errors = new StringBuilder();
        errors.append(NEW_LINE).append(
                "Please carefully read the stack trace below, there is often a good hint in there as to why ")
                .append("your file failed:");
        errors.append(NEW_LINE);
        errors.append(NEW_LINE).append("Stack Trace:");
        errors.append(NEW_LINE);

        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        errors.append(sw.toString());

        return errors.toString();
    }

    public static  String createEmailBodyForEmptyXML(String xmlFileName) {

        StringBuilder emailBody = new StringBuilder();
        emailBody.append("[This is an automated email from PatientView - do not reply to this email]");
        emailBody.append(NEW_LINE);
        emailBody.append(NEW_LINE).append("The file <" + xmlFileName + "> has not imported to RPV correctly. ");
        emailBody.append("This is because the file was empty. The most likely cause of that is "
                + "that it has not been encrypted properly at the unit before sending. ");
        emailBody.append(NEW_LINE);
        emailBody.append(NEW_LINE).append("Please contact your IT department to ask them to check the encryption.");
        emailBody.append(NEW_LINE);

        return emailBody.toString();
    }

    public static String createEmailBodyForXMLValidationErrors(List<SAXParseException> exceptions, String xmlFileName,
                                                         String xsdFileName, ServletContext context) {


        StringBuilder emailBody = new StringBuilder();
        emailBody.append("[This is an automated email from PatientView - do not reply to this email]");
        emailBody.append(NEW_LINE);
        emailBody.append(NEW_LINE).append("The file <").append(xmlFileName).append("> has failed to import.");
        emailBody.append(NEW_LINE);
        emailBody.append(NEW_LINE).append("It did not match the schema file named: ");
        emailBody.append(NEW_LINE);
        emailBody.append(NEW_LINE).append(xsdFileName);
        emailBody.append(NEW_LINE);
        emailBody.append(NEW_LINE).append("Before contacting the email address below please ensure that:");
        emailBody.append(NEW_LINE).append(" - The file is not empty.");
        emailBody.append(NEW_LINE).append(" - The file is well formed XML.");
        emailBody.append(NEW_LINE).append(" - The file matches the RPV XML schema.");
        emailBody.append(NEW_LINE).append(" - There are no missing values.");
        emailBody.append(NEW_LINE).append(" - There are no empty tags in letters, medicines, results etc.");
        emailBody.append(NEW_LINE);
        emailBody.append(NEW_LINE).append(
                "Please carefully read the stack trace below, there is often a good hint in there as to why your "
                        + "file failed:");
        emailBody.append(NEW_LINE);
        emailBody.append(NEW_LINE).append("Validation errors:");
        emailBody.append(NEW_LINE);

        for (SAXParseException exception : exceptions) {
            emailBody.append(NEW_LINE).append(exception.getLocalizedMessage());
            emailBody.append(NEW_LINE);
        }

        emailBody.append(NEW_LINE);
        emailBody.append(NEW_LINE);
        emailBody.append(NEW_LINE).append("For further help, please contact ").append(
                LegacySpringUtils.getContextProperties().getProperty("support.email"));
        emailBody.append(NEW_LINE);

        return emailBody.toString();
    }

    public static String createEmailBodyForXMLValidationErrors(List<SAXParseException> exceptions, String xmlFileName,
                                                         String xsdFileName, String supportEmail) {

        StringBuilder emailBody = new StringBuilder();
        emailBody.append("[This is an automated email from PatientView - do not reply to this email]");
        emailBody.append(NEW_LINE);
        emailBody.append(NEW_LINE).append("The file <").append(xmlFileName).append("> has failed to import.");
        emailBody.append(NEW_LINE);
        emailBody.append(NEW_LINE).append("It did not match the schema file named: ");
        emailBody.append(NEW_LINE);
        emailBody.append(NEW_LINE).append(xsdFileName);
        emailBody.append(NEW_LINE);
        emailBody.append(NEW_LINE).append("Before contacting the email address below please ensure that:");
        emailBody.append(NEW_LINE).append(" - The file is not empty.");
        emailBody.append(NEW_LINE).append(" - The file is well formed XML.");
        emailBody.append(NEW_LINE).append(" - The file matches the RPV XML schema.");
        emailBody.append(NEW_LINE).append(" - There are no missing values.");
        emailBody.append(NEW_LINE).append(" - There are no empty tags in letters, medicines, results etc.");
        emailBody.append(NEW_LINE);
        emailBody.append(NEW_LINE).append(
                "Please carefully read the stack trace below, there is often a good hint in there as to why your ")
                .append("file failed:");
        emailBody.append(NEW_LINE);
        emailBody.append(NEW_LINE).append("Validation errors:");
        emailBody.append(NEW_LINE);

        for (SAXParseException exception : exceptions) {
            emailBody.append(NEW_LINE).append(exception.getLocalizedMessage());
            emailBody.append(NEW_LINE);
        }

        emailBody.append(NEW_LINE);
        emailBody.append(NEW_LINE);
        emailBody.append(NEW_LINE).append("For further help, please contact " + supportEmail);
        emailBody.append(NEW_LINE);

        return emailBody.toString();
    }

    private static String getNodeAsString(Node node) {
        String nodeAsString = "";
        try {
            // Set up the output transformer
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer trans = transformerFactory.newTransformer();
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
            LOGGER.error("Failed to transform node because {}.", e.getMessage());
            if (LOGGER.isDebugEnabled()) {
                e.printStackTrace();
            }
            // Just return the blank string
        }

        return nodeAsString;
    }


}
