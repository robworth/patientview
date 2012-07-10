package com.worthsoln.patientview.logon;

import com.worthsoln.HibernateUtil;
import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.User;
import com.worthsoln.patientview.user.UserUtils;
import com.worthsoln.patientview.logging.AddLog;
import com.worthsoln.patientview.logging.LogEntry;
import com.worthsoln.patientview.news.NewsUtils;
import com.worthsoln.patientview.unit.Unit;
import com.worthsoln.utils.LegacySpringUtils;
import net.sf.hibernate.Criteria;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.Order;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggedInAction extends DatabaseAction {

    private final DateFormat format = new SimpleDateFormat("d MMM yyyy HH:mm");

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        String forward = "";
        ActionUtils.setUpNavLink(mapping.getParameter(), request);
        NewsUtils.putAppropriateNewsForViewingInRequest(request);
        String username = LegacySpringUtils.getSecurityUserManager().getLoggedInUsername();
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Expression.like("username", username));
        User user = (User) criteria.uniqueResult();
        tx.commit();
        HibernateUtil.closeSession();
        if (user != null) {

            // For JForum SSO need to set some variables in the session
            request.getSession().setAttribute("sso.email.attribute", user.getEmail());
            // Not sure if this makes a difference if it's encrypted
            request.getSession().setAttribute("sso.password.attribute", user.getPassword());

            // Is user patient or admin?
            if ("patient".equals(user.getRole())) {
                request.setAttribute("isPatient", true);
            }
            if ((user.getLastlogon() != null)) {
                request.setAttribute("lastLogin", format.format(user.getLastlogon()));
            }
            user.setLastlogon(new Date());
            session = HibernateUtil.currentSession();
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
            HibernateUtil.closeSession();
            if ("patient".equals(user.getRole())) {
                String nhsno = UserUtils.retrieveUsersRealNhsnoBestGuess(username);
                if (nhsno != null && !nhsno.equals("")) {
                    session = HibernateUtil.currentSession();
                    tx = session.beginTransaction();
                    criteria = session.createCriteria(LogEntry.class);
                    criteria.add(Expression.eq("nhsno", nhsno));
                    criteria.add(Expression.like("action", AddLog.PATIENT_DATA_FOLLOWUP));
                    criteria.addOrder(Order.desc("date"));
                    criteria.setMaxResults(1);
                    LogEntry log = (LogEntry) criteria.uniqueResult();
                    if (log != null) {
                        request.setAttribute("lastDataDate", log.getDate().getTime());
                        // Get the unit from the unitcode
                        String unitcode = log.getUnitcode();
                        if (unitcode != null) {
                            criteria = session.createCriteria(Unit.class);
                            criteria.add(Expression.eq("unitcode", unitcode));
                            criteria.setMaxResults(1);
                            Unit unit = (Unit) criteria.uniqueResult();
                            if (null == unit) {
                                request.setAttribute("lastDataFrom", "Unit with code: " + unitcode);
                            } else {
                                request.setAttribute("lastDataFrom", unit.getName());
                            }
                        }
                    }
                    tx.commit();
                    HibernateUtil.closeSession();
                }
                forward = "patient";
            } else {
                forward = "admin";
            }
        }
        return LogonUtils.logonChecks(mapping, request, forward);
    }

    public String getDatabaseName() {
        return "patientview";
    }

    public String getIdentifier() {
        return "user";
    }

}
