package com.worthsoln.ibd.model;

import com.worthsoln.ibd.model.enums.AreaToDiscuss;
import com.worthsoln.patientview.model.BaseModel;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ibd_careplan")
public class CarePlan extends BaseModel {

    @Column(nullable = false)
    private String nhsno;

    @Transient
    List<AreaToDiscuss> areasToDiscuss;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String furtherTopics;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String goals;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String goalToAchieve;

    @Column(nullable = true)
    private Integer goalScale;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String howToAchieveGoal;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String barriers;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String whatCanBeDone;

    @Column(nullable = true)
    private Integer confidenceScale;

    @Column(nullable = true)
    private Date reviewDate;

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public List<AreaToDiscuss> getAreasToDiscuss() {
        return areasToDiscuss;
    }

    public void setAreasToDiscuss(List<AreaToDiscuss> areasToDiscuss) {
        this.areasToDiscuss = areasToDiscuss;
    }

    @Access(AccessType.PROPERTY)
    @ElementCollection
    @CollectionTable(name = "ibd_careplan_areas_to_discuss", joinColumns = @JoinColumn(name = "careplan_id"))
    @Column(name = "area_to_discuss_id")
    public Set<Long> getAreaToDiscussIds() {
        if (areasToDiscuss != null) {
            Set<Long> areaToDiscussIds = new HashSet<Long>(areasToDiscuss.size());

            for (AreaToDiscuss areaToDiscuss : areasToDiscuss) {
                areaToDiscussIds.add(areaToDiscuss.getId());
            }

            return areaToDiscussIds;
        }

        return new HashSet<Long>(0);
    }

    public void setAreaToDiscussIds(Set<Long> areaToDiscussIds) {
        if (areaToDiscussIds != null) {
            areasToDiscuss = new ArrayList<AreaToDiscuss>(areaToDiscussIds.size());

            for (long areaToDiscussId : areaToDiscussIds) {
                areasToDiscuss.add(AreaToDiscuss.getAreasToDiscuss(areaToDiscussId));
            }
        }
    }

    public String getFurtherTopics() {
        return furtherTopics;
    }

    public void setFurtherTopics(String furtherTopics) {
        this.furtherTopics = furtherTopics;
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

    public Integer getGoalScale() {
        return goalScale;
    }

    public void setGoalScale(Integer goalScale) {
        this.goalScale = goalScale;
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

    public Integer getConfidenceScale() {
        return confidenceScale;
    }

    public void setConfidenceScale(Integer confidenceScale) {
        this.confidenceScale = confidenceScale;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}
