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

package org.patientview.patientview.logging;

import org.patientview.actionutils.ActionUtils;
import org.patientview.patientview.logon.LogonUtils;
import org.patientview.patientview.unit.UnitUtils;
import org.patientview.patientview.utils.TimestampUtils;
import org.patientview.utils.LegacySpringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LogViewAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        Calendar startdate = determineStartDate(form, request);
        Calendar enddate = determineEndDate(form, request);
        String nhsno = ActionUtils.retrieveStringPropertyValue("nhsno", form, request);
        String user = ActionUtils.retrieveStringPropertyValue("user", form, request);
        String actor = ActionUtils.retrieveStringPropertyValue("actor", form, request);
        String action = ActionUtils.retrieveStringPropertyValue("action", form, request);
        String unitcode = ActionUtils.retrieveStringPropertyValue("unitcode", form, request);
        String order = ActionUtils.retrieveStringPropertyValue("order", form, request);
        Boolean orderByAsc = null;

        // get order mark, false: order by date desc, true : order by date asc
        if (order != null && !"".equals(order)) {
            if ("false".equals(order)) {
                orderByAsc = false;
            } else {
                orderByAsc = true;
            }
        }

        List log = getLogEntries(nhsno, user, actor, action, unitcode, startdate, enddate, orderByAsc);
        request.setAttribute("log", log);

        UnitUtils.putRelevantUnitsInRequest(request);

        LoggingUtils.defaultDatesInForm(form, startdate, enddate);

        if (orderByAsc == null) {
            orderByAsc = false;
        } else {
            if (orderByAsc) {
                orderByAsc = false;
            } else {
                orderByAsc = true;
            }
        }
        request.setAttribute("order", orderByAsc);

        return LogonUtils.logonChecks(mapping, request);
    }

    private Calendar determineStartDate(ActionForm form, HttpServletRequest request)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String startDateString = ActionUtils.retrieveStringPropertyValue("startdate", form, request);
        Calendar startDate;

        if ((startDateString == null) || ("".equals(startDateString))) {
            startDate = LoggingUtils.getDefaultStartDateForLogQuery();
        } else {
            startDate = TimestampUtils.createTimestampStartDay(startDateString);
        }

        return startDate;
    }

    private Calendar determineEndDate(ActionForm form, HttpServletRequest request)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String endDateString = ActionUtils.retrieveStringPropertyValue("enddate", form, request);
        Calendar endDate;

        if ((endDateString == null) || ("".equals(endDateString))) {
            endDate = LoggingUtils.getDefaultEndDateForLogQuery();
        } else {
            endDate = TimestampUtils.createTimestampEndDay(endDateString);
        }

        return endDate;
    }

    private List getLogEntries(String nhsno, String user, String actor, String action, String unitcode,
                               Calendar startdate, Calendar enddate, Boolean orderByAsc) throws Exception {
        List logEntries = new ArrayList();

        if (orderByAsc != null) {
            logEntries = LegacySpringUtils.getLogEntryManager().getWithNhsNo(nhsno, user, actor, action, unitcode,
                    startdate, enddate, orderByAsc);
        }

        return logEntries;
    }
}
