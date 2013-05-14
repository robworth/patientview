package com.worthsoln.patientview.logon;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class BackToAdminAction extends LoggedInAction {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        // remove the magic "viewing as patient" from the session
        request.getSession().setAttribute("userBeingViewedUsername", null);

        return super.execute(mapping, form, request, response);
    }
}
