package com.worthsoln.patientview.feedback;

import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.model.Feedback;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FeedbackEditDisplayAction extends DatabaseAction {
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        Long feedbackId = Long.decode(BeanUtils.getProperty(form, "id"));

        Feedback feedback = LegacySpringUtils.getFeedbackManager().get(feedbackId);

        request.setAttribute("feedback", feedback);

        return mapping.findForward("success");
    }

    public String getIdentifier() {
        return null;
    }

    public String getDatabaseName() {
        return "patientview";
    }
}