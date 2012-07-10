package com.worthsoln.patientview;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.patientview.news.NewsUtils;

public class IndexAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        ActionUtils.setUpNavLink(mapping.getParameter(), request);
        NewsUtils.putAppropriateNewsForViewingInRequest(request);
        return LogonUtils.logonChecks(mapping, request);
    }
}
