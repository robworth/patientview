package com.worthsoln.taglib;

import javax.servlet.jsp.JspException;

/**
 * Override to evaluate user the patient view user and role rather than the tomcat/request/container
 */
public class PatientViewNotPresentTag extends PatientViewPresentTag {

    protected boolean condition() throws JspException {

        return (condition(false));

    }
}
