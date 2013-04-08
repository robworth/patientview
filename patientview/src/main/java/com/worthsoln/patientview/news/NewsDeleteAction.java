package com.worthsoln.patientview.news;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class NewsDeleteAction extends Action {

    public ActionForward execute(
            ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String id = BeanUtils.getProperty(form, "id");
        Long idLong = Long.decode(id);
        LegacySpringUtils.getNewsManager().delete(idLong);

        NewsUtils.putAppropriateNewsForEditInRequest(request);
        return mapping.findForward("success");
    }

}
