package com.worthsoln.patientview.news;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.worthsoln.patientview.model.News;
import com.worthsoln.utils.LegacySpringUtils;

public class NewsUtils {

    public static void putAppropriateNewsForViewingInRequest(HttpServletRequest request) throws Exception {

        List<News> items = LegacySpringUtils.getNewsManager().getNewsForViewing();
        request.setAttribute("newses", items);
    }

    public static void putAppropriateNewsForEditInRequest(HttpServletRequest request) throws Exception {

        List<News> items = LegacySpringUtils.getNewsManager().getNewsForEditing();
        request.setAttribute("newses", items);
    }
}
