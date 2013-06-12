package com.solidstategroup.radar.model;

import com.solidstategroup.radar.model.enums.XmlImportNotification;

public class AdminNotification extends BaseModel {

    private String email;
    private XmlImportNotification xmlImportNotification;

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
