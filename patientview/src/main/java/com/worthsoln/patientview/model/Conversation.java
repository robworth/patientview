package com.worthsoln.patientview.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.util.Date;

@Entity
public class Conversation extends BaseModel {

    @Column(nullable = false)
    private boolean deleted;

    @Column(nullable = false)
    private Date started;

    @ManyToOne(optional = false)
    @JoinColumn(name = "participant1_id")
    private User participant1;

    @ManyToOne(optional = false)
    @JoinColumn(name = "participant2_id")
    private User participant2;

    // Transient methods are set by the manager and are to help the front end so we do less struts
    @Transient
    private int numberUnread = 0;

    // all latest message stuff is controlled in the manager
    @Transient
    private String latestMessageSummary;

    @Transient
    private Date latestMessageDate;

    @Transient
    private String friendlyLatestMessageDate;

    // this will be set so that the user in the message being shown to the user is the other person in the message
    @Transient
    private User otherUser;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public User getParticipant1() {
        return participant1;
    }

    public void setParticipant1(User participant1) {
        this.participant1 = participant1;
    }

    public User getParticipant2() {
        return participant2;
    }

    public void setParticipant2(User participant2) {
        this.participant2 = participant2;
    }

    public int getNumberUnread() {
        return numberUnread;
    }

    public void setNumberUnread(int numberUnread) {
        this.numberUnread = numberUnread;
    }

    public String getLatestMessageSummary() {
        return latestMessageSummary;
    }

    public void setLatestMessageSummary(String latestMessageSummary) {
        this.latestMessageSummary = latestMessageSummary;
    }

    public Date getLatestMessageDate() {
        return latestMessageDate;
    }

    public void setLatestMessageDate(Date latestMessageDate) {
        this.latestMessageDate = latestMessageDate;
    }

    public String getFriendlyLatestMessageDate() {
        return friendlyLatestMessageDate;
    }

    public void setFriendlyLatestMessageDate(String friendlyLatestMessageDate) {
        this.friendlyLatestMessageDate = friendlyLatestMessageDate;
    }

    public User getOtherUser() {
        return otherUser;
    }

    public void setOtherUser(User otherUser) {
        this.otherUser = otherUser;
    }
}
