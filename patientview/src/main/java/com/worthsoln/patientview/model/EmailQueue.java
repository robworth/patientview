package com.worthsoln.patientview.model;

import com.worthsoln.patientview.model.enums.SendEmailEnum;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
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
}
