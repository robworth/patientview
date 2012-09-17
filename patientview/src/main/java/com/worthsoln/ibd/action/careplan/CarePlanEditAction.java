package com.worthsoln.ibd.action.careplan;

import com.worthsoln.actionutils.ActionUtils;
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
        // set current nav
        ActionUtils.setUpNavLink(mapping.getParameter(), request);

        CarePlan carePlan = getIbdManager().getCarePlan(UserUtils.retrieveUser(request));

        if (carePlan == null) {
            carePlan = new CarePlan();
        }

        DynaActionForm dynaForm = (DynaActionForm) form;
        dynaForm.set(Ibd.OTHER_AREAS_TO_DISCUSS_PARAM, carePlan.getOtherAreasToDiscuss());
        dynaForm.set(Ibd.GOALS_PARAM, carePlan.getGoals());
        dynaForm.set(Ibd.GOAL_TO_ACHIEVE_PARAM, carePlan.getGoalToAchieve());
        dynaForm.set(Ibd.IMPORTANCE_ID_PARAM, carePlan.getImportanceId());
        dynaForm.set(Ibd.HOW_TO_ACHIEVE_GOAL_PARAM, carePlan.getHowToAchieveGoal());
        dynaForm.set(Ibd.BARRIERS_PARAM, carePlan.getBarriers());
        dynaForm.set(Ibd.WHAT_CAN_BE_DONE_PARAM, carePlan.getWhatCanBeDone());
        dynaForm.set(Ibd.CONFIDENCE_ID_PARAM, carePlan.getConfidenceId());
        dynaForm.set(Ibd.REVIEW_DATE_PARAM, convertFormDateString(carePlan.getReviewDate()));
        dynaForm.set(Ibd.OVERALL_MY_CONDITION_SCORE_PARAM, carePlan.getOverallMyConditionScore());
        dynaForm.set(Ibd.TIREDNESS_FATIGUE_SCORE_PARAM, carePlan.getTirednessFatigueScore());
        dynaForm.set(Ibd.MANAGING_PAIN_SCORE_PARAM, carePlan.getManagingPainScore());
        dynaForm.set(Ibd.STRESS_AND_WORRY_SCORE_PARAM, carePlan.getStressAndWorryScore());
        dynaForm.set(Ibd.SUPPORT_FROM_FAMILY_AND_FRIENDS_SCORE_PARAM, carePlan.getSupportFromFamilyAndFriendsScore());
        dynaForm.set(Ibd.MANAGING_MY_SOCIAL_LIFE_HOBBIES_SCORE_PARAM, carePlan.getManagingMySocialLifeHobbiesScore());
        dynaForm.set(Ibd.MANAGING_WORK_STUDIES_SCORE_PARAM, carePlan.getManagingWorkStudiesScore());
        dynaForm.set(Ibd.TAKING_MY_MEDICINES_REGULARLY_SCORE_PARAM, carePlan.getTakingMyMedicinesRegularlyScore());
        dynaForm.set(Ibd.MANAGING_FLARE_UPS_SCORE_PARAM, carePlan.getManagingFlareUpsScore());
        dynaForm.set(Ibd.STOPPING_SMOKING_SCORE_PARAM, carePlan.getStoppingSmokingScore());
        dynaForm.set(Ibd.SLEEPING_SCORE_PARAM, carePlan.getSleepingScore());
        dynaForm.set(Ibd.SEXUAL_RELATIONSHIPS_SCORE_PARAM, carePlan.getSexualRelationshipsScore());
        dynaForm.set(Ibd.FERTILITY_PREGNANCY_SCORE_PARAM, carePlan.getFertilityPregnancyScore());
        dynaForm.set(Ibd.LEARNING_ABOUT_MY_CONDITION_SCORE_PARAM, carePlan.getLearningAboutMyConditionScore());
        dynaForm.set(Ibd.EATING_A_HEALTHY_DIET_SCORE_PARAM, carePlan.getEatingAHealthyDietScore());
        dynaForm.set(Ibd.TRAVELLING_SCORE_PARAM, carePlan.getTravellingScore());

        request.getSession().setAttribute(IMPORTANCE_LIST_PROPERTY, getImportanceList());
        request.getSession().setAttribute(CONFIDENCE_LIST_PROPERTY, getConfidenceList());

        return mapping.findForward(SUCCESS);
    }
}
