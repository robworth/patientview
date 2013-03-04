package com.worthsoln.patientview.news;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.patientview.model.News;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.patientview.unit.UnitUtils;

public class NewsEditAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        String id = BeanUtils.getProperty(form, "id");
        Long idLong = Long.decode(id);
        News newsItem = LegacySpringUtils.getNewsManager().get(idLong);
        request.setAttribute("news", newsItem);
        UnitUtils.putRelevantUnitsInRequest(request);
        
        return mapping.findForward("success");
    }

}
