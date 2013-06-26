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

import org.patientview.patientview.model.enums.SendEmailEnum;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Table(name = "pv_emailqueue")
public class EmailQueue extends BaseModel {

    @ManyToOne(optional = false)
    @JoinColumn(name = "message_id")
    private Message message;

    @ManyToOne(optional = false)
    @JoinColumn(name = "recipient_id")
    private User recipient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SendEmailEnum status;

    @Column(nullable = false)
    private Date created;

    @Column(nullable = true)
    private Date finished;

    @Transient
    @Column(nullable = false)
    private StringBuilder reports;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String report;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public SendEmailEnum getStatus() {
        return status;
    }

    public void setStatus(SendEmailEnum status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getFinished() {
        return finished;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
    }

    public StringBuilder getReports() {
        return reports;
    }

    public void setReports(StringBuilder reports) {
        this.reports = reports;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public void addReport(String report) {
        if (reports == null) {
            reports = new StringBuilder();
        }
        reports.append(report);
        reports.append("\n");
    }

    public void convertReports() {
        if (reports != null) {
            report = reports.toString();
        }
    }
}
