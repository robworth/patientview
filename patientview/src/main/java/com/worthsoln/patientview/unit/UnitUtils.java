package com.worthsoln.patientview.unit;

import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.patientview.user.UserUtils;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;

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
            unit = LegacySpringUtils.getUnitManager().get(unitcode);
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

    public static Unit buildUnit(Object form) throws Exception {

        // build object
        Unit unit = new Unit();
        unit.setUnitcode(BeanUtils.getProperty(form, "unitcode"));
        unit.setName(BeanUtils.getProperty(form, "name"));
        unit.setShortname(BeanUtils.getProperty(form, "shortname"));
        unit.setUnituser(BeanUtils.getProperty(form, "unituser"));
        unit.setAddress1(BeanUtils.getProperty(form, "address1"));
        unit.setAddress2(BeanUtils.getProperty(form, "address2"));
        unit.setAddress3(BeanUtils.getProperty(form, "address3"));
        unit.setPostcode(BeanUtils.getProperty(form, "postcode"));
        unit.setUniturl(BeanUtils.getProperty(form, "uniturl"));
        unit.setTrusturl(BeanUtils.getProperty(form, "trusturl"));
        unit.setRpvadminname(BeanUtils.getProperty(form, "rpvadminname"));
        unit.setRpvadminphone(BeanUtils.getProperty(form, "rpvadminphone"));
        unit.setRpvadminemail(BeanUtils.getProperty(form, "rpvadminemail"));
        unit.setUnitenquiriesphone(BeanUtils.getProperty(form, "unitenquiriesphone"));
        unit.setUnitenquiriesemail(BeanUtils.getProperty(form, "unitenquiriesemail"));
        unit.setAppointmentphone(BeanUtils.getProperty(form, "appointmentphone"));
        unit.setAppointmentemail(BeanUtils.getProperty(form, "appointmentemail"));
        unit.setOutofhours(BeanUtils.getProperty(form, "outofhours"));
        unit.setPeritonealdialysisphone(BeanUtils.getProperty(form, "peritonealdialysisphone"));
        unit.setPeritonealdialysisemail(BeanUtils.getProperty(form, "peritonealdialysisemail"));

        unit.setHaemodialysisunitname1(BeanUtils.getProperty(form, "haemodialysisunitname1"));
        unit.setHaemodialysisunitphone1(BeanUtils.getProperty(form, "haemodialysisunitphone1"));
        unit.setHaemodialysisunitphone1(BeanUtils.getProperty(form, "haemodialysisunit1"));
        unit.setHaemodialysisuniturl1(BeanUtils.getProperty(form, "haemodialysisuniturl1"));

        unit.setHaemodialysisunitname2(BeanUtils.getProperty(form, "haemodialysisunitname2"));
        unit.setHaemodialysisunitphone2(BeanUtils.getProperty(form, "haemodialysisunitphone2"));
        unit.setHaemodialysisunitphone2(BeanUtils.getProperty(form, "haemodialysisunit2"));
        unit.setHaemodialysisuniturl2(BeanUtils.getProperty(form, "haemodialysisuniturl2"));

        unit.setHaemodialysisunitname3(BeanUtils.getProperty(form, "haemodialysisunitname3"));
        unit.setHaemodialysisunitphone3(BeanUtils.getProperty(form, "haemodialysisunitphone3"));
        unit.setHaemodialysisunitphone3(BeanUtils.getProperty(form, "haemodialysisunit3"));
        unit.setHaemodialysisuniturl3(BeanUtils.getProperty(form, "haemodialysisuniturl3"));

        unit.setHaemodialysisunitname4(BeanUtils.getProperty(form, "haemodialysisunitname4"));
        unit.setHaemodialysisunitphone4(BeanUtils.getProperty(form, "haemodialysisunitphone4"));
        unit.setHaemodialysisunitphone4(BeanUtils.getProperty(form, "haemodialysisunit4"));
        unit.setHaemodialysisuniturl4(BeanUtils.getProperty(form, "haemodialysisuniturl4"));

        unit.setHaemodialysisunitname5(BeanUtils.getProperty(form, "haemodialysisunitname5"));
        unit.setHaemodialysisunitphone5(BeanUtils.getProperty(form, "haemodialysisunitphone5"));
        unit.setHaemodialysisunitphone5(BeanUtils.getProperty(form, "haemodialysisunit5"));
        unit.setHaemodialysisuniturl5(BeanUtils.getProperty(form, "haemodialysisuniturl5"));

        unit.setHaemodialysisunitname6(BeanUtils.getProperty(form, "haemodialysisunitname6"));
        unit.setHaemodialysisunitphone6(BeanUtils.getProperty(form, "haemodialysisunitphone6"));
        unit.setHaemodialysisunitphone6(BeanUtils.getProperty(form, "haemodialysisunit6"));
        unit.setHaemodialysisuniturl6(BeanUtils.getProperty(form, "haemodialysisuniturl6"));

        unit.setHaemodialysisunitname7(BeanUtils.getProperty(form, "haemodialysisunitname7"));
        unit.setHaemodialysisunitphone7(BeanUtils.getProperty(form, "haemodialysisunitphone7"));
        unit.setHaemodialysisunitphone7(BeanUtils.getProperty(form, "haemodialysisunit7"));
        unit.setHaemodialysisuniturl7(BeanUtils.getProperty(form, "haemodialysisuniturl7"));

        unit.setHaemodialysisunitname8(BeanUtils.getProperty(form, "haemodialysisunitname8"));
        unit.setHaemodialysisunitphone8(BeanUtils.getProperty(form, "haemodialysisunitphone8"));
        unit.setHaemodialysisunitphone8(BeanUtils.getProperty(form, "haemodialysisunit8"));
        unit.setHaemodialysisuniturl8(BeanUtils.getProperty(form, "haemodialysisuniturl8"));

        unit.setHaemodialysisunitname9(BeanUtils.getProperty(form, "haemodialysisunitname9"));
        unit.setHaemodialysisunitphone9(BeanUtils.getProperty(form, "haemodialysisunitphone9"));
        unit.setHaemodialysisunitphone9(BeanUtils.getProperty(form, "haemodialysisunit9"));
        unit.setHaemodialysisuniturl9(BeanUtils.getProperty(form, "haemodialysisuniturl9"));

        unit.setHaemodialysisunitname10(BeanUtils.getProperty(form, "haemodialysisunitname10"));
        unit.setHaemodialysisunitphone10(BeanUtils.getProperty(form, "haemodialysisunitphone10"));
        unit.setHaemodialysisunitphone10(BeanUtils.getProperty(form, "haemodialysisunit10"));
        unit.setHaemodialysisuniturl10(BeanUtils.getProperty(form, "haemodialysisuniturl10"));

        unit.setHaemodialysisunitname11(BeanUtils.getProperty(form, "haemodialysisunitname11"));
        unit.setHaemodialysisunitphone11(BeanUtils.getProperty(form, "haemodialysisunitphone11"));
        unit.setHaemodialysisunitphone11(BeanUtils.getProperty(form, "haemodialysisunit11"));
        unit.setHaemodialysisuniturl11(BeanUtils.getProperty(form, "haemodialysisuniturl11"));

        unit.setHaemodialysisunitname12(BeanUtils.getProperty(form, "haemodialysisunitname12"));
        unit.setHaemodialysisunitphone12(BeanUtils.getProperty(form, "haemodialysisunitphone12"));
        unit.setHaemodialysisunitphone12(BeanUtils.getProperty(form, "haemodialysisunit12"));
        unit.setHaemodialysisuniturl12(BeanUtils.getProperty(form, "haemodialysisuniturl12"));

        return unit;
    }
}