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
import org.patientview.utils.XssUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity(name = "letter")
public class Letter extends BaseModel {

    @Column(nullable = false)
    private String nhsno;

    @Column(nullable = false)
    private String unitcode;

    @Column(nullable = true)
    private Calendar date;

    @Column(nullable = true)
    private String type;

    @Column(nullable = true)
    private String content;

    private static final int HASH_SEED = 31;

    public Letter() {
    }

    public Letter(Long id, String nhsno, String unitcode, Calendar date, String type, String content) {
        this.setId(id);
        this.nhsno = nhsno;
        setUnitcode(unitcode);
        this.content = content;
        this.date = date;
        this.type = type;
    }

    public Letter(String nhsno, String unitcode, Calendar date, String type, String content) {
        this.nhsno = nhsno;
        setUnitcode(unitcode);
        this.content = content;
        this.date = date;
        this.type = type;
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

    public String getContent() {
        return content;
    }

    /**
     * Will return the content with the carriage returns replace with <br />
     *
     * @return String
     */
    public String getFormattedContent() {
        if (content != null) {
            return XssUtils.encodeForHTML(content, new String[]{"&#xa;"});
        }

        return "";
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setStringDate(String dateString) {
        this.date = TimestampUtils.createTimestamp(dateString);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Letter letter = (Letter) o;

        if (!this.getId().equals(letter.getId())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        long result = this.getId();
        result = HASH_SEED * result + (nhsno != null ? nhsno.hashCode() : 0);
        result = HASH_SEED * result + (unitcode != null ? unitcode.hashCode() : 0);
        result = HASH_SEED * result + (date != null ? date.hashCode() : 0);
        result = HASH_SEED * result + (type != null ? type.hashCode() : 0);
        result = HASH_SEED * result + (content != null ? content.hashCode() : 0);
        return (int) result;
    }
}
