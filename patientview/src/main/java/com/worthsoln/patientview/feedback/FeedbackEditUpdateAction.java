package com.worthsoln.patientview.feedback;

import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.model.Feedback;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FeedbackEditUpdateAction extends DatabaseAction {
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        Long feedbackId = Long.decode(BeanUtils.getProperty(form, "id"));
        String commentedited = BeanUtils.getProperty(form, "commentedited");
        String makepublic = BeanUtils.getProperty(form, "makepublic");
        boolean makepublicBool = "true".equals(makepublic);

        Feedback feedback = LegacySpringUtils.getFeedbackManager().get(feedbackId);

        feedback.setCommentedited(commentedited);
        feedback.setMakepublic(makepublicBool);

        LegacySpringUtils.getFeedbackManager().save(feedback);

        List feedbacks = LegacySpringUtils.getFeedbackManager().get(feedback.getUnitcode());

        request.setAttribute("feedbacks", feedbacks);

        return LogonUtils.logonChecks(mapping, request);
    }

    public String getIdentifier() {
        return null;
    }

    public String getDatabaseName() {
        return "patientview";
    }
}