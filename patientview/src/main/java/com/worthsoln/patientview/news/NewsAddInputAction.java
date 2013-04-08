package com.worthsoln.patientview.news;

import com.worthsoln.patientview.unit.UnitUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewsAddInputAction extends Action {

    public ActionForward execute(
            ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UnitUtils.putRelevantUnitsInRequest(request);
        return mapping.findForward("success");
    }

}
