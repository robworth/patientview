package com.worthsoln.patientview.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.patientview.model.Comment;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.patientview.PatientUtils;
import com.worthsoln.patientview.logon.LogonUtils;

import java.util.List;

public class CommentViewAllAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        String nhsno = PatientUtils.retrieveNhsNo(request);

        List<Comment> comments = LegacySpringUtils.getCommentManager().get(nhsno);

        if (CommentUtils.verifyPermissionToReadItem(request, nhsno)) {
            request.setAttribute("comments", comments);
            return LogonUtils.logonChecks(mapping, request);
        } else {
            return LogonUtils.logonChecks(mapping, request, "nopermission");
        }
    }

}