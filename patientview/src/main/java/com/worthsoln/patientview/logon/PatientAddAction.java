package com.worthsoln.patientview.logon;

import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.logging.AddLog;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.patientview.unit.UnitUtils;
import com.worthsoln.patientview.user.EmailVerificationUtils;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PatientAddAction extends DatabaseAction {

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
        boolean dummypatient = "true".equals(BeanUtils.getProperty(form, "dummypatient"));
        PatientLogon patient =
                new PatientLogon(username, password, name, email, false, true, dummypatient, null, 0, false, "");
        UserMapping userMapping = new UserMapping(username, unitcode, nhsno);
        UserMapping userMappingPatientEnters = new UserMapping(username, UnitUtils.PATIENT_ENTERS_UNITCODE, nhsno);
        PatientLogon gp =
                new PatientLogon(username + "-GP", gppassword, name + "-GP", null, false, true, dummypatient,
                        null, 0, false, "");
        UserMapping userMappingGp = new UserMapping(username + "-GP", unitcode, nhsno);
        DatabaseDAO dao = getDao(request);
        PatientLogon existingPatientwithSameUsername = (PatientLogon) dao.retrieveItem(new PatientLogonDao(patient));

        List existingPatientsWithSameNhsno = findExistingPatientsWithSameNhsno(nhsno);

        String mappingToFind = "";
        if (existingPatientwithSameUsername != null) {
            request.setAttribute(LogonUtils.USER_ALREADY_EXISTS, username);
            patient.setUsername("");
            mappingToFind = "input";
        }
        if (existingPatientsWithSameNhsno != null && !existingPatientsWithSameNhsno.isEmpty() && !overrideDuplicateNhsno.equals("on")) {
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
            PatientLogon hashedPatient = (PatientLogon) patient.clone();
            PatientLogon hashedGp = (PatientLogon) gp.clone();

            hashedPatient.setPassword(LogonUtils.hashPassword(hashedPatient.getPassword()));
            hashedGp.setPassword(LogonUtils.hashPassword(hashedGp.getPassword()));

            dao.insertItem(new LogonDao(hashedPatient));
            dao.insertItem(new LogonDao(hashedGp));

            LegacySpringUtils.getUserManager().save(userMapping);
            LegacySpringUtils.getUserManager().save(userMappingPatientEnters);
            LegacySpringUtils.getUserManager().save(userMappingGp);

            AddLog.addLog(LegacySpringUtils.getSecurityUserManager().getLoggedInUsername(), AddLog.PATIENT_ADD,
                    patient.getUsername(),
                    userMapping.getNhsno(), userMapping.getUnitcode(), "");
            EmailVerificationUtils.createEmailVerification(patient.getUsername(), patient.getEmail(), request);
            mappingToFind = "success";
        }

        List<Unit> units = LegacySpringUtils.getUnitManager().getAll(false);
        request.setAttribute("units", units);
        request.setAttribute("patient", patient);
        request.setAttribute("userMapping", userMapping);
        request.getSession().setAttribute("gp", gp);
        request.getSession().setAttribute("userMappingGp", userMappingGp);
        return mapping.findForward(mappingToFind);
    }

    private List findExistingPatientsWithSameNhsno(String nhsno) {
        return LegacySpringUtils.getUserManager().getUserMappingsForNhsNo(nhsno);
    }

    public String getIdentifier() {
        return null;
    }

    public String getDatabaseName() {
        return "patientview";
    }
}
