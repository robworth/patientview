package com.worthsoln.patientview.unit;

import com.worthsoln.HibernateUtil;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.logon.UserMapping;
import com.worthsoln.patientview.user.UserUtils;
import com.worthsoln.utils.LegacySpringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UnitUtils {

    public static String PATIENT_ENTERS_UNITCODE = "PATIENT";

    public static void putRelevantUnitsInRequest(HttpServletRequest request) throws Exception {
        putRelevantUnitsInRequestMinusSomeUnits(request, new String[]{PATIENT_ENTERS_UNITCODE}, new String[]{});
    }

    public static void putRelevantUnitsInRequestMinusSomeUnits(HttpServletRequest request, String[] notTheseUnitCodes,
                                                               String[] plusTheseUnitCodes) throws Exception {
        List items = LegacySpringUtils.getUnitManager().getLoggedInUsersUnits(notTheseUnitCodes, plusTheseUnitCodes);
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
        return LegacySpringUtils.getUnitManager().getUsersUnitCodes();
    }

    public static String retrieveUnitcode(HttpServletRequest request) {
        User user = UserUtils.retrieveUser(request);

        UserMapping userMapping = UserUtils.retrieveUserMappingsPatientEntered(user);

        return userMapping.getUnitcode();
    }
}