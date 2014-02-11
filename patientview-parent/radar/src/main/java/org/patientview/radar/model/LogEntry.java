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

package org.patientview.radar.model;

import org.patientview.radar.model.user.User;
import org.patientview.radar.web.RadarSecuredSession;

import java.util.Date;

public class LogEntry extends BaseModel {

    public static final String LOGGED_ON = "RDR logon";
    public static final String PATIENT_ADD = "RDR patient add";
    public static final String PATIENT_DELETE = "RDR patient delete";
    public static final String ADMIN_ADD = "RDR admin add";

    private static final int MAX_TEXT_FIELD_SIZE = 65000;

    private int specialty = 1;

    private Date date = new Date();

    private String nhsno;

    private String user;

    private String action;

    private String actor;

    private String unitcode;

    private String extrainfo;

    public LogEntry() {
    }

    public LogEntry(String nhsno, String user, String action, String unitcode, String extrainfo) {
        this.action = action;
        this.nhsno = nhsno;
        this.user = user;
        User loginUser = RadarSecuredSession.get().getUser();
        if (loginUser != null) {
            this.actor = loginUser.getUsername();
        }
        setUnitcode(unitcode);
        setExtrainfo(extrainfo);
    }

    public int getSpecialty() {
        return specialty;
    }

    public void setSpecialty(int specialty) {
        this.specialty = specialty;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public void setExtrainfo(String extrainfo) {
        if (extrainfo == null) {
            return;
        } else if (extrainfo.length() > MAX_TEXT_FIELD_SIZE) {
            this.extrainfo = extrainfo.substring(0, MAX_TEXT_FIELD_SIZE);
        } else {
            this.extrainfo = extrainfo;
        }
    }
}
