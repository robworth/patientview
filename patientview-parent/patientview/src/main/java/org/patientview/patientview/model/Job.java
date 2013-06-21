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
import org.patientview.patientview.model.enums.SendEmailEnum;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Table(name = "pv_job")
public class Job extends BaseModel {

    @ManyToOne(optional = false)
    @JoinColumn(name = "message_id")
    private Message message;

    @ManyToOne(optional = false)
    @JoinColumn(name = "creator_id")
    private User creator;

    @ManyToOne(optional = false)
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SendEmailEnum status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GroupEnum groupEnum;

    @Column(nullable = false)
    private Date created;

    @Column(nullable = true)
    private Date started;

    @Column(nullable = true)
    private Date finished;

    @Min(value = 0)
    private long errorCount;

    @Transient
    @Column(nullable = false)
    private StringBuilder reports;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String report;

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public SendEmailEnum getStatus() {
        return status;
    }

    public void setStatus(SendEmailEnum status) {
        this.status = status;
    }

    public GroupEnum getGroupEnum() {
        return groupEnum;
    }

    public void setGroupEnum(GroupEnum groupEnum) {
        this.groupEnum = groupEnum;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public Date getFinished() {
        return finished;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
    }

    public long getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(long errorCount) {
        this.errorCount = errorCount;
    }

    public StringBuilder getReports() {
        return reports;
    }

    public void setReports(StringBuilder reports) {
        this.reports = reports;
    }

    public void addReport(String report) {
        if (reports == null) {
            reports = new StringBuilder();
        }
        reports.append(report);
        reports.append("\n");
    }

    public void addErrorCount() {
        errorCount++;
    }

    public void convertReports() {
        if (reports != null) {
            report = reports.toString();
        }
    }
}
