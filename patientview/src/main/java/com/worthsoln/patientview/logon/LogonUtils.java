package com.worthsoln.patientview.logon;

import com.Ostermiller.util.RandPass;
import com.worthsoln.HibernateUtil;
import com.worthsoln.patientview.User;
import com.worthsoln.patientview.logging.AddLog;
import com.worthsoln.patientview.splashpage.SplashPage;
import com.worthsoln.patientview.user.UserUtils;
import com.worthsoln.utils.LegacySpringUtils;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

public class LogonUtils {

    public static final String USER_ALREADY_EXISTS = "userAlreadyExists";
    public static final String NHSNO_ALREADY_EXISTS = "nhsnoAlreadyExists";
    public static final String PATIENTS_WITH_SAME_NHSNO = "nhsnoAlreadyExists";
    public static final String PATIENT_ALREADY_IN_UNIT = "patientAlreadyInUnit";

    public static ActionForward logonChecks(ActionMapping mapping, HttpServletRequest request, String defaultForward) {
        String resultForward = defaultForward;

        // access the "user principal" from spring rather than request
        String username = LegacySpringUtils.getSecurityUserManager().getLoggedInUsername();

        if (username != null) {
            User user = (User) HibernateUtil.getPersistentObject(User.class, username);

            if (user.isFirstlogon()) {
                if (user.getRole().equalsIgnoreCase("patient")) {
                    resultForward = "patientPasswordChangeInput";
                } else {
                    resultForward = "controlPasswordChangeInput";
                }
                request.setAttribute("firstLogon", "true");
            } else {

                SplashPage splashPage = activeSplashPage(user);

                if (null != splashPage) {
                    resultForward = "splashPage";
                    request.setAttribute("splashPage", splashPage);
                }
            }
        }

        recordLogon(request);
        return mapping.findForward(resultForward);
    }

    private static SplashPage activeSplashPage(User user) {
        SplashPage returnSplashPage = null;

        if (user.getRole().equalsIgnoreCase("patient")) {
            List splashpages = null;
            try {
                Session session = HibernateUtil.currentSession();
                Transaction tx = session.beginTransaction();
                splashpages = session.find("from " + SplashPage.class.getName());
                tx.commit();
                HibernateUtil.closeSession();
            } catch (HibernateException e) {
                e.printStackTrace();
            }

            if (!splashpages.isEmpty()) {

                SplashPage splashPage = (SplashPage) splashpages.get(0);


                if (null != splashPage &&
                        !splashPage.getName().equals(user.getSplashpage()) &&
                        splashPage.isLive()) {
                    userHasSeenThisSplashPage(splashPage, user);
                    return returnSplashPage = splashPage;
                }
            }
        }
        return returnSplashPage;
    }

    private static void userHasSeenThisSplashPage(SplashPage splashPage, User user) {
        user.setSplashpage(splashPage.getName());
        try {
            HibernateUtil.saveOrUpdateWithTransaction(user);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    private static void recordLogon(HttpServletRequest request) {

        // access the "user principal" from spring rather than request
        String username = LegacySpringUtils.getSecurityUserManager().getLoggedInUsername();

        HttpSession session = request.getSession();
        String logonRecorded = (String) session.getAttribute("logonRecorded");
        if (logonRecorded == null && username != null) {
            String unitCode = UserUtils.retrieveUsersRealUnitcodeBestGuess(username);
            String nhsno = UserUtils.retrieveUsersRealNhsnoBestGuess(username);
            AddLog.addLog(username, AddLog.LOGGED_ON, username, nhsno, unitCode, "");
            session.setAttribute("logonRecorded", "true");
        }
    }

    public static ActionForward logonChecks(ActionMapping mapping, HttpServletRequest request) {
        return logonChecks(mapping, request, "success");
    }

    public static String generateNewPassword() {
        return new RandPass(RandPass.NONCONFUSING_ALPHABET).getPass(8);
    }

    public static String displayRole(String role) {
        String displayRole = "";
        if ("unitadmin".equals(role)) {
            displayRole = "Unit Admin";
        } else if ("unitstaff".equals(role)) {
            displayRole = "Unit Staff";
        } else if ("patient".equals(role)) {
            displayRole = "Patient";
        } else if ("superadmin".equals(role)) {
            displayRole = "Super Admin";
        } else {
            displayRole = "Role Unknown";
        }
        return displayRole;
    }

    public static String hashPassword(String password) {
        return DigestUtils.sha256Hex(password);
    }
}
