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
import java.util.List;

public class SplashPageUpdateAction extends Action {

    public ActionForward execute(
            ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        SplashPage splashPage;
        String idStr = BeanUtils.getProperty(form, "id");
        if (idStr != null && !idStr.equals("")) {
            splashPage = LegacySpringUtils.getSplashPageManager().get(Long.decode(idStr));
        } else {
            splashPage = new SplashPage();
        }

        BeanUtils.setProperty(splashPage, "name", BeanUtils.getProperty(form, "name"));
        BeanUtils.setProperty(splashPage, "headline", BeanUtils.getProperty(form, "headline"));
        BeanUtils.setProperty(splashPage, "bodytext", BeanUtils.getProperty(form, "bodytext"));
        BeanUtils.setProperty(splashPage, "unitcode", BeanUtils.getProperty(form, "unitcode"));
        String stringLive = BeanUtils.getProperty(form, "live");
        boolean isLive = "true".equals(stringLive);
        splashPage.setLive(isLive);

        LegacySpringUtils.getSplashPageManager().save(splashPage);

        request.setAttribute("splashPage", splashPage);

        List<SplashPage> splashpages = LegacySpringUtils.getSplashPageManager().getAll();
        request.setAttribute("splashpages", splashpages);

        return LogonUtils.logonChecks(mapping, request);
    }

}