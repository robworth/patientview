package com.worthsoln.patientview.feedback;

import com.worthsoln.HibernateUtil;
import com.worthsoln.database.action.DatabaseAction;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FeedbackEditDisplayAction extends DatabaseAction {
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        String feedbackId = BeanUtils.getProperty(form, "id");

        Feedback feedback = (Feedback) HibernateUtil.getPersistentObject(Feedback.class, Integer.decode(feedbackId));

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