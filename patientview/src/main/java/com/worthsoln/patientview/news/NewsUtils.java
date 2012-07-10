package com.worthsoln.patientview.news;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.worthsoln.utils.LegacySpringUtils;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.type.Type;
import com.worthsoln.HibernateUtil;
import com.worthsoln.patientview.User;
import com.worthsoln.patientview.unit.UnitUtils;

public class NewsUtils {

    public static void putAppropriateNewsForViewingInRequest(HttpServletRequest request) throws HibernateException {
        String hsql = "";
        Object[] params = new Object[]{};
        Type[] types = new Type[]{};
        String username = LegacySpringUtils.getSecurityUserManager().getLoggedInUsername();
        if (username == null) {
            hsql = "from " + News.class.getName() + " as news where news.everyone = ?";
            params = new Object[]{1};
            types = new Type[]{Hibernate.INTEGER};
        } else {
            User user = (User) HibernateUtil.getPersistentObject(User.class, username);
            String userType = user.getRole();
            if ("superadmin".equals(userType)) {
                hsql = "from " + News.class.getName() + " as news";
            } else if ("unitadmin".equals(userType) || "unitstaff".equals(userType)) {
                List<String> unitcodes = UnitUtils.usersUnitCodes(request);

                String unitCodeClause = "";
                params = new Object[unitcodes.size() + 4];
                types = new Type[unitcodes.size() + 4];
                unitCodeClause = createUnitCodeStringParamsAndTypes(params, types, unitcodes, unitCodeClause);

                hsql = "from " + News.class.getName() +
                        " as news where (( " + unitCodeClause + " ) and (news.admin = ? or news.patient = ?)) " +
                        " or news.everyone = ?";
                params[unitcodes.size()] = "all";
                params[unitcodes.size() + 1] = 1;
                params[unitcodes.size() + 2] = 1;
                params[unitcodes.size() + 3] = 1;

                types[unitcodes.size()] = Hibernate.STRING;
                types[unitcodes.size() + 1] = Hibernate.INTEGER;
                types[unitcodes.size() + 2] = Hibernate.INTEGER;
                types[unitcodes.size() + 3] = Hibernate.INTEGER;
            } else if ("patient".equals(userType)) {
                List<String> unitcodes = UnitUtils.usersUnitCodes(request);

                String unitCodeClause = "";
                params = new Object[unitcodes.size() + 3];
                types = new Type[unitcodes.size() + 3];
                unitCodeClause = createUnitCodeStringParamsAndTypes(params, types, unitcodes, unitCodeClause);

                hsql = "from " + News.class.getName() +
                        " as news where (( " + unitCodeClause + " ) and news.patient = ?) or news.everyone = ?";
                params[unitcodes.size()] = "all";
                params[unitcodes.size() + 1] = 1;
                params[unitcodes.size() + 2] = 1;

                types[unitcodes.size()] = Hibernate.STRING;
                types[unitcodes.size() + 1] = Hibernate.INTEGER;
                types[unitcodes.size() + 2] = Hibernate.INTEGER;
            }
        }

        hsql = hsql + " order by news.datestamp desc ";

        List items = getNewsList(hsql, params, types);
        request.setAttribute("newses", items);
    }

    private static String createUnitCodeStringParamsAndTypes(Object[] params, Type[] types, List<String> unitcodes, String unitCodeClause) {
        for (int i = 0; i < unitcodes.size(); i++) {
            unitCodeClause += " news.unitcode = ? or ";
            params[i] = unitcodes.get(i);
            types[i] = Hibernate.STRING;
        }
        unitCodeClause += " news.unitcode = ? ";
        return unitCodeClause;
    }

    public static void putAppropriateNewsForEditInRequest(HttpServletRequest request) throws HibernateException {
        String hsql = "";
        Object[] params = new Object[]{};
        Type[] types = new Type[]{};
        String username = LegacySpringUtils.getSecurityUserManager().getLoggedInUsername();
        if (username == null) {
            hsql = "from " + News.class.getName() + " as news where news.everyone = ?";
            params = new Object[]{1};
            types = new Type[]{Hibernate.INTEGER};
        } else {
            User user = (User) HibernateUtil.getPersistentObject(User.class, username);
            String userType = user.getRole();
            if ("superadmin".equals(userType)) {
                hsql = "from " + News.class.getName() + " as news";
            } else if ("unitadmin".equals(userType)) {

                List<String> unitcodes = UnitUtils.usersUnitCodes(request);

                String unitCodeClause = "";
                params = new Object[unitcodes.size() + 2];
                types = new Type[unitcodes.size() + 2];

                for (int i = 0; i < unitcodes.size(); i++) {
                    unitCodeClause += " news.unitcode = ? or ";
                    params[i] = unitcodes.get(i);
                    types[i] = Hibernate.STRING;
                }
                unitCodeClause = unitCodeClause.substring(0, unitCodeClause.length() - 3);

                hsql = "from " + News.class.getName() +
                        " as news where (" + unitCodeClause + " and (news.admin = ? or news.patient = ?)) ";
                params[unitcodes.size()] = 1;
                params[unitcodes.size() + 1] = 1;

                types[unitcodes.size()] = Hibernate.INTEGER;
                types[unitcodes.size() + 1] = Hibernate.INTEGER;
            }
        }
        List items = getNewsList(hsql, params, types);
        request.setAttribute("newses", items);
    }

    private static List getNewsList(String hsql, Object[] params, Type[] types) {
        List items = null;
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            items = session.find(hsql, params, types);
            tx.commit();
            HibernateUtil.closeSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return items;
    }
}
