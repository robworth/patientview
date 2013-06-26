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

import org.patientview.patientview.utils.TimestampUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity(name = "log")
public class LogEntry extends BaseModel {

    // NOTE: the specialty is optional here because so log events may occur outside a logged in context
    @ManyToOne(optional = true)
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    @Column(nullable = false)
    private Calendar date;

    @Column(nullable = true)
    private String nhsno;

    @Column(nullable = true)
    private String user;

    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private String actor;

    @Column(nullable = true)
    private String unitcode;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String extrainfo;

    public LogEntry() {
    }

    public LogEntry(String nhsno, String user, String action, String actor, String unitcode, String extrainfo) {
        this.action = action;
        this.nhsno = nhsno;
        this.user = user;
        this.actor = actor;
        setUnitcode(unitcode);
        this.extrainfo = extrainfo;
        this.date = Calendar.getInstance();
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setDate(String dateString) {
        this.date = TimestampUtils.createTimestamp(dateString);
    }

    public String getFormattedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
        if ((date.get(Calendar.HOUR_OF_DAY) == 0) && (date.get(Calendar.MINUTE) == 0)) {
            return dateFormat.format(date.getTime());
        } else {
            return dateTimeFormat.format(date.getTime());
        }
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = (unitcode != null) ? unitcode.toUpperCase() : unitcode;
    }

    public String getExtrainfo() {
        return extrainfo;
    }

    public String getExtrainfolessxmlfilename() {
        String extrainfoReturn = extrainfo;
        String xmlFilename = getXmlfilename();
        if (xmlFilename.length() != 0) {
            extrainfoReturn = extrainfo.substring(xmlFilename.length());
        }
        return extrainfoReturn;
    }

    public void setExtrainfo(String extrainfo) {
        this.extrainfo = extrainfo;
    }

    public String getXmlfilename() {
        String filename = "";

        if (extrainfo != null && !extrainfo.equals("")) {
            String suffix = ".xml";
            int endFilename = extrainfo.indexOf(suffix);

            if (endFilename != -1) {
                filename = extrainfo.substring(0, endFilename + suffix.length());
            }
        }

        return filename;
    }
}
