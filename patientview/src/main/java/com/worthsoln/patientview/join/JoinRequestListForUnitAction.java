package com.worthsoln.patientview.join;

import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.patientview.model.JoinRequest;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class JoinRequestListForUnitAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {

        List<JoinRequest> joinRequests = LegacySpringUtils.getJoinRequestManager().getUsersJoinRequests();

        request.setAttribute("joinRequests", joinRequests);

        return LogonUtils.logonChecks(mapping, request);
    }
}
