package com.worthsoln.patientview.user;

import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.database.DatabaseUpdateQuery;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.utils.LegacySpringUtils;

import javax.servlet.http.HttpServletRequest;
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

        return LegacySpringUtils.getUserManager().get(username);
    }

    public static List<UserMapping> retrieveUserMappings(User user) {
        return LegacySpringUtils.getUserManager().getUserMappings(user.getUsername());
    }

    public static String retrieveUsersRealUnitcodeBestGuess(String username) {
        return LegacySpringUtils.getUserManager().getUsersRealUnitcodeBestGuess(username);
    }

    public static String retrieveUsersRealNhsnoBestGuess(String username) {
        return LegacySpringUtils.getUserManager().getUsersRealNhsNoBestGuess(username);
    }

    public static UserMapping retrieveUserMappingsPatientEntered(User user) {
        return LegacySpringUtils.getUserManager().getUserMappingPatientEntered(user);
    }

    // todo move this into hibernate manager
    public static void removePatientFromSystem(String nhsno, String unitcode) {
        String[] tableNames = new String[]{"testresult", "letter",};        //TODO add back user
        //String[] tableNames = new String[]{"user", "testresult", "letter",};        //TODO add medicines and diagnosis
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
                int digit = Integer.parseInt(nhsno.substring(i, i + 1));
                checksum += digit * (10 - i);
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
