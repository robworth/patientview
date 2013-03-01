package com.worthsoln.patientview.logon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.logging.AddLog;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.user.UserUtils;

import java.util.List;

public class PasswordUnlockAction extends DatabaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        String username = BeanUtils.getProperty(form, "username");
        PatientLogon patient = new PatientLogon(username);
        DatabaseDAO dao = getDao(request);
        patient = (PatientLogon) dao.retrieveItem(new PatientLogonDao(patient));
        String mappingToFind = "";
        if (patient != null) {
            patient.setFailedlogons(0);
            patient.setAccountlocked(false);
            dao.updateItem(new LogonDao(patient));
            AddLog.addLog(LegacySpringUtils.getSecurityUserManager().getLoggedInUsername(), AddLog.PASSWORD_UNLOCKED,
                    patient.getUsername(), "",
                    UserUtils.retrieveUsersRealUnitcodeBestGuess(username), "");
            mappingToFind = "success";
        }
        List<Unit> units = LegacySpringUtils.getUnitManager().getAll(false);
        request.setAttribute("units", units);
        request.setAttribute("patient", patient);
        return mapping.findForward(mappingToFind);
    }

    public String getIdentifier() {
        return null;
    }

    public String getDatabaseName() {
        return "patientview";
    }
}