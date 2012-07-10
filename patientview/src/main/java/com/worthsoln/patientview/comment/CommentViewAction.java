package com.worthsoln.patientview.comment;

import com.worthsoln.HibernateUtil;
import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.logon.LogonUtils;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class CommentViewAction extends DatabaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        int commentId = Integer.parseInt(request.getParameter("commentId"));
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        Comment comment = (Comment) session.get(Comment.class, commentId);
        tx.commit();
        HibernateUtil.closeSession();

        if (CommentUtils.verifyPermissionToReadItem(request, comment.getNhsno())) {
            List<Comment> comments = new ArrayList<Comment>();

            comments.add(comment);

            request.setAttribute("comments", comments);
            return LogonUtils.logonChecks(mapping, request);
        } else {
            return LogonUtils.logonChecks(mapping, request, "nopermission");
        }
    }

    public String getDatabaseName() {
        return "patientview";
    }

    public String getIdentifier() {
        return "news";
    }
}
