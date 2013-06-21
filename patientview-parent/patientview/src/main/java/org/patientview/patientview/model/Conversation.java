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

import org.patientview.patientview.model.enums.GroupEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
