package com.worthsoln.ibd.model;

import com.worthsoln.ibd.model.enums.Confidence;
import com.worthsoln.ibd.model.enums.Importance;
import com.worthsoln.patientview.model.BaseModel;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Table(name = "ibd_careplan")
public class CarePlan extends BaseModel {

    @Transient
    private final int DEFAULT_SCORE = 5;

    @Column(nullable = false)
    private String nhsno;

    @Column(nullable = true)
    private Integer overallMyConditionScore = DEFAULT_SCORE;

    @Column(nullable = true)
    private Integer tirednessFatigueScore = DEFAULT_SCORE;

    @Column(nullable = true)
    private Integer managingPainScore = DEFAULT_SCORE;

    @Column(nullable = true)
    private Integer stressAndWorryScore = DEFAULT_SCORE;

    @Column(nullable = true)
    private Integer supportFromFamilyAndFriendsScore = DEFAULT_SCORE;

    @Column(nullable = true)
    private Integer managingMySocialLifeHobbiesScore = DEFAULT_SCORE;

    @Column(nullable = true)
    private Integer managingWorkStudiesScore = DEFAULT_SCORE;

    @Column(nullable = true)
    private Integer takingMyMedicinesRegularlyScore = DEFAULT_SCORE;

    @Column(nullable = true)
    private Integer managingFlareUpsScore = DEFAULT_SCORE;

    @Column(nullable = true)
    private Integer stoppingSmokingScore = DEFAULT_SCORE;

    @Column(nullable = true)
    private Integer sleepingScore = DEFAULT_SCORE;

    @Column(nullable = true)
    private Integer sexualRelationshipsScore = DEFAULT_SCORE;

    @Column(nullable = true)
    private Integer fertilityPregnancyScore = DEFAULT_SCORE;

    @Column(nullable = true)
    private Integer learningAboutMyConditionScore = DEFAULT_SCORE;

    @Column(nullable = true)
    private Integer eatingAHealthyDietScore = DEFAULT_SCORE;

    @Column(nullable = true)
    private Integer travellingScore = DEFAULT_SCORE;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String otherAreasToDiscuss;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String goals;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String goalToAchieve;

    @Transient
    private Importance importance;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String howToAchieveGoal;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String barriers;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String whatCanBeDone;

    @Transient
    private Confidence confidence;

    @Column(nullable = true)
    private Date reviewDate;

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public Integer getOverallMyConditionScore() {
        return overallMyConditionScore;
    }

    public void setOverallMyConditionScore(Integer overallMyConditionScore) {
        this.overallMyConditionScore = overallMyConditionScore;
    }

    public Integer getTirednessFatigueScore() {
        return tirednessFatigueScore;
    }

    public void setTirednessFatigueScore(Integer tirednessFatigueScore) {
        this.tirednessFatigueScore = tirednessFatigueScore;
    }

    public Integer getManagingPainScore() {
        return managingPainScore;
    }

    public void setManagingPainScore(Integer managingPainScore) {
        this.managingPainScore = managingPainScore;
    }

    public Integer getStressAndWorryScore() {
        return stressAndWorryScore;
    }

    public void setStressAndWorryScore(Integer stressAndWorryScore) {
        this.stressAndWorryScore = stressAndWorryScore;
    }

    public Integer getSupportFromFamilyAndFriendsScore() {
        return supportFromFamilyAndFriendsScore;
    }

    public void setSupportFromFamilyAndFriendsScore(Integer supportFromFamilyAndFriendsScore) {
        this.supportFromFamilyAndFriendsScore = supportFromFamilyAndFriendsScore;
    }

    public Integer getManagingMySocialLifeHobbiesScore() {
        return managingMySocialLifeHobbiesScore;
    }

    public void setManagingMySocialLifeHobbiesScore(Integer managingMySocialLifeHobbiesScore) {
        this.managingMySocialLifeHobbiesScore = managingMySocialLifeHobbiesScore;
    }

    public Integer getManagingWorkStudiesScore() {
        return managingWorkStudiesScore;
    }

    public void setManagingWorkStudiesScore(Integer managingWorkStudiesScore) {
        this.managingWorkStudiesScore = managingWorkStudiesScore;
    }

    public Integer getTakingMyMedicinesRegularlyScore() {
        return takingMyMedicinesRegularlyScore;
    }

    public void setTakingMyMedicinesRegularlyScore(Integer takingMyMedicinesRegularlyScore) {
        this.takingMyMedicinesRegularlyScore = takingMyMedicinesRegularlyScore;
    }

    public Integer getManagingFlareUpsScore() {
        return managingFlareUpsScore;
    }

    public void setManagingFlareUpsScore(Integer managingFlareUpsScore) {
        this.managingFlareUpsScore = managingFlareUpsScore;
    }

    public Integer getStoppingSmokingScore() {
        return stoppingSmokingScore;
    }

    public void setStoppingSmokingScore(Integer stoppingSmokingScore) {
        this.stoppingSmokingScore = stoppingSmokingScore;
    }

    public Integer getSleepingScore() {
        return sleepingScore;
    }

    public void setSleepingScore(Integer sleepingScore) {
        this.sleepingScore = sleepingScore;
    }

    public Integer getSexualRelationshipsScore() {
        return sexualRelationshipsScore;
    }

    public void setSexualRelationshipsScore(Integer sexualRelationshipsScore) {
        this.sexualRelationshipsScore = sexualRelationshipsScore;
    }

    public Integer getFertilityPregnancyScore() {
        return fertilityPregnancyScore;
    }

    public void setFertilityPregnancyScore(Integer fertilityPregnancyScore) {
        this.fertilityPregnancyScore = fertilityPregnancyScore;
    }

    public Integer getLearningAboutMyConditionScore() {
        return learningAboutMyConditionScore;
    }

    public void setLearningAboutMyConditionScore(Integer learningAboutMyConditionScore) {
        this.learningAboutMyConditionScore = learningAboutMyConditionScore;
    }

    public Integer getEatingAHealthyDietScore() {
        return eatingAHealthyDietScore;
    }

    public void setEatingAHealthyDietScore(Integer eatingAHealthyDietScore) {
        this.eatingAHealthyDietScore = eatingAHealthyDietScore;
    }

    public Integer getTravellingScore() {
        return travellingScore;
    }

    public void setTravellingScore(Integer travellingScore) {
        this.travellingScore = travellingScore;
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

    @Access(AccessType.PROPERTY)
    @Column(name = "importance_id", nullable = false)
    public long getImportanceId() {
        if (importance != null) {
            return importance.getId();
        }

        return -1;
    }

    public void setImportanceId(Long id) {
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

    @Access(AccessType.PROPERTY)
    @Column(name = "confidence_id", nullable = false)
    public long getConfidenceId() {
        if (confidence != null) {
            return confidence.getId();
        }

        return -1;
    }

    public void setConfidenceId(Long id) {
        this.confidence = Confidence.getConfidence(id);
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}
