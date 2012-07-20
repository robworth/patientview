package com.worthsoln.patientview.logging;

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
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.patientview.unit.UnitUtils;

public class ViewsOfPatientAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        Calendar startdate = LoggingUtils.getDefaultStartDateForLogQuery();
        Calendar enddate = LoggingUtils.getDefaultEndDateForLogQuery();
        String nhsno = BeanUtils.getProperty(form, "nhsno");
        List log = LegacySpringUtils.getLogEntryManager().getWithNhsNo(nhsno, startdate, enddate, "patient view");
        request.setAttribute("log", log);
        UnitUtils.putRelevantUnitsInRequest(request);
        LoggingUtils.defaultDatesInForm(form, startdate, enddate);
        BeanUtils.setProperty(form, "action", AddLog.PATIENT_VIEW);
        return LogonUtils.logonChecks(mapping, request);
    }
}
