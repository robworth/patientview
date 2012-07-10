package com.worthsoln.patientview.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.type.Type;
import com.worthsoln.HibernateUtil;
import com.worthsoln.patientview.PatientUtils;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.database.action.DatabaseAction;

import java.util.List;

public class CommentViewAllAction extends DatabaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        String nhsno = PatientUtils.retrieveNhsNo(request);

        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        List<Comment> comments = session.find("from " + Comment.class.getName() + " as comment where comment.nhsno = ? " ,
                             new Object[] {nhsno}, new Type[] {Hibernate.STRING});
        tx.commit();
        HibernateUtil.closeSession();

        if (CommentUtils.verifyPermissionToReadItem(request, nhsno)) {
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