package com.worthsoln.patientview.logon;

import com.Ostermiller.util.RandPass;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.logging.AddLog;
import com.worthsoln.patientview.model.SplashPage;
import com.worthsoln.patientview.model.SplashPageUserSeen;
import com.worthsoln.patientview.splashpage.SplashPageUtils;
import com.worthsoln.patientview.user.UserUtils;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
            User user = LegacySpringUtils.getUserManager().get(username);

            if (user.isFirstlogon()) {
                if (user.getRole().equalsIgnoreCase("patient")) {
                    resultForward = "patientPasswordChangeInput";
                } else {
                    resultForward = "controlPasswordChangeInput";
                }
                request.setAttribute("firstLogon", "true");
            } else {
                HttpSession session = request.getSession();
                Object splashPageViewedThisSession = session.getAttribute("splashPageViewed");

                if (splashPageViewedThisSession == null) {
                    SplashPage splashPage = activeSplashPage(user);

                    if (null != splashPage) {
                        resultForward = "loginSuccess";
                        request.setAttribute("splashPage", splashPage);
                        session.setAttribute("splashPageViewed", "splashPageViewed");
                    }
                }
            }
        }

        recordLogon(request);
        return mapping.findForward(resultForward);
    }

    private static SplashPage activeSplashPage(User user) {
        SplashPage returnSplashPage = null;

        if (user.getRole().equalsIgnoreCase("patient")) {
            List<SplashPage> splashpages = SplashPageUtils.retrieveSplashPagesForPatient(user);
            List<SplashPageUserSeen> splashPagesUserHasSeen = SplashPageUtils.retrieveSplashPagesPatientHasSeen(user);

            for (SplashPage splashPage : splashpages) {
                boolean userHasSeenThisSplashPage = false;

                for (SplashPageUserSeen splashPageUserSeen : splashPagesUserHasSeen) {
                    if (splashPage.getId() == splashPageUserSeen.getSplashpageid()) {
                        userHasSeenThisSplashPage = true;
                        break;
                    }
                }

                if (!userHasSeenThisSplashPage) {
                    returnSplashPage = splashPage;
                    markSplashPageAsSeenByUser(returnSplashPage, user);
                    break;
                }
            }
        }

        return returnSplashPage;
    }

    private static void markSplashPageAsSeenByUser(SplashPage splashPage, User user) {
        SplashPageUserSeen splashPageUserSeen = new SplashPageUserSeen(user.getUsername(), splashPage.getId());

        LegacySpringUtils.getSplashPageManager().save(splashPageUserSeen);
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
