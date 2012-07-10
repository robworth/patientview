package com.worthsoln.patientview.logon;

import com.worthsoln.HibernateUtil;
import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.logging.AddLog;
import com.worthsoln.patientview.User;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PatientAddToUnitAction extends DatabaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        String username = BeanUtils.getProperty(form, "username");
        String nhsno = BeanUtils.getProperty(form, "nhsno");
        String unitcode = BeanUtils.getProperty(form, "unitcode");

        UserMapping userMapping = new UserMapping(username, unitcode, nhsno);
        HibernateUtil.saveOrUpdateWithTransaction(userMapping);

        if (thereIsAGpUser(username)) {
            UserMapping userMappingGp = new UserMapping(username + "-GP", unitcode, nhsno);
            HibernateUtil.saveOrUpdateWithTransaction(userMappingGp);
        }

        AddLog.addLog(LegacySpringUtils.getSecurityUserManager().getLoggedInUsername(), AddLog.PATIENT_ADD, username,
                nhsno, unitcode, "");
        String mappingToFind = "success";

        request.setAttribute("userMapping", userMapping);
        return mapping.findForward(mappingToFind);
    }

    private boolean thereIsAGpUser(String username) {
        User user = (User) HibernateUtil.getPersistentObject(User.class, username + "-GP");
        return null != user;
    }


    public String getIdentifier() {
        return null;
    }

    public String getDatabaseName() {
        return "patientview";
    }
}