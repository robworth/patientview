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
        putRelevantUnitsInRequestMinusSomeUnits(request, new String[]{PATIENT_ENTERS_UNITCODE}, new String[]{});
    }

    public static void putRelevantUnitsInRequestMinusSomeUnits(HttpServletRequest request, String[] notTheseUnitCodes, String[] plusTheseUnitCodes) throws HibernateException {
        List items = fetchRelevantUnits(request, notTheseUnitCodes, plusTheseUnitCodes);
        request.getSession().setAttribute("units", items);
    }

    public static List fetchRelevantUnits(HttpServletRequest request, String[] notTheseUnitCodes, String[] plusTheseUnitCodes) throws HibernateException {
        List<String> unitcodes = usersUnitCodes(request);
        List<Unit> items;
        String addAND = " AND ";
        if (unitcodes.size() == 0) {
            String queryString = "";
            if (notTheseUnitCodes.length == 0) {
                queryString = "from " + Unit.class.getName() + " order by name ";

                Session session = HibernateUtil.currentSession();
                Transaction tx = session.beginTransaction();
                items = session.find(queryString);
                tx.commit();
                HibernateUtil.closeSession();
            } else {
                queryString = "from " + Unit.class.getName() + " where ";
                Object[] notTheseUnitcodeArray = new Object[notTheseUnitCodes.length];
                Type[] typeArray = new Type[notTheseUnitCodes.length];

                for (int i = 0; i < notTheseUnitCodes.length; i++) {
                    queryString += " unitcode != ? " + addAND;
                    notTheseUnitcodeArray[i] = notTheseUnitCodes[i];
                    typeArray[i] = Hibernate.STRING;
                }
                queryString = queryString.substring(0, queryString.length() - addAND.length());

                queryString += " order by name";

                Session session = HibernateUtil.currentSession();
                Transaction tx = session.beginTransaction();
                items = session.find(queryString, notTheseUnitcodeArray, typeArray);
                tx.commit();
                HibernateUtil.closeSession();
            }
        } else {
            String queryString = "from " + Unit.class.getName() + " as unit where ( ";
            Object[] unitcodeArray = new Object[unitcodes.size() + notTheseUnitCodes.length];
            Type[] typeArray = new Type[unitcodes.size() + notTheseUnitCodes.length];

            for (int i = 0; i < unitcodes.size(); i++) {
                queryString += " unit.unitcode = ? OR ";
                unitcodeArray[i] = unitcodes.get(i);
                typeArray[i] = Hibernate.STRING;
            }
            queryString = queryString.substring(0, queryString.length() - 3);

            queryString += " ) ";

            if (notTheseUnitCodes.length != 0) {
                for (int i = 0; i < notTheseUnitCodes.length; i++) {
                    queryString += addAND + " unitcode != ? ";
                    unitcodeArray[unitcodes.size() + i] = notTheseUnitCodes[i];
                    typeArray[unitcodes.size() + i] = Hibernate.STRING;
                }
            }

            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            items = session.find(queryString, unitcodeArray, typeArray);
            tx.commit();
            HibernateUtil.closeSession();
        }

        for (String unitcode : plusTheseUnitCodes) {
            Unit unit = new Unit(unitcode);
            unit.setName(unitcode);
            items.add(unit);
        }

        return items;
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
        User user = (User) HibernateUtil.getPersistentObject(User.class, LegacySpringUtils.getSecurityUserManager().getLoggedInUsername());
        return usersUnitCodes(user);
    }

    public static List<String> usersUnitCodes(User user) {
        List<String> unitcodes = new ArrayList();

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