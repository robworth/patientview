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

package org.patientview.patientview.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.patientview.ibd.Ibd;
import org.patientview.ibd.model.enums.Confidence;
import org.patientview.ibd.model.enums.Importance;
import org.patientview.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Transient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "dia_careplan")
public class DiabetesCarePlan extends BaseModel {

    @Transient
    private final int defaultScore = 5;

    @Column
//    @NotEmpty(message = "{" + Ibd.NHS_NO_NOT_FOUND + "}")
    private String nhsno;

    @Column(nullable = true)
    private Integer diabetesOverallScore = defaultScore;

    @Column(nullable = true)
    private Integer moodScore = defaultScore;

    @Column(nullable = true)
    private Integer diabetesControlScore = defaultScore;

    @Column(nullable = true)
    private Integer hypoglycaemiaScore = defaultScore;

    @Column(nullable = true)
    private Integer weightScore = defaultScore;

    @Column(nullable = true)
    private Integer bloodPressureScore = defaultScore;

    @Column(nullable = true)
    private Integer cholesterolScore = defaultScore;

    @Column(nullable = true)
    private Integer smokingAlcoholScore = defaultScore;

    @Column(nullable = true)
    private Integer eatingExerciseSportScore = defaultScore;

    @Column(nullable = true)
    private Integer drivingWorkStudyScore = defaultScore;

    @Column(nullable = true)
    private Integer pregnancySexRelationshipsScore = defaultScore;

    @Column(nullable = true)
    private Integer eyesScore = defaultScore;

    @Column(nullable = true)
    private Integer kidneysScore = defaultScore;

    @Column(nullable = true)
    private Integer feetScore = defaultScore;

    @Column(nullable = true)
    private Integer heartAttacksStrokesScore = defaultScore;

    @Column(nullable = true)
    private Integer takingMedicationRegularlyScore = defaultScore;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String otherAreasToDiscuss;

    @Column(columnDefinition = "TEXT")
    @NotEmpty(message = "{" + Ibd.GOALS_REQUIRED + "}")
    private String goals;

    @Column(columnDefinition = "TEXT")
    @NotEmpty(message = "{" + Ibd.GOAL_TO_ACHIEVE_REQUIRED + "}")
    private String goalToAchieve;

    @Transient
    private Importance importance;

    @Column(columnDefinition = "TEXT")
    @NotEmpty(message = "{" + Ibd.HOW_TO_ACHIEVE_GOAL_REQUIRED + "}")
    private String howToAchieveGoal;

    @Column(columnDefinition = "TEXT")
    @NotEmpty(message = "{" + Ibd.BARRIERS_REQUIRED + "}")
    private String barriers;

    @Column(columnDefinition = "TEXT")
    @NotEmpty(message = "{" + Ibd.WHAT_CAN_BE_DONE_REQUIRED + "}")
    private String whatCanBeDone;

    @Transient
    private Confidence confidence;

    @Column
    @NotNull(message = "{" + Ibd.REVIEW_DATE_REQUIRED + "}")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date reviewDate;

    @Column(name = "confidence_id")
    @NotNull(message = "{" + Ibd.CONFIDENCE_REQUIRED + "}")
    private long confidenceId;

    @Column(name = "importance_id")
    @NotNull(message = "{" + Ibd.IMPORTANCE_REQUIRED + "}")
    private long importanceId;

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public String getOtherAreasToDiscuss() {
        return otherAreasToDiscuss;
    }

    public void setOtherAreasToDiscuss(String otherAreasToDiscuss) {
        this.otherAreasToDiscuss = otherAreasToDiscuss;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getGoalToAchieve() {
        return goalToAchieve;
    }

    public void setGoalToAchieve(String goalToAchieve) {
        this.goalToAchieve = goalToAchieve;
    }

    public Importance getImportance() {
        return importance;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    public long getImportanceId() {
        return importanceId;
    }

    public void setImportanceId(long id) {
        this.importanceId = id;
        this.importance = Importance.getImportance(id);
    }

    public String getHowToAchieveGoal() {
        return howToAchieveGoal;
    }

    public void setHowToAchieveGoal(String howToAchieveGoal) {
        this.howToAchieveGoal = howToAchieveGoal;
    }

    public String getBarriers() {
        return barriers;
    }

    public void setBarriers(String barriers) {
        this.barriers = barriers;
    }

    public String getWhatCanBeDone() {
        return whatCanBeDone;
    }

    public void setWhatCanBeDone(String whatCanBeDone) {
        this.whatCanBeDone = whatCanBeDone;
    }

    public Confidence getConfidence() {
        return confidence;
    }

    public void setConfidence(Confidence confidence) {
        this.confidence = confidence;
    }


    public long getConfidenceId() {
        return confidenceId;
    }

    public void setConfidenceId(long id) {
        this.confidenceId = id;
        this.confidence = Confidence.getConfidence(id);
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Integer getDiabetesOverallScore() {
        return diabetesOverallScore;
    }

    public void setDiabetesOverallScore(Integer diabetesOverallScore) {
        this.diabetesOverallScore = diabetesOverallScore;
    }

    public Integer getMoodScore() {
        return moodScore;
    }

    public void setMoodScore(Integer moodScore) {
        this.moodScore = moodScore;
    }

    public Integer getDiabetesControlScore() {
        return diabetesControlScore;
    }

    public void setDiabetesControlScore(Integer diabetesControlScore) {
        this.diabetesControlScore = diabetesControlScore;
    }

    public Integer getHypoglycaemiaScore() {
        return hypoglycaemiaScore;
    }

    public void setHypoglycaemiaScore(Integer hypoglycaemiaScore) {
        this.hypoglycaemiaScore = hypoglycaemiaScore;
    }

    public Integer getWeightScore() {
        return weightScore;
    }

    public void setWeightScore(Integer weightScore) {
        this.weightScore = weightScore;
    }

    public Integer getBloodPressureScore() {
        return bloodPressureScore;
    }

    public void setBloodPressureScore(Integer bloodPressureScore) {
        this.bloodPressureScore = bloodPressureScore;
    }

    public Integer getCholesterolScore() {
        return cholesterolScore;
    }

    public void setCholesterolScore(Integer cholesterolScore) {
        this.cholesterolScore = cholesterolScore;
    }

    public Integer getSmokingAlcoholScore() {
        return smokingAlcoholScore;
    }

    public void setSmokingAlcoholScore(Integer smokingAlcoholScore) {
        this.smokingAlcoholScore = smokingAlcoholScore;
    }

    public Integer getEatingExerciseSportScore() {
        return eatingExerciseSportScore;
    }

    public void setEatingExerciseSportScore(Integer eatingExerciseSportScore) {
        this.eatingExerciseSportScore = eatingExerciseSportScore;
    }

    public Integer getDrivingWorkStudyScore() {
        return drivingWorkStudyScore;
    }

    public void setDrivingWorkStudyScore(Integer drivingWorkStudyScore) {
        this.drivingWorkStudyScore = drivingWorkStudyScore;
    }

    public Integer getPregnancySexRelationshipsScore() {
        return pregnancySexRelationshipsScore;
    }

    public void setPregnancySexRelationshipsScore(Integer pregnancySexRelationshipsScore) {
        this.pregnancySexRelationshipsScore = pregnancySexRelationshipsScore;
    }

    public Integer getEyesScore() {
        return eyesScore;
    }

    public void setEyesScore(Integer eyesScore) {
        this.eyesScore = eyesScore;
    }

    public Integer getKidneysScore() {
        return kidneysScore;
    }

    public void setKidneysScore(Integer kidneysScore) {
        this.kidneysScore = kidneysScore;
    }

    public Integer getFeetScore() {
        return feetScore;
    }

    public void setFeetScore(Integer feetScore) {
        this.feetScore = feetScore;
    }

    public Integer getHeartAttacksStrokesScore() {
        return heartAttacksStrokesScore;
    }

    public void setHeartAttacksStrokesScore(Integer heartAttacksStrokesScore) {
        this.heartAttacksStrokesScore = heartAttacksStrokesScore;
    }

    public Integer getTakingMedicationRegularlyScore() {
        return takingMedicationRegularlyScore;
    }

    public void setTakingMedicationRegularlyScore(Integer takingMedicationRegularlyScore) {
        this.takingMedicationRegularlyScore = takingMedicationRegularlyScore;
    }
}
