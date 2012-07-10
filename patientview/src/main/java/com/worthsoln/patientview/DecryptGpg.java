package com.worthsoln.patientview;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.plmatrix.gpg.GpgToolFree;
import com.worthsoln.patientview.logon.LogonUtils;

public class DecryptGpg extends Action {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String src = BeanUtils.getProperty(form, "src");
        String dest = BeanUtils.getProperty(form, "dest");
        String passphrase = BeanUtils.getProperty(form, "passphrase");
        String gpgHome = BeanUtils.getProperty(form, "gpgHome");
        String gpgRuntime = BeanUtils.getProperty(form, "gpgRuntime");
        GpgToolFree gpg = new GpgToolFree();
        try {
            gpg.setGpgHome(gpgHome);
            gpg.setGpgRuntime(gpgRuntime);
            gpg.decrypt(src, dest, passphrase);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LogonUtils.logonChecks(mapping, request);
    }
}
