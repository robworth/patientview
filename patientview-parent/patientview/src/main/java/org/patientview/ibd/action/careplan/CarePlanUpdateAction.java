/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.ibd.action.careplan;

import org.patientview.actionutils.ActionUtils;
import org.patientview.ibd.Ibd;
import org.patientview.ibd.action.BaseAction;
import org.patientview.ibd.model.CarePlan;
import org.patientview.patientview.model.User;
import org.patientview.patientview.user.UserUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        carePlan.setOtherAreasToDiscuss((String) dynaForm.get(Ibd.OTHER_AREAS_TO_DISCUSS_PARAM));
        carePlan.setGoals((String) dynaForm.get(Ibd.GOALS_PARAM));
        carePlan.setGoalToAchieve((String) dynaForm.get(Ibd.GOAL_TO_ACHIEVE_PARAM));
        carePlan.setImportanceId((Long) dynaForm.get(Ibd.IMPORTANCE_ID_PARAM));
        carePlan.setHowToAchieveGoal((String) dynaForm.get(Ibd.HOW_TO_ACHIEVE_GOAL_PARAM));
        carePlan.setBarriers((String) dynaForm.get(Ibd.BARRIERS_PARAM));
        carePlan.setWhatCanBeDone((String) dynaForm.get(Ibd.WHAT_CAN_BE_DONE_PARAM));
        carePlan.setConfidenceId((Long) dynaForm.get(Ibd.CONFIDENCE_ID_PARAM));
        carePlan.setReviewDate(convertFormDateString(Ibd.REVIEW_DATE_PARAM, dynaForm));
        carePlan.setOverallMyConditionScore((Integer) dynaForm.get(Ibd.OVERALL_MY_CONDITION_SCORE_PARAM));
        carePlan.setTirednessFatigueScore((Integer) dynaForm.get(Ibd.TIREDNESS_FATIGUE_SCORE_PARAM));
        carePlan.setManagingPainScore((Integer) dynaForm.get(Ibd.MANAGING_PAIN_SCORE_PARAM));
        carePlan.setStressAndWorryScore((Integer) dynaForm.get(Ibd.STRESS_AND_WORRY_SCORE_PARAM));
        carePlan.setSupportFromFamilyAndFriendsScore((Integer) dynaForm.get(
                Ibd.SUPPORT_FROM_FAMILY_AND_FRIENDS_SCORE_PARAM));
        carePlan.setManagingMySocialLifeHobbiesScore((Integer) dynaForm.get(
                Ibd.MANAGING_MY_SOCIAL_LIFE_HOBBIES_SCORE_PARAM));
        carePlan.setManagingWorkStudiesScore((Integer) dynaForm.get(Ibd.MANAGING_WORK_STUDIES_SCORE_PARAM));
        carePlan.setTakingMyMedicinesRegularlyScore((Integer) dynaForm.get(
                Ibd.TAKING_MY_MEDICINES_REGULARLY_SCORE_PARAM));
        carePlan.setManagingFlareUpsScore((Integer) dynaForm.get(Ibd.MANAGING_FLARE_UPS_SCORE_PARAM));
        carePlan.setStoppingSmokingScore((Integer) dynaForm.get(Ibd.STOPPING_SMOKING_SCORE_PARAM));
        carePlan.setSleepingScore((Integer) dynaForm.get(Ibd.SLEEPING_SCORE_PARAM));
        carePlan.setSexualRelationshipsScore((Integer) dynaForm.get(Ibd.SEXUAL_RELATIONSHIPS_SCORE_PARAM));
        carePlan.setFertilityPregnancyScore((Integer) dynaForm.get(Ibd.FERTILITY_PREGNANCY_SCORE_PARAM));
        carePlan.setLearningAboutMyConditionScore((Integer) dynaForm.get(Ibd.LEARNING_ABOUT_MY_CONDITION_SCORE_PARAM));
        carePlan.setEatingAHealthyDietScore((Integer) dynaForm.get(Ibd.EATING_A_HEALTHY_DIET_SCORE_PARAM));
        carePlan.setTravellingScore((Integer) dynaForm.get(Ibd.TRAVELLING_SCORE_PARAM));

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
        if (form.get(Ibd.GOALS_PARAM) == null || ((String) form.get(Ibd.GOALS_PARAM)).length() == 0) {
            actionErrors.add(Ibd.GOALS_PARAM, new ActionMessage(Ibd.GOALS_REQUIRED));
        }

        if (form.get(Ibd.GOAL_TO_ACHIEVE_PARAM) == null
                || ((String) form.get(Ibd.GOAL_TO_ACHIEVE_PARAM)).length() == 0) {
            actionErrors.add(Ibd.GOAL_TO_ACHIEVE_PARAM, new ActionMessage(Ibd.GOAL_TO_ACHIEVE_REQUIRED));
        }

        if (form.get(Ibd.IMPORTANCE_ID_PARAM) == null || ((Long) form.get(Ibd.IMPORTANCE_ID_PARAM) <= 0)) {
            actionErrors.add(Ibd.IMPORTANCE_ID_PARAM, new ActionMessage(Ibd.IMPORTANCE_REQUIRED));
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

        if (form.get(Ibd.CONFIDENCE_ID_PARAM) == null || ((Long) form.get(Ibd.CONFIDENCE_ID_PARAM) <= 0)) {
            actionErrors.add(Ibd.CONFIDENCE_ID_PARAM, new ActionMessage(Ibd.CONFIDENCE_REQUIRED));
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
