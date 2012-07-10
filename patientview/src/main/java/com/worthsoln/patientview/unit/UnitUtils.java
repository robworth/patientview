package com.worthsoln.patientview.unit;

import com.worthsoln.HibernateUtil;
import com.worthsoln.patientview.User;
import com.worthsoln.patientview.logon.UserMapping;
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

public class UnitUtils {

    public static String PATIENT_ENTERS_UNITCODE = "PATIENT";

    public static void putRelevantUnitsInRequest(HttpServletRequest request) throws HibernateException {
        List<String> unitcodes = usersUnitCodes(request);
        List items;
        if (unitcodes.size() == 0) {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            items = session.find("from " + Unit.class.getName() + " order by name");
            tx.commit();
            HibernateUtil.closeSession();
        } else {
            String queryString = "from " + Unit.class.getName() + " as unit where ";
            Object[] unitcodeArray = new Object[unitcodes.size()];
            Type[] typeArray = new Type[unitcodes.size()];

            for (int i = 0; i < unitcodes.size(); i++) {
                queryString += " unit.unitcode = ? OR ";
                unitcodeArray[i] = unitcodes.get(i);
                typeArray[i] = Hibernate.STRING;
            }
            
            queryString = queryString.substring(0, queryString.length() - 3);

            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            items = session.find(queryString, unitcodeArray, typeArray);
            tx.commit();
            HibernateUtil.closeSession();
        }
        request.getSession().setAttribute("units", items);
    }

    public static Unit retrieveUnit(String unitcode) {
        unitcode = unitcode.toUpperCase();
        Unit unit = null;
        try {
            unit = (Unit) HibernateUtil.getPersistentObject(Unit.class, unitcode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unit;
    }

    public static List<String> usersUnitCodes(HttpServletRequest request) {
        List<String> unitcodes = new ArrayList();
        User user = (User) HibernateUtil.getPersistentObject(User.class,
                LegacySpringUtils.getSecurityUserManager().getLoggedInUsername());
        if (!user.getRole().equals("superadmin")) {

            List<UserMapping> userMappings = UserUtils.retrieveUserMappings(user);

            for (UserMapping userMapping : userMappings) {
                if (!UnitUtils.PATIENT_ENTERS_UNITCODE.equalsIgnoreCase(userMapping.getUnitcode())) {
                    unitcodes.add(userMapping.getUnitcode());
                }
            }
        }
        return unitcodes;
    }

    public static String retrieveUnitcode(HttpServletRequest request) {
        User user = UserUtils.retrieveUser(request);

        UserMapping userMapping = UserUtils.retrieveUserMappingsPatientEntered(user);

        return userMapping.getUnitcode();
    }

}
