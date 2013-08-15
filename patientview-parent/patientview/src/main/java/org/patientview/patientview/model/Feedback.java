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

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Calendar;
import java.text.SimpleDateFormat;

@Entity
public class Feedback extends BaseModel {

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nhsno;

    @Column(nullable = false)
    private String unitcode;

    @Column(nullable = false)
    private Calendar datestamp;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = true)
    private String commentedited;

    @Column(nullable = false)
    private boolean anonymous;

    @Column(nullable = false)
    private boolean makepublic;

    public Feedback() {
    }

    public Feedback(Long id) {
        this.setId(id);
    }

    public Feedback(String username, String name, String nhsno, String unitcode, String comment, boolean anonymous) {
        setUsername(username);
        setName(name);
        setNhsno(nhsno);
        setUnitcode(unitcode);
        setComment(comment);
        setCommentedited(comment);
        setAnonymous(anonymous);
        this.datestamp = Calendar.getInstance();
        setMakepublic(false);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.unitcode = unitcode;
    }

    public Calendar getDatestamp() {
        return datestamp;
    }

    public void setDatestamp(Calendar datestamp) {
        this.datestamp = datestamp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentedited() {
        return commentedited;
    }

    public void setCommentedited(String commentedited) {
        this.commentedited = commentedited;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public boolean isMakepublic() {
        return makepublic;
    }

    public void setMakepublic(boolean makepublic) {
        this.makepublic = makepublic;
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
}
