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

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Comment extends BaseModel {

    @Column(nullable = false)
    private Calendar datestamp;

    @Column(nullable = false)
    private String nhsno;

    @Column(nullable = false)
    private String body;

    public Comment() {
    }

    public Comment(Long id) {
        this.setId(id);
    }

    public Comment(String nhsno, String body) {
        this.datestamp = Calendar.getInstance();
        setNhsno(nhsno);
        setBody(body);
    }

    public Comment(Calendar datestamp, String nhsno, String body) {
        this.datestamp = datestamp;
        setNhsno(nhsno);
        setBody(body);
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

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
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

    public String getIsoFormattedDatestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return dateFormat.format(datestamp.getTime());
    }

    public String getBodyForHtml() {
        return getBody().replaceAll("\r\n", "<br />");
    }
}
