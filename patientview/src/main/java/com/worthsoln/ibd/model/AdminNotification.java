package com.worthsoln.ibd.model;


import com.worthsoln.patientview.model.enums.XmlImportNotification;
import com.worthsoln.patientview.model.BaseModel;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "pv_admin_notification")
public class AdminNotification extends BaseModel {

    @Transient
    private XmlImportNotification xmlImportNotification;

    @Column(nullable = false)
    private String email;

    @Access(AccessType.PROPERTY)
    @Column(name = "notification_id", nullable = false)
    public long getXmlImportNotificationId() {
        if (xmlImportNotification != null) {
            return xmlImportNotification.getId();
        }

        return -1;
    }

    public void setXmlImportNotificationId(Long id) {
        this.xmlImportNotification = XmlImportNotification.getXmlImportNotification(id);
    }

    public XmlImportNotification getXmlImportNotification() {
        return xmlImportNotification;
    }

    public void setXmlImportNotification(XmlImportNotification xmlImportNotification) {
        this.xmlImportNotification = xmlImportNotification;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
