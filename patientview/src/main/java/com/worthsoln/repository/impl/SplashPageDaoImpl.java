package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.SplashPage;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.SplashPageDao;

import java.util.List;

/**
 *
 */
public class SplashPageDaoImpl extends AbstractHibernateDAO<SplashPage> implements SplashPageDao {

    @Override
    public List<SplashPage> getAll(User user) {

//        User user = (User) HibernateUtil.getPersistentObject(User.class, LegacySpringUtils.getSecurityUserManager().getLoggedInUsername());
//        String userType = user.getRole();
//        Object[] params = new Object[]{};
//        Type[] types = new Type[]{};
//
//        String hsql = "from " + SplashPage.class.getName() + " as splashpage";
//
//        if ("unitadmin".equals(userType)) {
//            List<String> unitcodes = UnitUtils.usersUnitCodes(request);
//
//            String unitCodeClause = "";
//            params = new Object[unitcodes.size()];
//            types = new Type[unitcodes.size()];
//
//            for (int i = 0; i < unitcodes.size(); i++) {
//                unitCodeClause += " splashpage.unitcode = ? or ";
//                params[i] = unitcodes.get(i);
//                types[i] = Hibernate.STRING;
//            }
//
//            unitCodeClause = unitCodeClause.substring(0, unitCodeClause.length() - 3);
//
//            hsql += " where " + unitCodeClause;
//        }
//
//        List splashpages = new ArrayList();
//        try {
//            Session session = HibernateUtil.currentSession();
//            Transaction tx = session.beginTransaction();
//            splashpages = session.find(hsql, params, types);
//            tx.commit();
//            HibernateUtil.closeSession();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        }

        return null;
    }

    @Override
    public List<SplashPage> getAllForPatient(User user) {

//        String hsql = "from " + SplashPage.class.getName() + " as splashpage ";
//
//        List<String> unitcodes = UnitUtils.usersUnitCodes(user);
//        unitcodes.add("ALL");
//
//        String unitCodeClause = "";
//        Object[] params = new Object[unitcodes.size()];
//        Type[] types = new Type[unitcodes.size()];
//
//        for (int i = 0; i < unitcodes.size(); i++) {
//            unitCodeClause += " splashpage.unitcode = ? or ";
//            params[i] = unitcodes.get(i);
//            types[i] = Hibernate.STRING;
//        }
//
//        unitCodeClause = unitCodeClause.substring(0, unitCodeClause.length() - 3);
//
//        hsql += " where " + unitCodeClause;
//
//        List splashpages = new ArrayList();
//        try {
//            Session session = HibernateUtil.currentSession();
//            Transaction tx = session.beginTransaction();
//            splashpages = session.find(hsql, params, types);
//            tx.commit();
//            HibernateUtil.closeSession();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        }

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<SplashPage> getSeenForPatient(User user) {

//        List<SplashPageUserSeen> splashPagesUserSeen = new ArrayList<SplashPageUserSeen>();
//
//
//        String hsql = "from " + SplashPageUserSeen.class.getName() + " as splashpageuserseen " +
//                " where splashpageuserseen.username = ?";
//        Object[] params = new Object[]{user.getUsername()};
//        Type[] types = new Type[]{Hibernate.STRING};
//
//        try {
//            Session session = HibernateUtil.currentSession();
//            Transaction tx = session.beginTransaction();
//            splashPagesUserSeen = session.find(hsql, params, types);
//            tx.commit();
//            HibernateUtil.closeSession();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        }

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeSeenSplashPage(Long id) {

//        String hql = "from " + SplashPageUserSeen.class.getName() + " where splashpageid = ?";
//        try {
//            Session session = HibernateUtil.currentSession();
//            Transaction tx = session.beginTransaction();
//            session.delete(hql, splashPageSeenId, Hibernate.INTEGER);
//            tx.commit();
//            HibernateUtil.closeSession();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        }
    }
}
