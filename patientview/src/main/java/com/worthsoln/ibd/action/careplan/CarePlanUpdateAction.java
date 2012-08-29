package com.worthsoln.ibd.action.careplan;

import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.ibd.Ibd;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.ibd.model.CarePlan;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;

public class CarePlanUpdateAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {
        // set current nav
        ActionUtils.setUpNavLink(mapping.getParameter(), request);

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
        carePlan.setReviewDate(convertFormDateString(Ibd.REVIEW_DATE_PARAM, dynaForm));

        Long[] areaToDiscussIds = (Long[]) dynaForm.get(Ibd.AREA_TO_DISCUSS_IDS_PARAM);
        carePlan.setAreaToDiscussIds(new HashSet<Long>(Arrays.asList(areaToDiscussIds)));

        getIbdManager().saveCarePlan(carePlan);

        return mapping.findForward(SUCCESS);
    }

    private boolean validate(DynaActionForm form, HttpServletRequest request) {
        ActionMessages actionErrors = new ActionMessages();

        // need a usermapping with nhs no before we can save so check we have this
        if (getNhsNoForUser(request) == null) {
            actionErrors.add(Ibd.NHS_NO_PARAM, new ActionMessage(Ibd.NHS_NO_NOT_FOUND));
        }

        // Comment out the ones that do not require validation

        if (form.get(Ibd.AREA_TO_DISCUSS_IDS_PARAM) == null
                || ((Long[]) form.get(Ibd.AREA_TO_DISCUSS_IDS_PARAM)).length == 0) {
            actionErrors.add(Ibd.AREA_TO_DISCUSS_IDS_PARAM, new ActionMessage(Ibd.AREAS_TO_DISCUSS_REQUIRED));
        }

        if (form.get(Ibd.GOALS_PARAM) == null || ((String) form.get(Ibd.GOALS_PARAM)).length() == 0) {
            actionErrors.add(Ibd.GOALS_PARAM, new ActionMessage(Ibd.GOALS_REQUIRED));
        }

        if (form.get(Ibd.GOAL_TO_ACHIEVE_PARAM) == null
                || ((String) form.get(Ibd.GOAL_TO_ACHIEVE_PARAM)).length() == 0) {
            actionErrors.add(Ibd.GOAL_TO_ACHIEVE_PARAM, new ActionMessage(Ibd.GOAL_TO_ACHIEVE_REQUIRED));
        }

        if (form.get(Ibd.GOAL_SCORE_PARAM) == null || ((Integer) form.get(Ibd.GOAL_SCORE_PARAM) <= 0)) {
            actionErrors.add(Ibd.GOAL_SCORE_PARAM, new ActionMessage(Ibd.GOAL_SCORE_REQUIRED));
        }

        if (form.get(Ibd.HOW_TO_ACHIEVE_GOAL_PARAM) == null
                || ((String) form.get(Ibd.HOW_TO_ACHIEVE_GOAL_PARAM)).length() == 0) {
            actionErrors.add(Ibd.HOW_TO_ACHIEVE_GOAL_PARAM, new ActionMessage(Ibd.HOW_TO_ACHIEVE_GOAL_REQUIRED));
        }

        if (form.get(Ibd.BARRIERS_PARAM) == null || ((String) form.get(Ibd.BARRIERS_PARAM)).length() == 0) {
            actionErrors.add(Ibd.BARRIERS_PARAM, new ActionMessage(Ibd.BARRIERS_REQUIRED));
        }

        if (form.get(Ibd.WHAT_CAN_BE_DONE_PARAM) == null
                || ((String) form.get(Ibd.WHAT_CAN_BE_DONE_PARAM)).length() == 0) {
            actionErrors.add(Ibd.WHAT_CAN_BE_DONE_PARAM, new ActionMessage(Ibd.WHAT_CAN_BE_DONE_REQUIRED));
        }

        if (form.get(Ibd.CONFIDENCE_SCALE_PARAM) == null || ((Integer) form.get(Ibd.CONFIDENCE_SCALE_PARAM) <= 0)) {
            actionErrors.add(Ibd.CONFIDENCE_SCALE_PARAM, new ActionMessage(Ibd.CONFIDENCE_SCORE_REQUIRED));
        }

        if (form.get(Ibd.REVIEW_DATE_PARAM) == null || ((String) form.get(Ibd.REVIEW_DATE_PARAM)).length() == 0) {
            actionErrors.add(Ibd.REVIEW_DATE_PARAM, new ActionMessage(Ibd.REVIEW_DATE_REQUIRED));
        }

        if (actionErrors.size() > 0) {
            saveErrors(request, actionErrors);
            return false;
        }

        return true;
    }
}
