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

import org.patientview.actionutils.ActionUtils;
import org.patientview.patientview.model.User;
import org.patientview.patientview.logging.AddLog;
import org.patientview.patientview.model.LogEntry;
import org.patientview.patientview.news.NewsUtils;
import org.patientview.patientview.model.Unit;
import org.patientview.utils.LegacySpringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggedInAction extends Action {

    private final DateFormat format = new SimpleDateFormat("d MMM yyyy HH:mm");

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        String forward = "";
        ActionUtils.setUpNavLink(mapping.getParameter(), request);
        NewsUtils.putAppropriateNewsForViewingInRequest(request);
        User user = LegacySpringUtils.getUserManager().getLoggedInUser();

        if (user != null) {

            final String role = LegacySpringUtils.getUserManager().getCurrentSpecialtyRole(user);

            // Is user patient or admin?
            if ("patient".equalsIgnoreCase(role)) {
                request.setAttribute("isPatient", true);
            }
            if ((user.getLastlogon() != null)) {
                request.setAttribute("lastLogin", format.format(user.getLastlogon()));
            }
            user.setLastlogon(new Date());

            LegacySpringUtils.getUserManager().save(user);

            if ("patient".equalsIgnoreCase(role)) {

                String nhsno = LegacySpringUtils.getUserManager().getUsersRealNhsNoBestGuess(user.getUsername());

                if (nhsno != null && !nhsno.equals("")) {
                    LogEntry log = LegacySpringUtils.getLogEntryManager().getLatestLogEntry(nhsno,
                            AddLog.PATIENT_DATA_FOLLOWUP);
                    if (log != null) {
                        request.setAttribute("lastDataDate", format.format(log.getDate().getTime()));
                        // Get the unit from the unitcode
                        String unitcode = log.getUnitcode();
                        if (unitcode != null) {
                            Unit unit = LegacySpringUtils.getUnitManager().get(unitcode);
                            if (null == unit) {
                                request.setAttribute("lastDataFrom", "Unit with code: " + unitcode);
                            } else {
                                request.setAttribute("lastDataFrom", unit.getName());
                            }
                        }
                    }
                }
                forward = "patient";
            } else {
                forward = "admin";
            }
        }
        return LogonUtils.logonChecks(mapping, request, forward);
    }

}
