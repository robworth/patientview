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
import org.patientview.patientview.model.EdtaCode;
import org.patientview.patientview.user.UserUtils;
import org.patientview.utils.LegacySpringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class CarePlanEditAction extends BaseAction {

    private static final HashMap<String, String> CAREPLAN_QUESTIONS_MAP = new HashMap<String, String>() {
        {
            put("Overall my condition", Ibd.OVERALL_MY_CONDITION_LINK_PARAM);
            put("Tiredness /Fatigue", Ibd.TIREDNESS_FATIGUE_LINK_PARAM);
            put("Managing Pain", Ibd.MANAGING_PAIN_LINK_PARAM);
            put("Stress and worry", Ibd.STRESS_AND_WORRY_LINK_PARAM);
            put("Support from family and friends", Ibd.SUPPORT_FROM_FAMILY_AND_FRIENDS_LINK_PARAM);
            put("Managing my social life / hobbies", Ibd.MANAGING_MY_SOCIAL_LIFE_HOBBIES_LINK_PARAM);
            put("Managing work / studies", Ibd.MANAGING_WORK_STUDIES_LINK_PARAM);
            put("Taking my medicines regularly", Ibd.TAKING_MY_MEDICINES_REGULARLY_LINK_PARAM);
            put("Managing flare ups", Ibd.MANAGING_FLARE_UPS_LINK_PARAM);
            put("Stopping smoking", Ibd.STOPPING_SMOKING_LINK_PARAM);
            put("Sleeping", Ibd.SLEEPING_LINK_PARAM);
            put("Sexual relationships", Ibd.SEXUAL_RELATIONSHIPS_LINK_PARAM);
            put("Fertility / Pregnancy", Ibd.FERTILITY_PREGNANCY_LINK_PARAM);
            put("Learning about my condition", Ibd.LEARNING_ABOUT_MY_CONDITION_LINK_PARAM);
            put("Eating a healthy diet", Ibd.EATING_A_HEALTHY_DIET_LINK_PARAM);
            put("Travelling", Ibd.TRAVELLING_LINK_PARAM);
        }
    };

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

        // add any managed links in for this page
        addCareplanLinks(request);

        return mapping.findForward(SUCCESS);
    }

    private void addCareplanLinks(HttpServletRequest request) {
        for (EdtaCode edtaCode : LegacySpringUtils.getEdtaCodeManager().get(Ibd.CAREPLAN_LINKS_TYPE)) {
            if (CAREPLAN_QUESTIONS_MAP.containsKey(edtaCode.getEdtaCode())) {
                String link = edtaCode.getMedicalLink01();

                if (link != null && link.length() > 0) {
                    request.setAttribute(CAREPLAN_QUESTIONS_MAP.get(edtaCode.getEdtaCode()), link);
                }
            }
        }
    }
}
