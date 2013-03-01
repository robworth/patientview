package com.worthsoln.patientview.user;

import com.Ostermiller.util.RandPass;
import com.worthsoln.patientview.EmailUtils;
import com.worthsoln.patientview.model.EmailVerification;
import com.worthsoln.utils.LegacySpringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *  todo move the functionallity out of here into the EmailVerificationManager
 */
public class EmailVerificationUtils {

    public static void createEmailVerification(String username, String email, HttpServletRequest request) {
        if (null != email && !"".equals(email)) {
            try {
                LegacySpringUtils.getEmailVerificationManager().delete(username);

                String verificationCode = new RandPass(RandPass.NUMBERS_AND_LETTERS_ALPHABET).getPass(50);
                Calendar now = GregorianCalendar.getInstance();
                ServletContext context = request.getSession().getServletContext();
                int daysToAdd = Integer.decode(context.getInitParameter("email.verification.best.before.days"));

                now.add(Calendar.DATE, daysToAdd);
                EmailVerification emailVerification = new EmailVerification(username, email, verificationCode, now);

                LegacySpringUtils.getEmailVerificationManager().save(emailVerification);

                sendEmailVerificationEmail(emailVerification, context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void sendEmailVerificationEmail(EmailVerification emailVerfification, ServletContext context) {
        String newLine = System.getProperty("line.separator");

        String emailBody = "";
        emailBody += "[This is an automated email from Renal PatientView - do not reply to this email]" + newLine;
        emailBody += newLine;
        emailBody += "Either you have requested that your email be verified or you have recently been added to Renal PatientView with an email address and it needs to be verified." + newLine;
        emailBody += newLine;
        emailBody += "This is simply so we can be sure that your email address is correct." + newLine;
        emailBody += newLine;
        emailBody += "Please note this link will not give access to your username and password - this will be given to you separately by your unit." + newLine;
        emailBody += newLine;
        emailBody += "To verify this email address simply click on the link below, or if that doesn't work, copy and paste the link into the address bar of your internet browser." + newLine;
        emailBody += newLine;
        emailBody += "If you have no idea why you have been sent this email, then it is probably not for you. Please delete it." + newLine;
        emailBody += newLine;
        emailBody += "Click this link to verify:" + newLine;
        emailBody += newLine;
        emailBody += "https://www.renalpatientview.org/emailverification.do?v=" + emailVerfification.getVerificationcode() + newLine;
        emailBody += newLine;
        emailBody += newLine;
        emailBody += "------------------------" + newLine;
        emailBody += newLine;
        emailBody += "Please note that Renal PatientView will never send you an email with link to click to ask you to log in to do anything. If you ever get an email like that, please let us know, because it it probably some kind of scam or phishing attempt." + newLine;

        EmailUtils.sendEmail(context, context.getInitParameter("noreply.email"), emailVerfification.getEmail(), "[Renal PatientView] Verify email address", emailBody);
    }
}
