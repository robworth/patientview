package com.worthsoln.patientview.unit;

import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.type.Type;
import com.worthsoln.HibernateUtil;
import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.User;
import com.worthsoln.patientview.logon.LogonUtils;

public class UnitsDisplayAction extends DatabaseAction {

    public ActionForward execute(
            ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        User user = (User) HibernateUtil.getPersistentObject(User.class,
                LegacySpringUtils.getSecurityUserManager().getLoggedInUsername());
        List items = new ArrayList();

        if (user.getRole().equals("superadmin")) {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            items = session.find("from " + Unit.class.getName() + " order by name");
            tx.commit();
            HibernateUtil.closeSession();
        } else {
            List<String> unitcodes = UnitUtils.usersUnitCodes(request);
            if (unitcodes.size() != 0) {
                String unitCodeClause = "";
                Object[] params = new Object[unitcodes.size()];
                Type[] types = new Type[unitcodes.size()];

                for (int i = 0; i < unitcodes.size(); i++) {
                    unitCodeClause += " unit.unitcode = ? or ";
                    params[i] = unitcodes.get(i);
                    types[i] = Hibernate.STRING;
                }
                unitCodeClause = unitCodeClause.substring(0, unitCodeClause.length() - 3);

                Session session = HibernateUtil.currentSession();
                Transaction tx = session.beginTransaction();
                items = session.find("from " + Unit.class.getName() + " as unit where " + unitCodeClause, params, types);
                tx.commit();
                HibernateUtil.closeSession();
            }
        }

        request.getSession().setAttribute("units", items);

        return LogonUtils.logonChecks(mapping, request);
    }

    public String getDatabaseName() {
        return "patientview";
    }

    public String getIdentifier() {
        return "unit";
    }
}
