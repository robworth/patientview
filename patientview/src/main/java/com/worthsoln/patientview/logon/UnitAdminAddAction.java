package com.worthsoln.patientview.logon;

import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.logging.AddLog;
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

public class UnitAdminAddAction extends DatabaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        String username = BeanUtils.getProperty(form, "username");
        String password = LogonUtils.generateNewPassword();
        String name = BeanUtils.getProperty(form, "name");
        String email = BeanUtils.getProperty(form, "email");
        String unitcode = BeanUtils.getProperty(form, "unitcode");
        String role = BeanUtils.getProperty(form, "role");
        UnitAdmin unitAdmin = new UnitAdmin(username, password, name, email, false, role, true);
        DatabaseDAO dao = getDao(request);

        List<UserMapping> usermappingList = LegacySpringUtils.getUserManager().getUserMappings(username);

        String mappingToFind;
        if (!usermappingList.isEmpty()) {

            // Note: legacy code assumes that there is a unique results here
            List<UserMapping> userMappings = LegacySpringUtils.getUserManager().getUserMappings(username, unitcode);
            UserMapping userMapping = null;
            if (userMappings != null && userMappings.size() > 0) {
                userMapping = userMappings.get(0);
            }

            if (userMapping != null) {
                request.setAttribute(LogonUtils.USER_ALREADY_EXISTS, username);
                unitAdmin.setUsername("");
                UnitUtils.putRelevantUnitsInRequest(request);
                mappingToFind = "input";
            } else {
                UserMapping userMappingNew = new UserMapping(username, unitcode, "");
                request.setAttribute("usermapping", userMappingNew);
                mappingToFind = "existinguser";
            }
        } else {
            UnitAdmin hashedUnitAdmin = (UnitAdmin) unitAdmin.clone();
            hashedUnitAdmin.setPassword(LogonUtils.hashPassword(hashedUnitAdmin.getPassword()));
            dao.insertItem(new LogonDao(hashedUnitAdmin));

            UserMapping userMapping = new UserMapping(username, unitcode, "");
            LegacySpringUtils.getUserManager().save(userMapping);

            AddLog.addLog(LegacySpringUtils.getSecurityUserManager().getLoggedInUsername(), AddLog.ADMIN_ADD,
                    unitAdmin.getUsername(), "",
                    unitcode, "");
            EmailVerificationUtils.createEmailVerification(hashedUnitAdmin.getUsername(), hashedUnitAdmin.getEmail(), request);
            mappingToFind = "success";
        }
        request.setAttribute("adminuser", unitAdmin);
        return mapping.findForward(mappingToFind);
    }

    public String getIdentifier() {
        return null;
    }

    public String getDatabaseName() {
        return "patientview";
    }
}
