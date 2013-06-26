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

package org.patientview.patientview.messaging;

import org.patientview.ibd.action.BaseAction;
import org.patientview.patientview.model.Unit;
import org.patientview.patientview.model.User;
import org.patientview.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RecipientByUnitAction extends BaseAction {

    private ObjectMapper objectMapper = new ObjectMapper();

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        User user = UserUtils.retrieveUser(request);

        // only do this if its a superadmin
        if (getSecurityUserManager().isRolePresent("superadmin")
                || getSecurityUserManager().isRolePresent("unitadmin")) {

            String unitCode = getUnitCode(request);

            if (StringUtils.hasText(unitCode)) {
                Unit unit = getUnitManager().get(unitCode);

                if (unit != null) {
                    request.setAttribute(Messaging.UNIT_ADMIN_RECIPIENTS_PARAM,
                            getMessageManager().getUnitAdminRecipients(unit, user));
                    request.setAttribute(Messaging.UNIT_STAFF_RECIPIENTS_PARAM,
                            getMessageManager().getUnitStaffRecipients(unit, user));
                    request.setAttribute(Messaging.UNIT_PATIENT_RECIPIENTS_PARAM,
                            getMessageManager().getUnitPatientRecipients(unit, user));
                    request.setAttribute(Messaging.UNIT_NAME_PARAM, unit.getName());
                }
            }
        }

        return mapping.findForward(SUCCESS);
    }

    private String getUnitCode(HttpServletRequest request) {
        try {
            return request.getParameter(Messaging.UNIT_CODE_PARAM);
        } catch (Exception e) {
            return null;
        }
    }
}
