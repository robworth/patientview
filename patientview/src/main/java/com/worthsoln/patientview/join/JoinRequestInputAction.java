package com.worthsoln.patientview.join;

import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class JoinRequestInputAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {

        List<Unit> units = LegacySpringUtils.getUnitManager().getAllDisregardingSpeciality(false);
        request.setAttribute("units", units);

        request.getSession().setAttribute("antiSpamQuestion", ActionUtils.getAntiSpamQuestion(request));

        return LogonUtils.logonChecks(mapping, request);
    }
}
