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

package com.worthsoln.patientview;

import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.patientview.model.Contact;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.patientview.model.Patient;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.unit.UnitUtils;
import com.worthsoln.patientview.user.UserUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ContactAction extends Action {

    public ActionForward execute(
            ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // set current nav
        ActionUtils.setUpNavLink(mapping.getParameter(), request);

        User user = UserUtils.retrieveUser(request);
        List<UserMapping> userMappings = UserUtils.retrieveUserMappings(user);

        List<Contact> contacts = new ArrayList<Contact>();

        for (UserMapping userMapping : userMappings) {
            if (!UnitUtils.PATIENT_ENTERS_UNITCODE.equalsIgnoreCase(userMapping.getUnitcode())) {
                Patient patient = PatientUtils.retrievePatient(userMapping.getNhsno(), userMapping.getUnitcode());

                Unit unit = UnitUtils.retrieveUnit(userMapping.getUnitcode());
                Contact contact = new Contact(patient, unit, userMapping);

                contacts.add(contact);
            }
        }

        request.setAttribute("contacts", contacts);

        DynaActionForm feedbackForm = (DynaActionForm) form;
        feedbackForm.set("anonymous", "true");

        return LogonUtils.logonChecks(mapping, request);
    }

}
