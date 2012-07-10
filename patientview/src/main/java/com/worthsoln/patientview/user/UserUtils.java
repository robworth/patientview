package com.worthsoln.patientview.user;

import com.worthsoln.HibernateUtil;
import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.database.DatabaseUpdateQuery;
import com.worthsoln.patientview.User;
import com.worthsoln.patientview.logon.UserMapping;
import com.worthsoln.patientview.unit.UnitUtils;
import com.worthsoln.utils.LegacySpringUtils;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.type.Type;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class UserUtils {

    public static User retrieveUser(HttpServletRequest request) {
        String username = null;

        if (!LegacySpringUtils.getSecurityUserManager().isRolePresent("patient")) {
            username = (String) request.getSession().getAttribute("userBeingViewedUsername");
        }

        if (username == null || "".equals(username)) {
            username = LegacySpringUtils.getSecurityUserManager().getLoggedInUsername();
        }

        User user = (User) HibernateUtil.getPersistentObject(User.class, username);

        return user;
    }

    public static List<UserMapping> retrieveUserMappings(User user) {
        return retrieveUserMappings(user.getUsername());
    }

    public static List<UserMapping> retrieveUserMappings(String username) {
        List userMappings = new ArrayList();
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();


            userMappings = session.find("from " + UserMapping.class.getName() + " as usermapping " +
                    " where usermapping.username = ? ",
                    new Object[]{username}, new Type[]{Hibernate.STRING});
            tx.commit();
            HibernateUtil.closeSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return userMappings;
    }

    public static List<UserMapping> retrieveUserMappingsExcludeUnitcode(String username, String unitcode) {
        List userMappings = new ArrayList();
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();


            userMappings = session.find("from " + UserMapping.class.getName() + " as usermapping " +
                    " where usermapping.username = ? and usermapping.unitcode != ?",
                    new Object[]{username, unitcode}, new Type[]{Hibernate.STRING, Hibernate.STRING});
            tx.commit();
            HibernateUtil.closeSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return userMappings;
    }

    public static List<UserMapping> retrieveUserMappings(String username, String unitcode) {
        List userMappings = new ArrayList();
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();


            userMappings = session.find("from " + UserMapping.class.getName() + " as usermapping " +
                    " where usermapping.username = ? and usermapping.unitcode = ?",
                    new Object[]{username, unitcode}, new Type[]{Hibernate.STRING, Hibernate.STRING});
            tx.commit();
            HibernateUtil.closeSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return userMappings;
    }

    public static List<UserMapping> retrieveUserMappingsForNhsno(String nhsno) {
        List<UserMapping> userMappings = new ArrayList();
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();


            userMappings = session.find("from " + UserMapping.class.getName() + " as usermapping " +
                    " where usermapping.nhsno = ? ",
                    new Object[]{nhsno}, new Type[]{Hibernate.STRING});
            tx.commit();
            HibernateUtil.closeSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return userMappings;
    }

    public static String retrieveUsersRealUnitcodeBestGuess(String username) {
        List<UserMapping> userMappings = new ArrayList();
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();


            userMappings = session.find("from " + UserMapping.class.getName() + " as usermapping " +
                    " where usermapping.username = ? and usermapping.unitcode != ?",
                    new Object[]{username, UnitUtils.PATIENT_ENTERS_UNITCODE}, new Type[]{Hibernate.STRING, Hibernate.STRING});
            tx.commit();
            HibernateUtil.closeSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        if (userMappings.isEmpty()) {
            return "";
        } else {
            return userMappings.get(0).getUnitcode();
        }
    }

    public static String retrieveUsersRealNhsnoBestGuess(String username) {
        List<UserMapping> userMappings = new ArrayList();
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();


            userMappings = session.find("from " + UserMapping.class.getName() + " as usermapping " +
                    " where usermapping.username = ? and usermapping.unitcode != ?",
                    new Object[]{username, UnitUtils.PATIENT_ENTERS_UNITCODE}, new Type[]{Hibernate.STRING, Hibernate.STRING});
            tx.commit();
            HibernateUtil.closeSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        if (userMappings.isEmpty()) {
            return "";
        } else {
            return userMappings.get(0).getNhsno();
        }
    }

    public static UserMapping retrieveUserMappingsPatientEntered(User user) {
        List userMappings = new ArrayList();
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();


            userMappings = session.find("from " + UserMapping.class.getName() + " as usermapping " +
                    " where usermapping.username = ? ",
                    new Object[]{user.getUsername()}, new Type[]{Hibernate.STRING});
            tx.commit();
            HibernateUtil.closeSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }


        UserMapping patientEntryUserMapping = null;
        UserMapping anyOtherUserMapping = null;

        for (Object obj : userMappings) {
            UserMapping currentUserMapping = (UserMapping) obj;

            if (UnitUtils.PATIENT_ENTERS_UNITCODE.equals(currentUserMapping.getUnitcode())) {
                patientEntryUserMapping = currentUserMapping;
            } else {
                anyOtherUserMapping = currentUserMapping;
            }
        }

        if (patientEntryUserMapping == null) {
            if (anyOtherUserMapping != null) {
                patientEntryUserMapping = anyOtherUserMapping;
                patientEntryUserMapping = new UserMapping(anyOtherUserMapping.getUsername(), UnitUtils.PATIENT_ENTERS_UNITCODE, anyOtherUserMapping.getNhsno());
                try {
                    HibernateUtil.saveOrUpdateWithTransaction(patientEntryUserMapping);
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }

        return patientEntryUserMapping;
    }

    public static void removePatientFromSystem(String nhsno, String unitcode) {
        String[] tableNames = new String[]{"user", "testresult", "letter",};        //TODO add medicines and diagnosis
        for (int i = 0; i < tableNames.length; i++) {
            runDeleteQuery("DELETE FROM " + tableNames[i] + " WHERE nhsno = ? AND unitcode = ?", nhsno, unitcode);
        }
        runDeleteQuery("DELETE FROM patient WHERE nhsno = ? and centreCode = ?", nhsno, unitcode);
    }

    private static void runDeleteQuery(String removePatientSql, String nhsno, String unitcode) {
        DatabaseUpdateQuery query = new DatabaseUpdateQuery(removePatientSql, new Object[]{nhsno, unitcode});
        DatabaseDAO dao = new DatabaseDAO("patientview");
        dao.doExecute(query);
    }

    public static boolean nhsNumberChecksumValid(String nhsno) {
        if (nhsno.length() != 10) {
            return false;
        } else { //generate the checksum using modulus 11 algorithm
            int checksum = 0;

            //multiply each of the first 9 digits by 10-character position (where the left character is in position 0)
            for (int i = 0; i <= 8; i++) {//sum of each
                int digit = Integer.parseInt(nhsno.substring(i, i+1));
                checksum +=  digit * (10 - i);
            }
            //(modulus 11)
            checksum = 11 - checksum % 11;

            if (checksum == 11) {
                checksum = 0;
            }

            //does checksum match the 10th digit?
            return checksum == Integer.parseInt(nhsno.substring(9));
        }
    }
}
