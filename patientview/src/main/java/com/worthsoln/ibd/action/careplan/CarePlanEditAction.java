package com.worthsoln.ibd.action.careplan;

import com.worthsoln.ibd.Ibd;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.ibd.model.CarePlan;
import com.worthsoln.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CarePlanEditAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {
        CarePlan carePlan = getIbdManager().getCarePlan(UserUtils.retrieveUser(request));

        if (carePlan == null) {
            carePlan = new CarePlan();
        }

        DynaActionForm dynaForm = (DynaActionForm) form;
        dynaForm.set(Ibd.FURTHER_TOPICS_PARAM, carePlan.getFurtherTopics());
        dynaForm.set(Ibd.GOALS_PARAM, carePlan.getGoals());
        dynaForm.set(Ibd.GOAL_TO_ACHIEVE_PARAM, carePlan.getGoalToAchieve());
        dynaForm.set(Ibd.GOAL_SCORE_PARAM, carePlan.getGoalScale());
        dynaForm.set(Ibd.HOW_TO_ACHIEVE_GOAL_PARAM, carePlan.getHowToAchieveGoal());
        dynaForm.set(Ibd.BARRIERS_PARAM, carePlan.getBarriers());
        dynaForm.set(Ibd.WHAT_CAN_BE_DONE_PARAM, carePlan.getWhatCanBeDone());
        dynaForm.set(Ibd.CONFIDENCE_SCALE_PARAM, carePlan.getConfidenceScale());

        String reviewDate = "";

        if (carePlan.getReviewDate() != null) {
            reviewDate = Ibd.DATE_FORMAT.format(carePlan.getReviewDate());
        }

        dynaForm.set(Ibd.REVIEW_DATE_PARAM, reviewDate);

        Long[] areaToDiscussIds = new Long[carePlan.getAreaToDiscussIds().size()];
        int count = 0;

        for (Long l : carePlan.getAreaToDiscussIds()) {
            areaToDiscussIds[count] = l;
            count++;
        }

        dynaForm.set(Ibd.AREA_TO_DISCUSS_IDS_PARAM, areaToDiscussIds);

        request.getSession().setAttribute(AREA_TO_DISCUSS_LIST_PROPERTY, getAreaToDiscussList());
        request.getSession().setAttribute(SCALE_LIST_PROPERTY, getScaleList());

        return mapping.findForward(SUCCESS);
    }
}
