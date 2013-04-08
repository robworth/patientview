package com.worthsoln.patientview.logon;

import com.worthsoln.patientview.logging.AddLog;
import com.worthsoln.patientview.model.Patient;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.patientview.unit.UnitUtils;
import com.worthsoln.patientview.user.UserUtils;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PatientAddAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        String username = BeanUtils.getProperty(form, "username");
        String password = LogonUtils.generateNewPassword();
        String gppassword = LogonUtils.generateNewPassword();
        String name = BeanUtils.getProperty(form, "name");
        String email = BeanUtils.getProperty(form, "email");
        String nhsno = BeanUtils.getProperty(form, "nhsno").trim();
        String unitcode = BeanUtils.getProperty(form, "unitcode");
        String overrideDuplicateNhsno = BeanUtils.getProperty(form, "overrideDuplicateNhsno");
        String overrideInvalidNhsno = BeanUtils.getProperty(form, "overrideInvalidNhsno");
        boolean dummypatient = "true".equals(BeanUtils.getProperty(form, "dummypatient"));
        
        PatientLogon patientLogon =
                new PatientLogon(username, password, name, email, false, true, dummypatient, null, 0, false, "");
        
        UserMapping userMapping = new UserMapping(username, unitcode, nhsno);
        UserMapping userMappingPatientEnters = new UserMapping(username, UnitUtils.PATIENT_ENTERS_UNITCODE, nhsno);
        
        PatientLogon gpPatientLogon =
                new PatientLogon(username + "-GP", gppassword, name + "-GP", null, false, true, dummypatient,
                        null, 0, false, "");

        UserMapping userMappingGp = new UserMapping(username + "-GP", unitcode, nhsno);

        User existingUser =  LegacySpringUtils.getUserManager().get(username);

        List existingPatientsWithSameNhsno = findExistingPatientsWithSameNhsno(nhsno);

        String mappingToFind = "";
        if (!"on".equals(overrideInvalidNhsno) && !UserUtils.nhsNumberChecksumValid(nhsno)) {
            request.setAttribute(LogonUtils.INVALID_NHSNO, nhsno);
            mappingToFind = "input";
        }

        if (existingUser != null) {
            request.setAttribute(LogonUtils.USER_ALREADY_EXISTS, username);
            patientLogon.setUsername("");
            mappingToFind = "input";
        }

        if (existingPatientsWithSameNhsno != null && !existingPatientsWithSameNhsno.isEmpty() &&
                !overrideDuplicateNhsno.equals("on")) {
            for (Object obj : existingPatientsWithSameNhsno) {
                UserMapping userMappingWithSameNhsno = (UserMapping) obj;
                if (userMappingWithSameNhsno.getUnitcode().equalsIgnoreCase(unitcode)) {
                    request.setAttribute(LogonUtils.PATIENT_ALREADY_IN_UNIT, nhsno);
                    mappingToFind = "input";
                }
            }
            if ("".equals(mappingToFind)) {
                request.setAttribute(LogonUtils.NHSNO_ALREADY_EXISTS, nhsno);
                request.setAttribute(LogonUtils.PATIENTS_WITH_SAME_NHSNO, existingPatientsWithSameNhsno.get(0));
                mappingToFind = "samenhsno";
            }
        }

        if (mappingToFind.equals("")) {
            PatientLogon hashedPatient = (PatientLogon) patientLogon.clone();
            PatientLogon hashedGp = (PatientLogon) gpPatientLogon.clone();

            hashedPatient.setPassword(LogonUtils.hashPassword(hashedPatient.getPassword()));
            hashedGp.setPassword(LogonUtils.hashPassword(hashedGp.getPassword()));

            LegacySpringUtils.getUserManager().saveUserFromPatient(hashedPatient);
            LegacySpringUtils.getUserManager().saveUserFromPatient(hashedGp);

            LegacySpringUtils.getUserManager().save(userMapping);
            LegacySpringUtils.getUserManager().save(userMappingPatientEnters);
            LegacySpringUtils.getUserManager().save(userMappingGp);

            AddLog.addLog(LegacySpringUtils.getSecurityUserManager().getLoggedInUsername(), AddLog.PATIENT_ADD,
                    patientLogon.getUsername(),
                    userMapping.getNhsno(), userMapping.getUnitcode(), "");
            mappingToFind = "success";
        }

        List<Unit> units = LegacySpringUtils.getUnitManager().getAll(false);

        request.setAttribute("units", units);
        request.setAttribute("patient", patientLogon);
        request.setAttribute("userMapping", userMapping);
        request.getSession().setAttribute("gp", gpPatientLogon);
        request.getSession().setAttribute("userMappingGp", userMappingGp);

        return mapping.findForward(mappingToFind);
    }

    private List findExistingPatientsWithSameNhsno(String nhsno) {
        return LegacySpringUtils.getUserManager().getUserMappingsForNhsNo(nhsno);
    }

}
