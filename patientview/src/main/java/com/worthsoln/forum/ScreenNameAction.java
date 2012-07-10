package com.worthsoln.forum;

import com.worthsoln.HibernateUtil;
import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.User;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.utils.LegacySpringUtils;
import net.jforum.dao.DataAccessDriver;
import net.jforum.dao.UserDAO;
import net.jforum.sso.SSOUtils;
import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.expression.Expression;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ScreenNameAction extends DatabaseAction {


    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        String screenName = request.getParameter("screenname");
        if (screenName == null || screenName.trim().length() == 0) {
            return mapping.findForward("input");
        }
        User user = setScreenName(request, screenName);
        setJforumScreenname(user);

        return LogonUtils.logonChecks(mapping, request);
    }

    private User setScreenName(HttpServletRequest request, String screenName) {
        User user = null;
        try {
            String username = LegacySpringUtils.getSecurityUserManager().getLoggedInUsername();
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Expression.like("username", username));
            user = (User) criteria.uniqueResult();
            user.setScreenname(screenName);
            session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            try {
                HibernateUtil.closeSession();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    private void setJforumScreenname(com.worthsoln.patientview.User user) {
        SSOUtils ssoutils = new SSOUtils();

        if (!ssoutils.userExists(user.getUsername())) {
            net.jforum.entities.User jforumuser = new net.jforum.entities.User();

            jforumuser.setUsername(user.getUsername());
            jforumuser.setPassword("sso");
            String email = null == user.getEmail() ? "" : user.getEmail();
            jforumuser.setEmail(email);
            jforumuser.setFrom(user.getScreenname());
            jforumuser.setActive(1);

            UserDAO dao = DataAccessDriver.getInstance().newUserDAO();

            dao.addNew(jforumuser);



            net.jforum.entities.User addedjforumuser = dao.selectByName(user.getUsername());



            addedjforumuser.setFrom(user.getScreenname());

            dao.update(addedjforumuser);


        }
    }

    public String getDatabaseName() {
        return "patientview";
    }

    public String getIdentifier() {
        return "user";
    }
}
