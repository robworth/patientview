package com.worthsoln.patientview.splashpage;

import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.patientview.model.SplashPage;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SplashPageEditAction extends Action {

    public ActionForward execute(
            ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = BeanUtils.getProperty(form, "id");
        Long idLong = Long.decode(id);

        SplashPage splashpage = LegacySpringUtils.getSplashPageManager().get(idLong);

        request.setAttribute("splashPage", splashpage);

        SplashPageUtils.putSplashPageUnitsInRequest(request);

        return LogonUtils.logonChecks(mapping, request);
    }
}