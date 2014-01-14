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

package org.patientview.radar.service;

import org.patientview.radar.model.user.PatientUser;
import org.patientview.radar.model.user.ProfessionalUser;

import java.util.Map;

public interface EmailManager {

    void sendPatientRegistrationEmail(PatientUser patientUser, String password);

    void sendPatientRegistrationReminderEmail(PatientUser patientUser) throws Exception;

    void sendPatientRegistrationAdminNotificationEmail(PatientUser patientUser);

    void sendProfessionalRegistrationAdminNotificationEmail(ProfessionalUser professionalUser);

    void sendForgottenPassword(PatientUser patientUser, String password);

    void sendForgottenPassword(ProfessionalUser professionalUser, String password) throws Exception;

    // to allow for testing, this method is in the interface
    String renderTemplate(Map<String, Object> map, String template);

    // to allow for testing, this method is in the interface
    void sendEmail(String from, String[] to, String[] bbc, String subject, String body);

}
