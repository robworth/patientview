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

package org.patientview.patientview.logon;

import org.patientview.patientview.logging.AddLog;
import org.patientview.patientview.model.User;
import org.patientview.patientview.user.EmailVerificationUtils;
import org.patientview.patientview.user.UserUtils;
import org.patientview.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmailChangeAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        /**
* This allows to change their email address.
*
* Note: there is also struts validation, see validation.xml
*/

        // receive data from submitted form
        User user = LegacySpringUtils.getUserManager().getLoggedInUser();
        String emailAddress = BeanUtils.getProperty(form, "emailAddress");
        String emailAddressAgain = BeanUtils.getProperty(form, "emailAddressAgain");

        boolean errorFound = false;
        boolean sendVerificationEmail = true;

        // if both email boxes empty -> fine, and no validation email sent (this
        if (!StringUtils.hasLength(emailAddress) && !StringUtils.hasLength(emailAddressAgain)) {
            sendVerificationEmail = false;

        } else if (!emailAddress.equals(emailAddressAgain)) {
            // emails supplied, they must match
            request.setAttribute("emailError", "Email addresses don't match");
            errorFound = true;

        } else {
            // update the user's email with that supplied
            user.setEmail(emailAddress);
            user.setEmailverified(false);
        }

        if (errorFound) {
            return mapping.findForward("input");
        } else {

            // ok so it worked, save the email
            // change if it was made.
            LegacySpringUtils.getUserManager().save(user);

            // db logging
            AddLog.addLog(user.getUsername(), AddLog.EMAIL_CHANGED, user.getUsername(),
                    UserUtils.retrieveUsersRealNhsnoBestGuess(user.getUsername()),
                    UserUtils.retrieveUsersRealUnitcodeBestGuess(user.getUsername()), "");

            // email verification - only required if the user has supplied an email address
            // (regardless of if it is the same as the one used to create by the admin)
            if (sendVerificationEmail) {
                EmailVerificationUtils.createEmailVerification(user.getUsername(), user.getEmail(), request);
                request.setAttribute("verificationMailSent", true);
            }
            request.setAttribute("emailMsg", "Email was updated successfully.");
            return mapping.findForward("success");
        }
    }
}
