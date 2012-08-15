package com.worthsoln.ibd;

import java.text.SimpleDateFormat;

public class Ibd {

    public static final String MY_IBD_PARAM = "myIbd";
    public static final String MY_MEDICATION_PARAM = "myMedication";
    public static final String NHS_NO_PARAM = "nhsNo";
    public static final String ID_PARAM = "id";
    public static final String SUBMIT_PARAM = "submit";
    public static final String NUTRITION_LIST = "nutritionList";

    public static final String CROHNS_DIAGRAM = "crohns.jpg";
    public static final String COLITIS_UNSPECIFIED_DIAGRAM = "colitis-unspecified.jpg";
    public static final String ULCERATIVE_COLITIS_DIAGRAM = "ulcerative-colitis.jpg";

    // ibd form params
    public static final String DIAGNOSIS_ID_PARAM = "diagnosisId";
    public static final String DISEASE_EXTENT_ID_PARAM = "diseaseExtentId";
    public static final String BODY_PART_AFFECTED_ID_PARAM = "bodyPartAffectedId";
    public static final String WEIGHT_PARAM = "weight";
    public static final String FAMILY_HISTORY_ID_PARAM = "familyHistoryId";
    public static final String SMOKING_ID_PARAM = "smokingId";
    public static final String SURGERY_ID_PARAM = "surgeryId";
    public static final String VACCINATION_RECORD_ID_PARAM = "vaccinationRecordId";
    public static final String COMPLICATION_IDS_PARAM = "complicationIds";

    // care plan params
    public static final String AREA_TO_DISCUSS_IDS_PARAM = "areaToDiscussIds";
    public static final String FURTHER_TOPICS_PARAM = "furtherTopics";
    public static final String GOALS_PARAM = "goals";
    public static final String GOAL_TO_ACHIEVE_PARAM = "goalToAchieve";
    public static final String GOAL_SCORE_PARAM = "goalScale";
    public static final String HOW_TO_ACHIEVE_GOAL_PARAM = "howToAchieveGoal";
    public static final String BARRIERS_PARAM = "barriers";
    public static final String WHAT_CAN_BE_DONE_PARAM = "whatCanBeDone";
    public static final String CONFIDENCE_SCALE_PARAM = "confidenceScale";
    public static final String REVIEW_DATE_PARAM = "reviewDate";

    // medicine form params
    public static final String DATE_STARTED_PARAM = "dateStarted";
    public static final String DATE_STOPPED_PARAM = "dateStopped";
    public static final String MEDICATION_TYPE_ID_PARAM = "medicationTypeId";
    public static final String MEDICATION_ID_PARAM = "medicationId";
    public static final String MEDICATION_DOSE_ID_PARAM = "medicationDoseId";
    public static final String OTHER_MEDICATION_ID_PARAM = "otherMedication";
    public static final String MEDICATION_NO_OF_ID_PARAM = "medicationNoOfId";
    public static final String MEDICATION_FREQUENCY_ID_PARAM = "medicationFrequencyId";
    public static final String REASON_FOR_STOPPING_PARAM = "reasonForStopping";

    // nutrition params
    public static final String FOODS_THAT_DISAGREE_PARAM = "foodsThatDisagree";
    public static final String COMMENT_PARAM = "comment";
    public static final String NUTRITION_DATE_PARAM = "nutritionDate";

    // symptoms params
    public static final String SYMPTOM_DATE_PARAM = "symptomDate";
    public static final String ABDOMINAL_PAIN_PARAM = "abdominalPain";
    public static final String OPEN_BOWELS_PARAM = "openBowels";
    public static final String FEELING_PARAM = "feeling";
    public static final String COMPLICATIONS_PARAM = "complications";
    public static final String MASS_IN_TUMMY_PARAM = "massInTummy";
    public static final String STOOLS_DATE_PARAM = "stoolsDay";
    public static final String STOOLS_NIGHT_PARAM = "stoolsNight";
    public static final String TOILET_TIMING_PARAM = "toiletTiming";
    public static final String PRESENT_BLOOD_PARAM = "presentBlood";
    public static final String FURTHER_COMPLICATIONS_PARAM = "furtherComplications";

    // graph data params
    public static final int COLITIS_GRAPH_TYPE = 1;
    public static final int CROHNS_GRAPH_TYPE = 2;
    public static final String FROM_DATE_PARAM = "fromDate";
    public static final String TO_DATE_PARAM = "toDate";
    public static final String GRAPH_TYPE_PARAM = "graphType";
    public static final String GRAPH_DATA_ERROR_PARAM = "graphDataError";
    public static final String GRAPH_SCORES_PARAM = "graphScores";

    // message keys
    public static final String NHS_NO_NOT_FOUND = "nhsNo.notFound";
    public static final String NOT_A_VALID_ID = "id.notValid";

    // my ibd
    public static final String DIAGNOSIS_REQUIRED = "diagnosis.required";
    public static final String DISEASE_EXTENT_REQUIRED = "diseaseExtent.required";
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
    public static final String GOAL_SCORE_REQUIRED = "goalScore.required";
    public static final String HOW_TO_ACHIEVE_GOAL_REQUIRED = "howToAchieveGoal.required";
    public static final String BARRIERS_REQUIRED = "barriers.required";
    public static final String WHAT_CAN_BE_DONE_REQUIRED = "whatCanBeDone.required";
    public static final String CONFIDENCE_SCORE_REQUIRED = "confidenceScore.required";
    public static final String REVIEW_DATE_REQUIRED = "reviewDate.required";

    // medication
    public static final String MEDICATION_NOT_FOUND_TO_UPDATE = "medication.notFound";
    public static final String DATE_STARTED_REQUIRED = "dateStarted.required";
    public static final String MEDICATION_TYPE_REQUIRED = "medicationType.required";
    public static final String MEDICATION_REQUIRED = "medication.required";
    public static final String MEDICATION_DOSE_REQUIRED = "medicationDose.required";
    public static final String OTHER_MEDICATION_REQUIRED = "otherMedication.required";
    public static final String MEDICATION_NO_OF_REQUIRED = "medicationNoOf.required";
    public static final String MEDICATION_FREQUENCY_REQUIRED = "medicationFrequency.required";
    public static final String REASON_FOR_STOPPING_REQUIRED = "reasonForStopping.required";

    // nutrition
    public static final String FOODS_THAT_DISAGREE_REQUIRED = "foodsThatDisagree.required";

    // symptoms
    public static final String OPEN_BOWELS_INVALID_NUMBER = "openBowels.invalidNumber";

    public static final String DATE_REQUIRED = "date.required";

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
}
