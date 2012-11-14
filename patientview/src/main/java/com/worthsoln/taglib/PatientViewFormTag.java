package com.worthsoln.taglib;

import com.worthsoln.specialty.SpecialtyUtils;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.FormTag;

import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class PatientViewFormTag extends FormTag {

    @Override
    /**
     * Renders the action attribute
     */
    protected void renderAction(StringBuffer results) {

        HttpServletResponse response =
                (HttpServletResponse) this.pageContext.getResponse();

        results.append(" action=\"");
        results.append(
                response.encodeURL(
                        SpecialtyUtils.rewriteSpecialtyUrlAddContext(TagUtils.getInstance().getActionMappingURL(
                                this.action,
                                this.pageContext))));

        results.append("\"");
    }
}
