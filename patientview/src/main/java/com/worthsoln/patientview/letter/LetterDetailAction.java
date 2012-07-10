package com.worthsoln.patientview.letter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import com.worthsoln.HibernateUtil;
import com.worthsoln.patientview.comment.CommentUtils;
import com.worthsoln.patientview.logon.LogonUtils;

public class LetterDetailAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        int letterId = Integer.parseInt(request.getParameter("letterId"));
        Letter letter = getLetterForPatient(letterId);
        boolean permissionToReadLetter = CommentUtils.verifyPermissionToReadItem(request, letter.getNhsno());
        if (permissionToReadLetter) {
            request.setAttribute("letter", letter);
            return LogonUtils.logonChecks(mapping, request);
        } else {
            return LogonUtils.logonChecks(mapping, request, "nopermission");
        }
    }

    private Letter getLetterForPatient(int letterId) throws HibernateException {
        Session session = HibernateUtil.currentSession();
        Letter letter = (Letter) session.get(Letter.class, new Integer(letterId));
        HibernateUtil.closeSession();
        return letter;
    }

    public String getDatabaseName() {
        return "patientview";
    }

    public String getIdentifier() {
        return "letter";
    }
}
