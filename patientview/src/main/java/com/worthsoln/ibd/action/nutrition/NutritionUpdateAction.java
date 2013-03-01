package com.worthsoln.ibd.action.nutrition;

import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.ibd.Ibd;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.ibd.model.Nutrition;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NutritionUpdateAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        // set current nav
        ActionUtils.setUpNavLink(mapping.getParameter(), request);

        DynaActionForm dynaForm = (DynaActionForm) form;

        if (!validate(dynaForm, request)) {
            return mapping.findForward(INPUT);
        }

        Nutrition nutrition = new Nutrition();
        nutrition.setNhsno(getNhsNoForUser(request));
        nutrition.setFoodsThatDisagree((String) dynaForm.get(Ibd.FOODS_THAT_DISAGREE_PARAM));
        nutrition.setComment((String) dynaForm.get(Ibd.COMMENT_PARAM));
        nutrition.setNutritionDate(convertFormDateString(Ibd.NUTRITION_DATE_PARAM, dynaForm));

        getIbdManager().saveNutrition(nutrition);

        return mapping.findForward(SUCCESS);
    }

    private boolean validate(DynaActionForm form, HttpServletRequest request) {
        ActionMessages actionErrors = new ActionMessages();

        // need a usermapping with nhs no before we can save so check we have this
        if (getNhsNoForUser(request) == null) {
            actionErrors.add(Ibd.NHS_NO_PARAM, new ActionMessage(Ibd.NHS_NO_NOT_FOUND));
        }

        if (form.get(Ibd.NUTRITION_DATE_PARAM) == null ||
                ((String) form.get(Ibd.NUTRITION_DATE_PARAM)).length() == 0) {
            actionErrors.add(Ibd.NUTRITION_DATE_PARAM, new ActionMessage(Ibd.DATE_REQUIRED));
        }

        if (form.get(Ibd.FOODS_THAT_DISAGREE_PARAM) == null ||
                ((String) form.get(Ibd.FOODS_THAT_DISAGREE_PARAM)).length() == 0) {
            actionErrors.add(Ibd.FOODS_THAT_DISAGREE_PARAM, new ActionMessage(Ibd.FOODS_THAT_DISAGREE_REQUIRED));
        }

        if (actionErrors.size() > 0) {
            saveErrors(request, actionErrors);
            return false;
        }

        return true;
    }
}
