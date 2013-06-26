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
import java.sql.Timestamp;

import org.patientview.patientview.utils.TimestampUtils;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "medicine")
public class Medicine extends BaseModel {

    @Column(nullable = false)
    private String nhsno;

    @Column(nullable = false)
    private String unitcode;

    @Column(nullable = true)
    private Calendar startdate;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String dose;

    public Medicine() {
    }

    public Medicine(Long id, String nhsno, String unitcode, Calendar startdate, String name, String dose) {
        this.setId(id);
        this.nhsno = nhsno;
        setUnitcode(unitcode);
        this.dose = dose;
        this.startdate = startdate;
        this.name = name;
    }

    public void setId(String id) {
        this.setId(Long.decode(id));
    }

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = (unitcode != null) ? unitcode.toUpperCase() : unitcode;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public Calendar getStartdate() {
        return startdate;
    }

    public void setStartdate(Calendar startdate) {
        this.startdate = startdate;
    }

    public void setStartdate(Timestamp datestamped) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(datestamped.getTime());
        this.startdate = cal;
    }

    public void setStartdate(String dateString) {
        this.startdate = TimestampUtils.createTimestamp(dateString);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormattedStartDate() {
        SimpleDateFormat dateWithoutHourAndMinutes = new SimpleDateFormat("dd/MM/yy");
        SimpleDateFormat dateWithHourAndMinutes = new SimpleDateFormat("dd/MM/yy HH:mm");

        String formattedStartDate = "";

        if (startdate != null) {
            /**
             * if hour and minute information exist, parse it as well.
             */
            if ((startdate.get(Calendar.HOUR_OF_DAY) == 0) && (startdate.get(Calendar.MINUTE) == 0)) {
                formattedStartDate = dateWithoutHourAndMinutes.format(startdate.getTime());
            } else {
                formattedStartDate = dateWithHourAndMinutes.format(startdate.getTime());
            }
        }

        return formattedStartDate;
    }

}
