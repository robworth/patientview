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

package org.patientview.ibd;

import java.text.SimpleDateFormat;

public final class Ibd {

    public static final int OTHER_MEDICATION_TYPE_ID = 6;

    public static final String SHOW_ADVICE_PARAM = "showAdvice";
    public static final String MY_IBD_PARAM = "myIbd";
    public static final String MY_IBD_DIAGNOSIS_PARAM = "myIbdDiagnosis";
    public static final String MY_MEDICATION_PARAM = "myMedication";
    public static final String NHS_NO_PARAM = "nhsNo";
    public static final String ID_PARAM = "id";
    public static final String SUBMIT_PARAM = "submit";
    public static final String NUTRITION_LIST = "nutritionList";
    public static final String CURRENT_MEDICATIONS_PARAM = "currentMedications";
    public static final String STOPPED_MEDICATIONS_PARAM = "stoppedMedications";
    public static final String UNIT_CODE_PARAM = "unitcode";

    public static final String CROHNS_DIAGRAM = "crohns.jpg";
    public static final String COLITIS_UNSPECIFIED_DIAGRAM = "colitis-unspecified.jpg";
    public static final String ULCERATIVE_COLITIS_DIAGRAM = "ulcerative-colitis.jpg";
    public static final String PROCTITIS_DIAGRAM = "proctitis.png";
    public static final String LEFT_SIDED_COLITIS_DIAGRAM = "left-sided-colitis.png";
    public static final String EXTENSIVE_COLITIS_DIAGRAM = "extensive-colitis.png";
    public static final String ILEAL_CROHNS_DIAGRAM = "ileal-crohns.png";
    public static final String ILEO_COLONIC_DISEASE_DIAGRAM = "ileo-colonic-disease.png";
    public static final String CROHNS_COLITIS_DIAGRAM = "crohns-colitis.png";
    public static final String ISOLATED_UPPER_GI_DISEASE_DIAGRAM = "isolated-upper-gi-disease.png";

    // ibd severity level form params
    public static final String SEVERE_LEVEL_PARAM = "severeLevel";
    public static final String SEVERE_TREATMENT_PARAM = "severeTreatment";
    public static final String MODERATE_LEVEL_PARAM = "moderateLevel";
    public static final String MODERATE_TREATMENT_PARAM = "moderateTreatment";
    public static final String MILD_LEVEL_PARAM = "mildLevel";
    public static final String MILD_TREATMENT_PARAM = "mildTreatment";

    // ibd link params
    public static final String MY_IBD_LINKS_TYPE = "myIbdLinks";
    public static final String PRIMARY_DIAGNOSIS_LINK_PARAM = "primaryDiagnosisLink";
    public static final String DISEASE_EXTENT_LINK_PARAM = "diseaseExtentLink";
    public static final String YEAR_OF_DIAGNOSIS_LINK_PARAM = "yearOfDiagnosisLink";
    public static final String BODY_PART_AFFECTED_LINK_PARAM = "bodyPartAffectedLink";
    public static final String WEIGHT_LINK_PARAM = "weightLink";
    public static final String COMPLICATIONS_LINK_PARAM = "complicationLink";
    public static final String YEAR_FOR_SURVEILLANCE_COLONOSCOPY_LINK_PARAM = "yearForSurveillanceColonoscopyLink";
    public static final String NAMED_CONSULTANT_LINK_PARAM = "namedConsultantLink";
    public static final String NURSES_LINK_PARAM = "nursesLink";
    public static final String FAMILY_HISTORY_LINK_PARAM = "familyHistoryLink";
    public static final String SMOKING_LINK_PARAM = "smokingLink";
    public static final String SURGERY_LINK_PARAM = "surgeryLink";
    public static final String VACCINATION_RECORD_LINK_PARAM = "vaccinationRecordLink";

    // care plan link params
    public static final String CAREPLAN_LINKS_TYPE = "careplanLinks";
    public static final String OVERALL_MY_CONDITION_LINK_PARAM = "overallMyConditionLink";
    public static final String TIREDNESS_FATIGUE_LINK_PARAM = "tirednessFatigueLink";
    public static final String MANAGING_PAIN_LINK_PARAM = "managingPainLink";
    public static final String STRESS_AND_WORRY_LINK_PARAM = "stressAndWorryLink";
    public static final String SUPPORT_FROM_FAMILY_AND_FRIENDS_LINK_PARAM = "supportFromFamilyAndFriendsLink";
    public static final String MANAGING_MY_SOCIAL_LIFE_HOBBIES_LINK_PARAM = "managingMySocialLifeHobbiesLink";
    public static final String MANAGING_WORK_STUDIES_LINK_PARAM = "managingWorkStudiesLink";
    public static final String TAKING_MY_MEDICINES_REGULARLY_LINK_PARAM = "takingMyMedicinesRegularlyLink";
    public static final String MANAGING_FLARE_UPS_LINK_PARAM = "managingFlareUpsLink";
    public static final String STOPPING_SMOKING_LINK_PARAM = "stoppingSmokingLink";
    public static final String SLEEPING_LINK_PARAM = "sleepingLink";
    public static final String SEXUAL_RELATIONSHIPS_LINK_PARAM = "sexualRelationshipsLink";
    public static final String FERTILITY_PREGNANCY_LINK_PARAM = "fertilityPregnancyLink";
    public static final String LEARNING_ABOUT_MY_CONDITION_LINK_PARAM = "learningAboutMyConditionLink";
    public static final String EATING_A_HEALTHY_DIET_LINK_PARAM = "eatingAHealthyDietLink";
    public static final String TRAVELLING_LINK_PARAM = "travellingLink";

    // ibd form params
    public static final String DIAGNOSIS_ID_PARAM = "diagnosisId";
    public static final String DISEASE_EXTENT_ID_PARAM = "diseaseExtentId";
    public static final String YEAR_OF_DIAGNOSIS_PARAM = "yearOfDiagnosis";
    public static final String BODY_PART_AFFECTED_PARAM = "bodyPartAffected";
    public static final String WEIGHT_PARAM = "weight";
    public static final String COMPLICATIONS_PARAM = "complications";
    public static final String YEAR_FOR_SURVEILLANCE_COLONOSCOPY_PARAM = "yearForSurveillanceColonoscopy";
    public static final String NAMED_CONSULTANT_PARAM = "namedConsultant";
    public static final String NURSES_PARAM = "nurses";
    public static final String FAMILY_HISTORY_PARAM = "familyHistory";
    public static final String SMOKING_PARAM = "smoking";
    public static final String SURGERY_PARAM = "surgery";
    public static final String VACCINATION_RECORD_PARAM = "vaccinationRecord";

    // care plan params
    public static final String OVERALL_MY_CONDITION_SCORE_PARAM = "overallMyConditionScore";
    public static final String TIREDNESS_FATIGUE_SCORE_PARAM = "tirednessFatigueScore";
    public static final String MANAGING_PAIN_SCORE_PARAM = "managingPainScore";
    public static final String STRESS_AND_WORRY_SCORE_PARAM = "stressAndWorryScore";
    public static final String SUPPORT_FROM_FAMILY_AND_FRIENDS_SCORE_PARAM = "supportFromFamilyAndFriendsScore";
    public static final String MANAGING_MY_SOCIAL_LIFE_HOBBIES_SCORE_PARAM = "managingMySocialLifeHobbiesScore";
    public static final String MANAGING_WORK_STUDIES_SCORE_PARAM = "managingWorkStudiesScore";
    public static final String TAKING_MY_MEDICINES_REGULARLY_SCORE_PARAM = "takingMyMedicinesRegularlyScore";
    public static final String MANAGING_FLARE_UPS_SCORE_PARAM = "managingFlareUpsScore";
    public static final String STOPPING_SMOKING_SCORE_PARAM = "stoppingSmokingScore";
    public static final String SLEEPING_SCORE_PARAM = "sleepingScore";
    public static final String SEXUAL_RELATIONSHIPS_SCORE_PARAM = "sexualRelationshipsScore";
    public static final String FERTILITY_PREGNANCY_SCORE_PARAM = "fertilityPregnancyScore";
    public static final String LEARNING_ABOUT_MY_CONDITION_SCORE_PARAM = "learningAboutMyConditionScore";
    public static final String EATING_A_HEALTHY_DIET_SCORE_PARAM = "eatingAHealthyDietScore";
    public static final String TRAVELLING_SCORE_PARAM = "travellingScore";
    public static final String OTHER_AREAS_TO_DISCUSS_PARAM = "otherAreasToDiscuss";
    public static final String GOALS_PARAM = "goals";
    public static final String GOAL_TO_ACHIEVE_PARAM = "goalToAchieve";
    public static final String IMPORTANCE_ID_PARAM = "importanceId";
    public static final String HOW_TO_ACHIEVE_GOAL_PARAM = "howToAchieveGoal";
    public static final String BARRIERS_PARAM = "barriers";
    public static final String WHAT_CAN_BE_DONE_PARAM = "whatCanBeDone";
    public static final String CONFIDENCE_ID_PARAM = "confidenceId";
    public static final String REVIEW_DATE_PARAM = "reviewDate";

    // medicine form params
    public static final String DATE_STARTED_PARAM = "dateStarted";
    public static final String DATE_STOPPED_PARAM = "dateStopped";
    public static final String MEDICATION_TYPE_ID_PARAM = "medicationTypeId";
    public static final String MEDICATION_ID_PARAM = "medicationId";
    public static final String MEDICATION_DOSE_ID_PARAM = "medicationDoseId";
    public static final String OTHER_MEDICATION_ID_PARAM = "otherMedication";
    public static final String OTHER_MEDICATION_DOSE_ID_PARAM = "otherMedicationDose";
    public static final String MEDICATION_FREQUENCY_ID_PARAM = "medicationFrequencyId";
    public static final String REASON_FOR_STOPPING_PARAM = "reasonForStopping";

    // nutrition params
    public static final String FOODS_THAT_DISAGREE_PARAM = "foodsThatDisagree";
    public static final String COMMENT_PARAM = "comment";
    public static final String NUTRITION_DATE_PARAM = "nutritionDate";

    // symptoms params
    public static final String SYMPTOM_DATE_PARAM = "symptomDate";
    public static final String ABDOMINAL_PAIN_PARAM = "abdominalPainId";
    public static final String OPEN_BOWELS_PARAM = "openBowels";
    public static final String FEELING_PARAM = "feelingId";
    public static final String COMPLICATION_PARAM = "complicationId";
    public static final String MASS_IN_TUMMY_PARAM = "massInTummyId";
    public static final String NUMBER_OF_STOOLS_DAYTIME_PARAM = "numberOfStoolsDaytimeId";
    public static final String NUMBER_OF_STOOLS_NIGHTTIME_PARAM = "numberOfStoolsNighttimeId";
    public static final String TOILET_TIMING_PARAM = "toiletTimingId";
    public static final String PRESENT_BLOOD_PARAM = "presentBloodId";
    public static final String MY_IBD_SEVERITY_LEVEL_PARAM = "myIbdSeverityLevel";

    // graph data params
    public static final int COLITIS_GRAPH_TYPE = 1;
    public static final int CROHNS_GRAPH_TYPE = 2;
    public static final String FROM_DATE_PARAM = "fromDate";
    public static final String TO_DATE_PARAM = "toDate";
    public static final String GRAPH_TYPE_PARAM = "graphType";
    public static final String GRAPH_DATA_PARAM = "graphData";
    public static final String NO_GRAPH_TYPE_SPECIFIED = "No graph type specified";

    // message keys
    public static final String NHS_NO_NOT_FOUND = "nhsNo.notFound";
    public static final String NOT_A_VALID_ID = "id.notValid";

    // my ibd
    public static final String DIAGNOSIS_REQUIRED = "diagnosis.required";
    public static final String DISEASE_EXTENT_REQUIRED = "diseaseExtent.required";
    public static final String YEAR_OF_DIAGNOSIS_REQUIRED = "yearOfDiagnosis.required";
    public static final String COMPLICATIONS_REQUIRED = "complications.required";
    public static final String BODY_PART_AFFECTED_REQUIRED = "bodyPartAffected.required";
    public static final String WEIGHT_REQUIRED = "weight.required";
    public static final String FAMILY_HISTORY_REQUIRED = "familyHistory.required";
    public static final String SMOKING_REQUIRED = "smoking.required";
    public static final String SURGERY_REQUIRED = "surgery.required";
    public static final String VACCINATION_RECORD_REQUIRED = "vaccinationRecord.required";

    // care plan
    public static final String AREAS_TO_DISCUSS_REQUIRED = "areasToDiscuss.required";
    public static final String GOALS_REQUIRED = "goals.required";
    public static final String GOAL_TO_ACHIEVE_REQUIRED = "goalToAchieve.required";
    public static final String IMPORTANCE_REQUIRED = "importance.required";
    public static final String HOW_TO_ACHIEVE_GOAL_REQUIRED = "howToAchieveGoal.required";
    public static final String BARRIERS_REQUIRED = "barriers.required";
    public static final String WHAT_CAN_BE_DONE_REQUIRED = "whatCanBeDone.required";
    public static final String CONFIDENCE_REQUIRED = "confidence.required";
    public static final String REVIEW_DATE_REQUIRED = "reviewDate.required";

    // medication
    public static final String MEDICATION_NOT_FOUND_TO_UPDATE = "medication.notFound";
    public static final String DATE_STARTED_REQUIRED = "dateStarted.required";
    public static final String MEDICATION_TYPE_REQUIRED = "medicationType.required";
    public static final String MEDICATION_REQUIRED = "medication.required";
    public static final String MEDICATION_DOSE_REQUIRED = "medicationDose.required";
    public static final String OTHER_MEDICATION_REQUIRED = "otherMedication.required";
    public static final String MEDICATION_FREQUENCY_REQUIRED = "medicationFrequency.required";
    public static final String REASON_FOR_STOPPING_REQUIRED = "reasonForStopping.required";

    // nutrition
    public static final String FOODS_THAT_DISAGREE_REQUIRED = "foodsThatDisagree.required";

    // symptoms
    public static final String OPEN_BOWELS_INVALID_NUMBER = "openBowels.invalidNumber";
    public static final String VALUES_MUST_BE_MULTIPLE_OF_TWO = "valuesMustBeMultipleOfTwo";

    public static final String DATE_REQUIRED = "date.required";

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    public static final SimpleDateFormat YEAR_DATE_FORMAT = new SimpleDateFormat("yyyy");

    // areas to discuss types
    public static final int AREA_TO_DISCUSS_CONCERN_TYPE_ID = 1;
    public static final int AREA_TO_DISCUSS_HELP_TYPE_ID = 2;

    private Ibd() {

    }
}
