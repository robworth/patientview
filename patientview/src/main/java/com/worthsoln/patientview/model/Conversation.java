package com.worthsoln.patientview.model;

import com.worthsoln.patientview.model.enums.GroupEnum;

import javax.persistence.*;
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

    @Column(nullable = false, columnDefinition = "TEXT")
    private String subject;

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

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private GroupEnum groupEnum;

    @Column(nullable = true)
    private String type;

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    public GroupEnum getGroupEnum() {
        return groupEnum;
    }

    public String getType() {
        return type;
    }

    public void setGroupEnum(GroupEnum groupEnum) {
        this.groupEnum = groupEnum;
    }

    public void setType(String type) {
        this.type = type;
    }
}
