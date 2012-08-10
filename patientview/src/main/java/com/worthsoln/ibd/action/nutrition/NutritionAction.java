package com.worthsoln.ibd.action.nutrition;


import com.worthsoln.ibd.Ibd;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.ibd.model.Nutrition;
import com.worthsoln.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class NutritionAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        List<Nutrition> nutritionList = getIbdManager().getAllNutritions(UserUtils.retrieveUser(request));

        if (nutritionList != null) {
            request.setAttribute(Ibd.NUTRITION_LIST, nutritionList);
        }

        return mapping.findForward(SUCCESS);
    }

}
