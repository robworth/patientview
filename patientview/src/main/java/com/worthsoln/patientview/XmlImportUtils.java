package com.worthsoln.patientview;

import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.unit.UnitUtils;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;


public class XmlImportUtils {

    public static void sendEmailOfExpectionStackTraceToUnitAdmin(Exception e, File xmlFile, ServletContext context) {

        try {
            String stacktrace = extractStringFromStackTrace(e);

            String fileName = xmlFile.getName();

            String unitcode = fileName.substring(0, fileName.indexOf("_"));

            Unit unit = UnitUtils.retrieveUnit(unitcode);

            String toAddress = allocateToAddress(context, unit);

            String emailBody = createEmailBody(context, stacktrace, fileName);

            EmailUtils.sendEmail(context, context.getInitParameter("noreply.email"), toAddress,
                    "[RPV] File import failed: " + fileName, emailBody);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private static String createEmailBody(ServletContext context, String stacktrace, String fileName) {
        String newLine = System.getProperty("line.separator");

        String emailBody = "";
        emailBody += "[This is an automated email from Renal PatientView - do not reply to this email]";
        emailBody += newLine;
        emailBody += newLine + "The file named: ";
        emailBody += newLine;
        emailBody += newLine + fileName;
        emailBody += newLine;
        emailBody += newLine + "Has failed to import.";
        emailBody += newLine;
        emailBody += newLine + "This means that the file has been received by RPV but there is something wrong with the file that prevents it being imported properly.";
        emailBody += newLine + "It might be that there is an XML tag missing or an empty result value or something similar.";
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
        emailBody += newLine + "Stack Trace:";
        emailBody += newLine;
        emailBody += newLine + stacktrace;
        emailBody += newLine;
        emailBody += newLine;
        emailBody += newLine;
        emailBody += newLine + "For further help, please contact " + context.getInitParameter("support.email");
        emailBody += newLine;
        return emailBody;
    }

    private static String allocateToAddress(ServletContext context, Unit unit) {
        String toAddress = "";

        if (null == unit || null == unit.getRpvadminemail() || "".equals(unit.getRpvadminemail())) {
            toAddress = context.getInitParameter("support.email");
        } else {
            toAddress = unit.getRpvadminemail();
        }
        return toAddress;
    }

    public static String extractStringFromStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String stacktrace = sw.toString();
        return stacktrace;
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
