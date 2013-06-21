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

package org.patientview.patientview.user;

import com.Ostermiller.util.RandPass;
import org.patientview.patientview.EmailUtils;
import org.patientview.patientview.model.EmailVerification;
import org.patientview.utils.LegacySpringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * TODO: move the functionallity out of here into the EmailVerificationManager
 */
public final class EmailVerificationUtils {

    private static final int VERIFICATION_CODE_LENGTH = 50;

    private EmailVerificationUtils() {

    }

    public static void createEmailVerification(String username, String email, HttpServletRequest request) {
        if (null != email && !"".equals(email)) {
            try {
                LegacySpringUtils.getEmailVerificationManager().delete(username);

                String verificationCode =
                        new RandPass(RandPass.NUMBERS_AND_LETTERS_ALPHABET).getPass(VERIFICATION_CODE_LENGTH);
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

    private static void sendEmailVerificationEmail(EmailVerification emailVerification, ServletContext context) {
        String newLine = System.getProperty("line.separator");

        String emailBody = "";
        emailBody += "[This is an automated email from Renal PatientView - do not reply to this email]" + newLine;
        emailBody += newLine;
        emailBody += "Either you have requested that your email be verified or you have recently been added to "
                + "Renal PatientView with an email address and it needs to be verified." + newLine;
        emailBody += newLine;
        emailBody += "This is simply so we can be sure that your email address is correct." + newLine;
        emailBody += newLine;
        emailBody +=
                "To verify this email address simply click on the link below, or if that doesn't work, "
                        + "copy and paste the link into the address bar of your internet browser."
                        + newLine;
        emailBody += newLine;
        emailBody +=
                "If you have no idea why you have been sent this email, then it is probably not for you. Please "
                        + "delete it."
                        + newLine;
        emailBody += newLine;
        emailBody += "Click this link to verify:" + newLine;
        emailBody += newLine;
        emailBody += context.getInitParameter("config.site.url") + "emailverification.do?v="
                + emailVerification.getVerificationcode() + newLine;
        emailBody += newLine;
        emailBody += newLine;
        emailBody += "------------------------" + newLine;
        emailBody += newLine;
        emailBody +=
                "Please note that Renal PatientView will never send you an email with link to click to ask you to log"
                        + " in to do anything. If you ever get an email like that, please let us know, "
                        + "because it it probably some kind of scam or phishing attempt."
                        + newLine;

        EmailUtils.sendEmail(context, context.getInitParameter("noreply.email"), emailVerification.getEmail(),
                "[Renal PatientView] Verify email address", emailBody);
    }
}
