package com.worthsoln.patientview.letter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.patientview.model.Letter;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.patientview.comment.CommentUtils;
import com.worthsoln.patientview.logon.LogonUtils;

public class LetterDetailAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        Long letterId = Long.parseLong(request.getParameter("letterId"));
        Letter letter = LegacySpringUtils.getLetterManager().get(letterId);
        boolean permissionToReadLetter = CommentUtils.verifyPermissionToReadItem(request, letter.getNhsno());
        if (permissionToReadLetter) {
            request.setAttribute("letter", letter);
            return LogonUtils.logonChecks(mapping, request);
        } else {
            return LogonUtils.logonChecks(mapping, request, "nopermission");
        }
    }

    public String getDatabaseName() {
        return "patientview";
    }

    public String getIdentifier() {
        return "letter";
    }
}
