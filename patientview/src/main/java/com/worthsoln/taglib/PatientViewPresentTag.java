package com.worthsoln.taglib;

import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.logic.PresentTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import java.util.StringTokenizer;

/**
 *      Override to evaluate user the patient view user and role rather than the tomcat/request/container
 */
public class PatientViewPresentTag extends PresentTag {

    private String tenancy;

    @Override
    protected boolean condition(boolean desired) throws JspException {

        // Evaluate the presence of the specified value
        boolean present = false;
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

        if (cookie != null) {
            present = this.isCookiePresent(request);

        } else if (header != null) {
            String value = request.getHeader(header);
            present = (value != null);

        } else if (name != null) {
            present = this.isBeanPresent();

        } else if (parameter != null) {
            String value = request.getParameter(parameter);
            present = (value != null);

        } else if (role != null) {
            StringTokenizer st = new StringTokenizer(role, ROLE_DELIMITER, false);
            while (!present && st.hasMoreTokens()) {
                present = LegacySpringUtils.getSecurityUserManager().isRolePresent(st.nextToken());
            }

        } else if (user != null) {
            String username = LegacySpringUtils.getSecurityUserManager().getLoggedInUsername();
            present = (username != null) && user.equals(username);

        } else if (tenancy != null) {
            present = LegacySpringUtils.getSecurityUserManager().isTenancyPresent(tenancy);
        } else {
            JspException e = new JspException
                    (messages.getMessage("logic.selector"));
            TagUtils.getInstance().saveException(pageContext, e);
            throw e;
        }

        return (present == desired);
    }

    @Override
    public void release() {
        super.release();

        tenancy = null;
    }

    public String getTenancy() {
        return tenancy;
    }

    public void setTenancy(String tenancy) {
        this.tenancy = tenancy;
    }
}
