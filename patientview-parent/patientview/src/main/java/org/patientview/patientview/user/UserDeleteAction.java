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

import org.patientview.patientview.model.User;
import org.patientview.patientview.logging.AddLog;
import org.patientview.patientview.logon.PatientLogon;
import org.patientview.patientview.model.Unit;
import org.patientview.patientview.unit.UnitUtils;
import org.patientview.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserDeleteAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {
        String username = BeanUtils.getProperty(form, "username");
        String unitcode = BeanUtils.getProperty(form, "unitcode");
        String nhsno = BeanUtils.getProperty(form, "nhsno");

        PatientLogon patient = new PatientLogon();
        Unit unit = UnitUtils.retrieveUnit(unitcode);
        patient.setUsername(username);
        patient.setNhsno(nhsno);
        User user = LegacySpringUtils.getUserManager().get(username);
        patient.setName(user.getName());

        LegacySpringUtils.getUserManager().delete(username, unitcode);

        AddLog.addLog(LegacySpringUtils.getSecurityUserManager().getLoggedInUsername(), AddLog.PATIENT_DELETE, username,
                nhsno, unitcode, "");
        String mappingToFind = "success";

        request.setAttribute("units", LegacySpringUtils.getUnitManager().getAll(false));
        request.setAttribute("patient", patient);
        request.setAttribute("unit", unit);

        return mapping.findForward(mappingToFind);
    }
}
