package com.worthsoln.patientview.logon;

import com.worthsoln.HibernateUtil;
import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.logging.AddLog;
import com.worthsoln.patientview.unit.UnitUtils;
import com.worthsoln.patientview.user.EmailVerificationUtils;
import com.worthsoln.utils.LegacySpringUtils;
import net.sf.hibernate.Criteria;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.expression.Expression;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UnitAdminAddAction extends DatabaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        String username = BeanUtils.getProperty(form, "username");
        String password = LogonUtils.generateNewPassword();
        String name = BeanUtils.getProperty(form, "name");
        String email = BeanUtils.getProperty(form, "email");
        String unitcode = BeanUtils.getProperty(form, "unitcode");
        String role = BeanUtils.getProperty(form, "role");
        UnitAdmin unitAdmin = new UnitAdmin(username, password, name, email, false, role, true);
        DatabaseDAO dao = getDao(request);
        User existingAdminWithSameUsername = (User) HibernateUtil.getPersistentObject(User.class, username);


        Session session1 = HibernateUtil.currentSession();
        Transaction tx1 = session1.beginTransaction();
        Criteria criteria1 = session1.createCriteria(UserMapping.class);
        criteria1.add(Expression.eq("username", username));
        List usermappingList = criteria1.list();
        tx1.commit();
        HibernateUtil.closeSession();


        String mappingToFind;
        if (!usermappingList.isEmpty()) {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(UserMapping.class);
            criteria.add(Expression.eq("username", username));
            criteria.add(Expression.eq("unitcode", unitcode));
            UserMapping userMapping = (UserMapping) criteria.uniqueResult();
            tx.commit();
            HibernateUtil.closeSession();

            if (userMapping != null) {
                request.setAttribute(LogonUtils.USER_ALREADY_EXISTS, username);
                unitAdmin.setUsername("");
                UnitUtils.putRelevantUnitsInRequest(request);
                mappingToFind = "input";
            } else {
                UserMapping userMappingNew = new UserMapping(username, unitcode, "");
                request.setAttribute("usermapping", userMappingNew);
                mappingToFind = "existinguser";
            }
        } else {
            UnitAdmin hashedUnitAdmin = (UnitAdmin) unitAdmin.clone();
            hashedUnitAdmin.setPassword(LogonUtils.hashPassword(hashedUnitAdmin.getPassword()));
            dao.insertItem(new LogonDao(hashedUnitAdmin));

            UserMapping userMapping = new UserMapping(username, unitcode, "");
            HibernateUtil.saveOrUpdateWithTransaction(userMapping);

            AddLog.addLog(LegacySpringUtils.getSecurityUserManager().getLoggedInUsername(), AddLog.ADMIN_ADD,
                    unitAdmin.getUsername(), "",
                    unitcode, "");
            EmailVerificationUtils.createEmailVerification(hashedUnitAdmin.getUsername(), hashedUnitAdmin.getEmail(), request);
            mappingToFind = "success";
        }
        request.setAttribute("adminuser", unitAdmin);
        return mapping.findForward(mappingToFind);
    }

    public String getIdentifier() {
        return null;
    }

    public String getDatabaseName() {
        return "patientview";
    }
}
