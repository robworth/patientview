package com.worthsoln.taglib;

import com.worthsoln.specialty.SpecialtyUtils;
import org.apache.struts.taglib.html.LinkTag;

import javax.servlet.jsp.JspException;

/**
 *  Extend the Struts link tag to apply Specialty contexts to links
 */
public class PatientViewLinkTag extends LinkTag {

    @Override
    protected String calculateURL() throws JspException {
        String result = super.calculateURL();

        return SpecialtyUtils.rewriteSpecialtyUrlAddContext(result);
    }
}
