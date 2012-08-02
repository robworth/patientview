package com.worthsoln.ibd;

import java.text.SimpleDateFormat;

public class Ibd {

    public static final String MY_IBD_PARAM = "myIbd";
    public static final String NHS_NO_PARAM = "nhsNo";

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

    // message keys
    public static final String NHS_NO_NOT_FOUND = "nhsNo.notFound";

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


    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
}
