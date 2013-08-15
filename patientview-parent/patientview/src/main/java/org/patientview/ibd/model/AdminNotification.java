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

package org.patientview.ibd.model;


import org.patientview.patientview.model.enums.XmlImportNotification;
import org.patientview.patientview.model.BaseModel;

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
