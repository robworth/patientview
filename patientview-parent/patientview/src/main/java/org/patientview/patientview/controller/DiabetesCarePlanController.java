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

package org.patientview.patientview.controller;

import org.patientview.actionutils.ActionUtils;
import org.patientview.ibd.Ibd;
import org.patientview.ibd.model.enums.Confidence;
import org.patientview.ibd.model.enums.Importance;
import org.patientview.patientview.model.DiabetesCarePlan;
import org.patientview.patientview.model.EdtaCode;
import org.patientview.patientview.model.User;
import org.patientview.patientview.model.UserMapping;
import org.patientview.patientview.user.UserUtils;
import org.patientview.service.DiabetesCarePlanManager;
import org.patientview.utils.LegacySpringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;

@Controller
public class DiabetesCarePlanController extends BaseController {

    // care plan lists
    private static final String IMPORTANCE_LIST_PROPERTY = "importanceList";
    private static final String CONFIDENCE_LIST_PROPERTY = "confidenceList";

    private static final String CAREPLAN_LINKS_TYPE = "careplanLinks";
    private static final String DIABETES_OVERALL_LINK_PARAM = "diabetesOverallLink";
    private static final String MOOD_LINK_PARAM = "moodLink";
    private static final String DIABETES_CONTROL_LINK_PARAM = "diabetesControlLink";
    private static final String HYPOGLYCAEMIA_LINK_PARAM = "hypoglycaemiaLink";
    private static final String WEIGHT_LINK_PARAM = "weightLink";
    private static final String BLOOD_PRESSURE_LINK_PARAM = "bloodPressureLink";
    private static final String CHOLESTEROL_LINK_PARAM = "cholesterolLink";
    private static final String SMOKING_ALCOHOL_LINK_PARAM = "smokingAlcoholLink";
    private static final String EATING_EXERCISE_SPORT_LINK_PARAM = "eatingExerciseSportLink";
    private static final String DRIVING_WORK_STUDY_LINK_PARAM = "drivingWorkStudyLink";
    private static final String PREGNANCY_SEX_RELATIONSHIPS_LINK_PARAM = "pregnancySexRelationshipsLink";
    private static final String EYES_LINK_PARAM = "eyesLink";
    private static final String KIDNEYS_LINK_PARAM = "kidneysLink";
    private static final String FEET_LINK_PARAM = "feetLink";
    private static final String HEART_ATTACKS_STROKES_LINK_PARAM = "heartAttacksStrokesLink";
    private static final String TAKING_MEDICATION_REGULARLY_LINK_PARAM = "takingMedicationRegularlyLink";

    private static final HashMap<String, String> CAREPLAN_QUESTIONS_MAP = new HashMap<String, String>() {

        {
            put("Diabetes overall", DIABETES_OVERALL_LINK_PARAM);
            put("Mood", MOOD_LINK_PARAM);
            put("Diabetes Control", DIABETES_CONTROL_LINK_PARAM);
            put("Hypoglycaemia", HYPOGLYCAEMIA_LINK_PARAM);
            put("Weight", WEIGHT_LINK_PARAM);
            put("Blood Pressure", BLOOD_PRESSURE_LINK_PARAM);
            put("Cholesterol", CHOLESTEROL_LINK_PARAM);
            put("Smoking / Alcohol", SMOKING_ALCOHOL_LINK_PARAM);
            put("Eating / Exercise / Sport", EATING_EXERCISE_SPORT_LINK_PARAM);
            put("Driving / Work / Study", DRIVING_WORK_STUDY_LINK_PARAM);
            put("Pregnancy / Sex / Relationships", PREGNANCY_SEX_RELATIONSHIPS_LINK_PARAM);
            put("Eyes", EYES_LINK_PARAM);
            put("Kidneys", KIDNEYS_LINK_PARAM);
            put("Feet", FEET_LINK_PARAM);
            put("Heart Attacks / Strokes", HEART_ATTACKS_STROKES_LINK_PARAM);
            put("Taking medication regularly", TAKING_MEDICATION_REGULARLY_LINK_PARAM);
        }
    };

    @RequestMapping(value = {Routes.DIABETES_CAREPLAN_URL }, method = {RequestMethod.POST, RequestMethod.GET })
    public String getCarePlan(HttpServletRequest request) {
        // set current nav
        ActionUtils.setUpNavLink("diabetes_careplan", request);

        DiabetesCarePlan carePlan = LegacySpringUtils.getDiabetesCarePlanManager().
                getCarePlan(UserUtils.retrieveUser(request));

        if (carePlan == null) {
            carePlan = new DiabetesCarePlan();
        }

        request.setAttribute("carePlan", carePlan);

        request.getSession().setAttribute(IMPORTANCE_LIST_PROPERTY, Importance.getAsList());
        request.getSession().setAttribute(CONFIDENCE_LIST_PROPERTY, Confidence.getAsList());

        // add any managed links in for this page
        addCareplanLinks(request);

        return Routes.DIABETES_CAREPLAN_PAGE;
    }

    private void addCareplanLinks(HttpServletRequest request) {
        for (EdtaCode edtaCode : LegacySpringUtils.getEdtaCodeManager().get(CAREPLAN_LINKS_TYPE)) {
            if (CAREPLAN_QUESTIONS_MAP.containsKey(edtaCode.getEdtaCode())) {
                String link = edtaCode.getMedicalLink01();

                if (link != null && link.length() > 0) {
                    request.setAttribute(CAREPLAN_QUESTIONS_MAP.get(edtaCode.getEdtaCode()), link);
                }
            }
        }
    }

    protected String convertFormDateString(Date date) {
        if (date != null) {
            return Ibd.DATE_FORMAT.format(date);
        }

        return "";
    }

    @RequestMapping(value = {Routes.DIABETES_CAREPLAN_UPDATE_URL }, method = {RequestMethod.POST })
    public String update(@Valid @ModelAttribute("carePlan") DiabetesCarePlan carePlan,
                         BindingResult formBindingResult, HttpServletRequest request) {

        if (formBindingResult.hasErrors()) {
            request.setAttribute("messages", true);
            return Routes.DIABETES_CAREPLAN_PAGE;
        }
        // set current nav
        ActionUtils.setUpNavLink("diabetes_careplan", request);

        User user = UserUtils.retrieveUser(request);
        DiabetesCarePlanManager diabetesCarePlanManager = LegacySpringUtils.getDiabetesCarePlanManager();

        // if its null then its new so set the nhs no to the user
        if (carePlan == null) {
            carePlan = new DiabetesCarePlan();
        }

        if (!carePlan.hasValidId()) {
            UserMapping userMapping = LegacySpringUtils.getUserManager().getUserMappingPatientEntered(user);
            if (userMapping != null) {
                carePlan.setNhsno(userMapping.getNhsno());
            }
        }

        diabetesCarePlanManager.saveCarePlan(carePlan);

        request.setAttribute("carePlan", carePlan);

        return Routes.DIABETES_CAREPLAN_PAGE;

    }


}
