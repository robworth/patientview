package com.worthsoln.patientview.patiententry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class PatientResultAddAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        String year = BeanUtils.getProperty(form, "year");
        String month = BeanUtils.getProperty(form, "month");
        String day = BeanUtils.getProperty(form, "day");
        String hour = BeanUtils.getProperty(form, "hour");
        String minute = BeanUtils.getProperty(form, "minute");
        String resultName = BeanUtils.getProperty(form, "patientResultName");
        String resultCode1 = BeanUtils.getProperty(form, "patientResultCode1");
        String resultValue1 = BeanUtils.getProperty(form, "patientResultValue1");
        String resultCode2 = (BeanUtils.getProperty(form, "patientResultCode2") != null) ? BeanUtils.getProperty(form, "patientResultCode2") : "";
        String resultValue2 = (BeanUtils.getProperty(form, "patientResultValue2") != null) ? BeanUtils.getProperty(form, "patientResultValue2") : "";

        HttpSession session = request.getSession();
        Map<Long, PatientEnteredResult> results = (Map<Long, PatientEnteredResult>) session.getAttribute(resultName);

        if (null == results) {
            results = new TreeMap<Long, PatientEnteredResult>();
        }

        int intMonth = Integer.decode(month).intValue() - 1;

        if ("".equals(resultCode2)) {
            PatientEnteredResult result = new PatientEnteredResult(year, intMonth + "", day, hour, minute,
                    resultCode1, resultValue1);
            results.put((new Date()).getTime(), result);
        } else {
            PatientEnteredResult result = new PatientEnteredResult(year, intMonth + "", day, hour, minute,
                    resultCode1, resultValue1, resultCode2, resultValue2);
            results.put((new Date()).getTime(), result);
        }

        session.setAttribute(resultName, results);

        BeanUtils.setProperty(form, "patientResultCode1", null);
        BeanUtils.setProperty(form, "patientResultCode2", null);
        BeanUtils.setProperty(form, "patientResultValue1", null);
        BeanUtils.setProperty(form, "patientResultValue2", null);

        return mapping.findForward("success");
    }
}