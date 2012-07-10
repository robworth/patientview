package com.worthsoln.patientview;

import com.worthsoln.HibernateUtil;
import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.logging.AddLog;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.patientview.user.UserUtils;
import net.sf.hibernate.Criteria;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForgottenPasswordAction extends DatabaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String forwardMapping = "success";

        if (username != null && username.trim().length() > 0 && email != null && email.trim().length() > 0) {
            Session session = HibernateUtil.currentSession();
            //Transaction tx = session.beginTransaction();

            // From the username get the number of failed logins and display the locked out message if that's the case
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Expression.like("username", username));
            User user = (User) criteria.uniqueResult();

            request.setAttribute("foundUser", user != null);

            if (user != null) {
                String storedEmail = user.getEmail();
                if (storedEmail != null && user.getEmail().trim().length() > 0) {
                    if (email.equalsIgnoreCase(storedEmail)) {
                        String password = LogonUtils.generateNewPassword();
                        user.setPassword(LogonUtils.hashPassword(password));
                        user.setFirstlogon(true);

                        // Email password
                        String message = "Hello User,\n" +
                                "\n" +
                                "Your password has been reset, your new password is\n" +
                                password + "\n" +
                                "\n" +
                                "Renal Patient View";
                        String fromAddress = request.getSession().getServletContext().getInitParameter("noreply.email");
                        EmailUtils.sendEmail(request.getSession().getServletContext(), fromAddress, user.getEmail(),
                                "Renal Patient View - Your password has been reset", message);
                        session.save(user);

                        AddLog.addLog("system", AddLog.PASSWORD_RESET_FORGOTTEN, username, "",
                                UserUtils.retrieveUsersRealUnitcodeBestGuess(username), user.getEmail());
                    } else {
                        request.setAttribute("noMatch", true);
                        forwardMapping = "input";
                    }
                } else {
                    request.setAttribute("nullEmail", true);
                    forwardMapping = "input";
                }
            } else {
                request.setAttribute("nullUser", true);
                forwardMapping = "input";
            }
            //tx.commit();
            HibernateUtil.closeSession();
        } else {
            request.setAttribute("nullUser", true);
            forwardMapping = "input";
        }
        return mapping.findForward(forwardMapping);
    }

    public String getDatabaseName() {
        return "patientview";
    }

    public String getIdentifier() {
        return "user";
    }
}