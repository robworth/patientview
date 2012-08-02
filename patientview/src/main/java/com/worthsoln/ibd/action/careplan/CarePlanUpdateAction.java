package com.worthsoln.ibd.action.careplan;

import com.worthsoln.ibd.Ibd;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.ibd.model.CarePlan;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;

public class CarePlanUpdateAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {
        DynaActionForm dynaForm = (DynaActionForm) form;

        if (!validate(dynaForm, request)) {
            return mapping.findForward(INPUT);
        }

        User user = UserUtils.retrieveUser(request);

        CarePlan carePlan = getIbdManager().getCarePlan(user);

        // if its null then its new so set the nhs no to the user
        if (carePlan == null || !carePlan.hasValidId()) {
            carePlan = new CarePlan();
            carePlan.setNhsno(getNhsNoForUser(request));
        }

        carePlan.setFurtherTopics((String) dynaForm.get(Ibd.FURTHER_TOPICS_PARAM));
        carePlan.setGoals((String) dynaForm.get(Ibd.GOALS_PARAM));
        carePlan.setGoalToAchieve((String) dynaForm.get(Ibd.GOAL_TO_ACHIEVE_PARAM));
        carePlan.setGoalScale((Integer) dynaForm.get(Ibd.GOAL_SCORE_PARAM));
        carePlan.setHowToAchieveGoal((String) dynaForm.get(Ibd.HOW_TO_ACHIEVE_GOAL_PARAM));
        carePlan.setBarriers((String) dynaForm.get(Ibd.BARRIERS_PARAM));
        carePlan.setWhatCanBeDone((String) dynaForm.get(Ibd.WHAT_CAN_BE_DONE_PARAM));
        carePlan.setConfidenceScale((Integer) dynaForm.get(Ibd.CONFIDENCE_SCALE_PARAM));

        Long[] areaToDiscussIds = (Long[]) dynaForm.get(Ibd.AREA_TO_DISCUSS_IDS_PARAM);
        carePlan.setAreaToDiscussIds(new HashSet<Long>(Arrays.asList(areaToDiscussIds)));

        getIbdManager().saveCarePlan(carePlan);

        return mapping.findForward(SUCCESS);
    }

    private boolean validate(DynaActionForm form, HttpServletRequest request) {
        ActionMessages actionErrors = new ActionMessages();

        if (actionErrors.size() > 0) {
            saveErrors(request, actionErrors);
            return false;
        }

        return true;
    }
}
