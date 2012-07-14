package com.worthsoln.patientview.splashpage;

import com.worthsoln.HibernateUtil;
import com.worthsoln.patientview.User;
import com.worthsoln.patientview.unit.UnitUtils;
import com.worthsoln.patientview.user.UserUtils;
import com.worthsoln.utils.LegacySpringUtils;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.type.Type;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


public class SplashPageUtils {

    static List<SplashPage> retrieveSplashPages(HttpServletRequest request) {
        User user = (User) HibernateUtil.getPersistentObject(User.class, LegacySpringUtils.getSecurityUserManager().getLoggedInUsername());
        String userType = user.getRole();
        Object[] params = new Object[]{};
        Type[] types = new Type[]{};

        String hsql = "from " + SplashPage.class.getName() + " as splashpage";

        if ("unitadmin".equals(userType)) {
            List<String> unitcodes = UnitUtils.usersUnitCodes(request);

            String unitCodeClause = "";
            params = new Object[unitcodes.size()];
            types = new Type[unitcodes.size()];

            for (int i = 0; i < unitcodes.size(); i++) {
                unitCodeClause += " splashpage.unitcode = ? or ";
                params[i] = unitcodes.get(i);
                types[i] = Hibernate.STRING;
            }

            unitCodeClause = unitCodeClause.substring(0, unitCodeClause.length() - 3);

            hsql += " where " + unitCodeClause;
        }

        List splashpages = new ArrayList();
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            splashpages = session.find(hsql, params, types);
            tx.commit();
            HibernateUtil.closeSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return splashpages;
    }

    public static List<SplashPage> retrieveSplashPagesForPatient(User user) {
        String hsql = "from " + SplashPage.class.getName() + " as splashpage ";

        List<String> unitcodes = UnitUtils.usersUnitCodes(user);
        unitcodes.add("ALL");

        String unitCodeClause = "";
        Object[] params = new Object[unitcodes.size()];
        Type[] types = new Type[unitcodes.size()];

        for (int i = 0; i < unitcodes.size(); i++) {
            unitCodeClause += " splashpage.unitcode = ? or ";
            params[i] = unitcodes.get(i);
            types[i] = Hibernate.STRING;
        }

        unitCodeClause = unitCodeClause.substring(0, unitCodeClause.length() - 3);

        hsql += " where " + unitCodeClause;

        List splashpages = new ArrayList();
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            splashpages = session.find(hsql, params, types);
            tx.commit();
            HibernateUtil.closeSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return splashpages;
    }

    public static List<SplashPageUserSeen> retrieveSplashPagesPatientHasSeen(User user) {
        List<SplashPageUserSeen> splashPagesUserSeen = new ArrayList<SplashPageUserSeen>();


        String hsql = "from " + SplashPageUserSeen.class.getName() + " as splashpageuserseen " +
                " where splashpageuserseen.username = ?";
        Object[] params = new Object[]{user.getUsername()};
        Type[] types = new Type[]{Hibernate.STRING};

        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            splashPagesUserSeen = session.find(hsql, params, types);
            tx.commit();
            HibernateUtil.closeSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return splashPagesUserSeen;
    }

    static void putSplashPageUnitsInRequest(HttpServletRequest request) throws HibernateException {
        List<SplashPage> splashpages = SplashPageUtils.retrieveSplashPages(request);
        String[] unitsWithSplashPages = new String[splashpages.size() + 1];


        boolean allUnitsSplashPageAlreadyExists = false;

        for (int i = 0; i < splashpages.size(); i++) {
            unitsWithSplashPages[i] = splashpages.get(i).getUnitcode();
            if (unitsWithSplashPages[i].equals("ALL")) {
                allUnitsSplashPageAlreadyExists = true;
            }
        }

        unitsWithSplashPages[splashpages.size()] = UnitUtils.PATIENT_ENTERS_UNITCODE;

        if (UserUtils.isUserInRole(request, "superadmin") && !allUnitsSplashPageAlreadyExists) {
            UnitUtils.putRelevantUnitsInRequestMinusSomeUnits(request, unitsWithSplashPages, new String[]{"ALL"});
        } else {
            UnitUtils.putRelevantUnitsInRequestMinusSomeUnits(request, unitsWithSplashPages, new String[]{});
        }
    }

    static void removeSplashPagesSeen(int splashPageSeenId) {
        String hql = "from " + SplashPageUserSeen.class.getName() + " where splashpageid = ?";
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            session.delete(hql, splashPageSeenId, Hibernate.INTEGER);
            tx.commit();
            HibernateUtil.closeSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}