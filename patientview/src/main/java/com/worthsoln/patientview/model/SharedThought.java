package com.worthsoln.patientview.model;

import com.worthsoln.ibd.Ibd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity(name = "pv_st_shared_thought")
public class SharedThought extends BaseModel {

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @Column(name = "positive_negative", nullable = false)
    private int positiveNegative;

    @Column(name = "is_patient")
    private Boolean isPatient;

    @Column(name = "is_principal_carer")
    private Boolean isPrincipalCarer;

    @Column(name = "is_relative")
    private Boolean isRelative;

    @Column(name = "is_friend")
    private Boolean isFriend;

    @Column(name = "is_about_me")
    private Boolean isAboutMe;

    @Column(name = "is_about_other")
    private Boolean isAboutOther;

    @Column(name = "is_anonymous")
    private Boolean isAnonymous;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "is_ongoing")
    private Boolean isOngoing;

    @Column
    private String location;

    @Column
    private String description;

    @Column(name = "suggested_action")
    private String suggestedAction;

    @Column(name = "concern_reason")
    private String concernReason;

    @Column(name = "likelihood_recurrence")
    private int likelihoodOfRecurrence;

    @Column(name = "how_serious")
    private int howSerious;

    @Column(name = "is_submitted")
    private boolean isSubmitted;

    // this will be set by manager
    @Column(name = "date_last_saved")
    private Date dateLastSaved;

    @ManyToOne(optional = false)
    @JoinColumn(name = "allocation_user_id")
    private User allocationUser;

    @Column(name = "is_action_taken")
    private Boolean isActionTaken;

    @ManyToOne(optional = false)
    @JoinColumn(name = "filterer_user_id")
    private User filtererUser;

    @Column(name = "is_patient_contacted")
    private Boolean isPatientContacted;

    @Column(name = "notes")
    private String notes;

    @Column(name = "status")
    private String status;

    @Column(name = "is_viewed")
    private Boolean isViewed;

    public SharedThought() {
    }

    public SharedThought(User user, Unit unit, int positiveNegative, Boolean isPatient, Boolean isPrincipalCarer,
                         Boolean isRelative,
                         Boolean isFriend, Boolean isAboutMe, Boolean isAboutOther, Boolean isAnonymous,
                         Date startDate, Date endDate, Boolean isOngoing, String location, String suggestedAction,
                         String concernDescription, String concernReason, int likelihoodOfRecurrence, int howSerious,
                         boolean isSubmitted) {
        this.user = user;
        this.unit = unit;
        this.positiveNegative = positiveNegative;
        this.isPatient = isPatient;
        this.isPrincipalCarer = isPrincipalCarer;
        this.isRelative = isRelative;
        this.isFriend = isFriend;
        this.isAboutMe = isAboutMe;
        this.isAboutOther = isAboutOther;
        this.isAnonymous = isAnonymous;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isOngoing = isOngoing;
        this.location = location;
        this.suggestedAction = suggestedAction;
        this.description = concernDescription;
        this.concernReason = concernReason;
        this.likelihoodOfRecurrence = likelihoodOfRecurrence;
        this.howSerious = howSerious;
        this.isSubmitted = isSubmitted;
    }
    public SharedThought(long id, User user, Unit unit, int positiveNegative, Boolean isPatient, Boolean isPrincipalCarer,
                         Boolean isRelative,
                         Boolean isFriend, Boolean isAboutMe, Boolean isAboutOther, Boolean isAnonymous,
                         Date startDate, Date endDate, Boolean isOngoing, String location, String suggestedAction,
                         String description, String concernReason, int likelihoodOfRecurrence, int howSerious,
                         boolean isSubmitted) {
        setId(id);
        this.user = user;
        this.unit = unit;
        this.positiveNegative = positiveNegative;
        this.isPatient = isPatient;
        this.isPrincipalCarer = isPrincipalCarer;
        this.isRelative = isRelative;
        this.isFriend = isFriend;
        this.isAboutMe = isAboutMe;
        this.isAboutOther = isAboutOther;
        this.isAnonymous = isAnonymous;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isOngoing = isOngoing;
        this.location = location;
        this.suggestedAction = suggestedAction;
        this.description = description;
        this.concernReason = concernReason;
        this.likelihoodOfRecurrence = likelihoodOfRecurrence;
        this.howSerious = howSerious;
        this.isSubmitted = isSubmitted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public int getPositiveNegative() {
        return positiveNegative;
    }

    public void setPositiveNegative(int positiveNegative) {
        this.positiveNegative = positiveNegative;
    }

    public Boolean getPatient() {
        return isPatient;
    }

    public void setPatient(Boolean patient) {
        isPatient = patient;
    }

    public Boolean getPrincipalCarer() {
        return isPrincipalCarer;
    }

    public void setPrincipalCarer(Boolean getPrincipalCarer) {
        this.isPrincipalCarer = isPrincipalCarer;
    }

    public Boolean getRelative() {
        return isRelative;
    }

    public void setRelative(Boolean getRelative) {
        this.isRelative = isRelative;
    }

    public Boolean getFriend() {
        return isFriend;
    }

    public void setFriend(Boolean getFriend) {
        this.isFriend = isFriend;
    }

    public Boolean getAboutMe() {
        return isAboutMe;
    }

    public void setAboutMe(Boolean aboutMe) {
        this.isAboutMe = aboutMe;
    }

    public Boolean getAboutOther() {
        return isAboutOther;
    }

    public void setAboutOther(Boolean aboutOther) {
        isAboutOther = aboutOther;
    }

    public Boolean getAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        this.isAnonymous = anonymous;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getOngoing() {
        return isOngoing;
    }

    public void setOngoing(Boolean ongoing) {
        isOngoing = ongoing;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSuggestedAction() {
        return suggestedAction;
    }

    public void setSuggestedAction(String suggestedAction) {
        this.suggestedAction = suggestedAction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConcernReason() {
        return concernReason;
    }

    public void setConcernReason(String concernReason) {
        this.concernReason = concernReason;
    }

    public int getLikelihoodOfRecurrence() {
        return likelihoodOfRecurrence;
    }

    public void setLikelihoodOfRecurrence(int likelihoodOfRecurrence) {
        this.likelihoodOfRecurrence = likelihoodOfRecurrence;
    }

    public int getHowSerious() {
        return howSerious;
    }

    public void setHowSerious(int howSerious) {
        this.howSerious = howSerious;
    }

    public Boolean isSubmitted() {
        return isSubmitted;
    }

    public void setSubmitted(boolean submitted) {
        isSubmitted = submitted;
    }

    public Date getDateLastSaved() {
        return dateLastSaved;
    }

    public void setDateLastSaved(Date dateLastSaved) {
        this.dateLastSaved = dateLastSaved;
    }

    public User getAllocationUser() {
        return allocationUser;
    }

    public void setAllocationUser(User allocationUser) {
        this.allocationUser = allocationUser;
    }

    public Boolean getActionTaken() {
        return isActionTaken;
    }

    public void setActionTaken(Boolean actionTaken) {
        isActionTaken = actionTaken;
    }

    public User getFiltererUser() {
        return filtererUser;
    }

    public void setFiltererUser(User filtererUser) {
        this.filtererUser = filtererUser;
    }

    public Boolean getPatientContacted() {
        return isPatientContacted;
    }

    public void setPatientContacted(Boolean patientContacted) {
        isPatientContacted = patientContacted;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getViewed() {
        return isViewed;
    }

    public void setViewed(Boolean viewed) {
        isViewed = viewed;
    }

    public String getDateLastSavedFormattedDate() {
        return getDateFormattedDate(dateLastSaved);
    }

    public String getDateLastSavedFormattedDateTime() {
        return getDateFormattedDateTime(dateLastSaved);
    }

    public String getStartDateFormattedDate() {
        return getDateFormattedDate(startDate);
    }

    public String getStartDateFormattedDateTime() {
        return getDateFormattedDateTime(startDate);
    }

    public String getEndDateFormattedDate() {
        return getDateFormattedDate(endDate);
    }

    public String getEndDateFormattedDateTime() {
        return getDateFormattedDateTime(endDate);
    }

    private String getDateFormattedDate(Date date) {
        if (date != null) {
            return Ibd.DATE_FORMAT.format(date);
        }

        return "";
    }

    private String getDateFormattedDateTime(Date date) {
        if (date != null) {
            return Ibd.DATE_TIME_FORMAT.format(date);
        }

        return "";
    }

    public String getDescriptionBeginning() {
        return (description.length() >= 49) ? description.substring(0, 48) : description;
    }
}
