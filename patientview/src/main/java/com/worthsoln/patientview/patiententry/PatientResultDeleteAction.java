package com.worthsoln.patientview.patiententry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class PatientResultDeleteAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {


        String patientResultKeyString = BeanUtils.getProperty(form, "patientResultKey");
        String patientResultName = BeanUtils.getProperty(form, "patientResultName");

        HttpSession session = request.getSession();
        Map<Long, PatientEnteredResult> patientResults = (Map<Long, PatientEnteredResult>) session.getAttribute(patientResultName);

        Long patientResultKey = Long.decode(patientResultKeyString);

        patientResults.remove(patientResultKey);

        return mapping.findForward("success");
    }
}