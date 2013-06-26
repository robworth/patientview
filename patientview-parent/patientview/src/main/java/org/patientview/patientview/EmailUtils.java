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

import org.patientview.patientview.model.Unit;
import org.patientview.utils.LegacySpringUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletContext;

public final class EmailUtils {

    private EmailUtils() {

    }

    public static void sendEmail(ServletContext context, String emailText) {
        sendEmail(context, "Intranet Feedback", emailText);
    }

    public static void sendEmail(ServletContext context, String subject, String emailText) {
        String to = context.getInitParameter("admin.email.to");
        sendEmail(context, to, subject, emailText);
    }

    public static void sendEmail(ServletContext context, String toAddress, String subject, String emailText) {
        String from = context.getInitParameter("noreply.email");
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
        String toAddress = "";

        if (unit == null || StringUtils.isBlank(unit.getRenaladminemail())) {
            toAddress = LegacySpringUtils.getAdminNotificationManager().getSupportEmailAddress(servletContext);
        } else {
            toAddress = unit.getRenaladminemail();
        }

        return toAddress;
    }
}
