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

import org.patientview.ibd.action.BaseAction;
import org.patientview.patientview.model.Patient;
import org.patientview.patientview.model.User;
import org.patientview.utils.LegacySpringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class PatientDetailsUpdateAction extends BaseAction {


    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        DynaActionForm dynaForm = (DynaActionForm) form;

        if (!validate(dynaForm, request)) {
            return mapping.findForward(INPUT);
        }

        // pull back the patient and the user and store if the data has been supplied
        Long patientId = (Long) dynaForm.get("patientId");
        String otherConditions = (String) dynaForm.get("otherConditions");
        String email = (String) dynaForm.get("email");

        if (email != null) {
            // update the user's email
            User user = LegacySpringUtils.getUserManager().getLoggedInUser();
            user.setEmail(email);
            LegacySpringUtils.getUserManager().save(user);
        }

        if (otherConditions != null && patientId != null) {
            Patient patient = LegacySpringUtils.getPatientManager().get(patientId);
            patient.setOtherConditions(otherConditions);
            LegacySpringUtils.getPatientManager().save(patient);
        }

        return mapping.findForward(SUCCESS);
    }

    // todo
    private boolean validate(DynaActionForm form, HttpServletRequest request) {
        ActionMessages actionErrors = new ActionMessages();
        return true;
    }
}
