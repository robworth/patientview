package com.worthsoln.taglib;

import com.worthsoln.patientview.model.Tenancy;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.taglib.html.LinkTag;

import javax.servlet.jsp.JspException;

/**
 *  Extend the Struts link tag to apply tenancy contexts to links
 */
public class PatientViewLinkTag extends LinkTag {

    @Override
    protected String calculateURL() throws JspException {
        String result = super.calculateURL();

        Tenancy tenancy = LegacySpringUtils.getSecurityUserManager().getLoggedInTenancy();

        String context = tenancy != null ? "/" + tenancy.getContext() : "";

        return context + result;
    }
}
