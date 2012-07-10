package com.worthsoln.patientview.logon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.actionutils.ActionUtils;

/**
 *      Note: this action never gets hit now as this url and logout is handled by spring security.
 *
 *      Assuming this setUpNavLink() is redundant now.
 */
public class LogoutAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        request.getSession().invalidate();
        ActionUtils.setUpNavLink(mapping.getParameter(), request);
        response.sendRedirect("index.do");
        return null;
    }
}
