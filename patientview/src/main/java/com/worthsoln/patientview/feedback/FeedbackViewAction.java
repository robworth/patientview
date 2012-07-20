package com.worthsoln.patientview.feedback;

import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FeedbackViewAction extends Action {


    public ActionForward execute(
            ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String unitcode = BeanUtils.getProperty(form, "unitcode");

        List feedbacks = LegacySpringUtils.getFeedbackManager().get(unitcode);

        request.setAttribute("feedbacks", feedbacks);

        return LogonUtils.logonChecks(mapping, request);
    }
}
