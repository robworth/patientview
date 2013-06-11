package com.worthsoln.patientview;

import com.worthsoln.patientview.model.enums.XmlImportNotification;
import com.worthsoln.patientview.exception.XmlImportException;
import com.worthsoln.patientview.model.CorruptNode;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.unit.UnitUtils;
import com.worthsoln.utils.LegacySpringUtils;
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

public class XmlImportUtils {

    public static void sendEmptyFileEmailToUnitAdmin(File file, ServletContext context) {

        String fileName = file.getName();

        String unitcode = fileName.substring(0, fileName.indexOf("_"));

        Unit unit = UnitUtils.retrieveUnit(unitcode);

        String emailBody = createEmailBodyForEmptyXML(fileName);

        String toAddress = allocateToAddress(context, unit);

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
        String rpvAdminEmailAddress = allocateToAddress(context, unit);

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
        emailBody += "This is because the file was empty. " +
                "The most likely cause of that is that it has not been encrypted properly at the unit before sending. ";
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
        emailBody += newLine + "Please carefully read the stack trace below, there is often a good hint in there as to why your file failed:";
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
            String toAddress = allocateToAddress(context, unit);

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
        emailBody += newLine + "This means that the file has been received by RPV but there is something wrong with the file that prevents it being imported properly.";
        emailBody += newLine;
        emailBody += newLine + "You will most likely need to correct the data in your local system before the file is resent to PatientView.";
        emailBody += newLine;
        emailBody += newLine + errors;
        emailBody += newLine;
        emailBody += newLine + "Otherwise, it might be that there is an XML tag missing or an empty result value or something similar.";
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

    private static String allocateToAddress(ServletContext context, Unit unit) {
        String toAddress = "";

        if (null == unit || null == unit.getRenaladminemail() || "".equals(unit.getRenaladminemail())) {
            toAddress = LegacySpringUtils.getAdminNotificationManager().getSupportEmailAddress(context);
        } else {
            toAddress = unit.getRenaladminemail();
        }

        return toAddress;
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

                if (numberOfErrors > 20) {
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
            errors += newLine + "Please carefully read the stack trace below, there is often a good hint in there as to why your file failed:";
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
