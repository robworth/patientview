package com.worthsoln.patientview.news;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worthsoln.patientview.model.News;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.worthsoln.database.action.DatabaseAction;

public class NewsPreviewAction extends DatabaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        ActionForward actionForward;
        String unitcode = BeanUtils.getProperty(form, "unitcode");
        boolean patient = "true".equals(BeanUtils.getProperty(form, "patient"));
        boolean admin = "true".equals(BeanUtils.getProperty(form, "admin"));
        boolean everyone = "true".equals(BeanUtils.getProperty(form, "everyone"));
        String headline = BeanUtils.getProperty(form, "headline");
        String body = BeanUtils.getProperty(form, "body");
        News news = new News(unitcode, admin, patient, everyone, headline, body);
        String id = BeanUtils.getProperty(form, "id");
        if (!"".equals(id)) {
            news.setId(Long.decode(id));
        }
        if ("Preview".equals(BeanUtils.getProperty(form, "submission"))) {
            request.setAttribute("news", news);
            actionForward = mapping.findForward("preview");
        } else {
            LegacySpringUtils.getNewsManager().save(news);
            NewsUtils.putAppropriateNewsForEditInRequest(request);
            actionForward = mapping.findForward("add");
        }
        return actionForward;
    }

    public String getDatabaseName() {
        return "patientview";
    }

    public String getIdentifier() {
        return "news";
    }
}