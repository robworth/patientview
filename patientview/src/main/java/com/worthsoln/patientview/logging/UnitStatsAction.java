package com.worthsoln.patientview.logging;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.patientview.utils.TimestampUtils;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.patientview.unit.UnitUtils;

public class UnitStatsAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        Calendar startdate = determineStartDate(form);
        Calendar enddate = determineEndDate(form);
        String unitcode = BeanUtils.getProperty(form, "unitcode");

        List log = getUnitStats(unitcode, startdate, enddate);
        request.setAttribute("log", log);

        UnitUtils.putRelevantUnitsInRequest(request);

        LoggingUtils.defaultDatesInForm(form, startdate, enddate);

        return LogonUtils.logonChecks(mapping, request);
    }

    private Calendar determineStartDate(ActionForm form)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String startDateString = BeanUtils.getProperty(form, "startdate");
        Calendar startDate;

        if ((startDateString == null) || ("".equals(startDateString))) {
            startDate = LoggingUtils.getStartDateForLogQuery(Calendar.MONTH, -1);
        } else {
            startDate = TimestampUtils.createTimestampEndDay(startDateString);
        }

        return startDate;
    }

    private Calendar determineEndDate(ActionForm form)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String endDateString = BeanUtils.getProperty(form, "enddate");
        Calendar endDate;

        if ((endDateString == null) || ("".equals(endDateString))) {
            endDate = LoggingUtils.getDefaultEndDateForLogQuery();
        } else {
            endDate = TimestampUtils.createTimestampEndDay(endDateString);
        }

        return endDate;
    }

    private static List getUnitStats(String unitcode, Calendar startdate, Calendar enddate) throws Exception {
        List logEntries = new ArrayList();
        if (!unitcode.equals("")) {
            logEntries = LegacySpringUtils.getLogEntryManager().getWithUnitCode(unitcode, startdate, enddate);
        }
        return logEntries;
    }
}
