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

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.patientview.patientview.utils.TimestampUtils;
import org.patientview.utils.XssUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class News extends BaseModel {

    @ManyToOne(optional = false)
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    @Column(nullable = false)
    private Calendar datestamp;

    @Column(nullable = false)
    private String unitcode;

    @Column(nullable = false)
    private boolean admin;

    @Column(nullable = false)
    private boolean patient;

    @Column(nullable = false)
    private boolean everyone;

    @Column(nullable = false)
    private String headline;

    @Column(nullable = false)
    private String body;

    public News() {
    }

    public News(Long id) {
        this.setId(id);
    }

    public News(String unitcode, boolean admin, boolean patient, boolean everyone, String headline, String body) {
        this.datestamp = Calendar.getInstance();
        setUnitcode(unitcode);
        this.admin = admin;
        this.patient = patient;
        this.everyone = everyone;
        this.headline = headline;
        this.body = body;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Calendar getDatestamp() {
        return datestamp;
    }

    public void setDatestamp(Calendar date) {
        this.datestamp = date;
    }

    public void setDatestamp(String dateString) {
        this.datestamp = TimestampUtils.createTimestamp(dateString);
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = (unitcode != null) ? unitcode.toUpperCase() : unitcode;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isPatient() {
        return patient;
    }

    public void setPatient(boolean patient) {
        this.patient = patient;
    }

    public boolean isEveryone() {
        return everyone;
    }

    public void setEveryone(boolean everyone) {
        this.everyone = everyone;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFormattedDatestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
        if ((datestamp.get(Calendar.HOUR_OF_DAY) == 0) && (datestamp.get(Calendar.MINUTE) == 0)) {
            return dateFormat.format(datestamp.getTime());
        } else {
            return dateTimeFormat.format(datestamp.getTime());
        }
    }

    public String getBodyForHtml() {
        return XssUtils.encodeForHTML(getBody(), new String[] {"&#xd;&#xa;" });
    }
}
