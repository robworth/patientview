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

@Entity
public class Aboutme extends BaseModel {

    @Column(nullable = false)
    private String nhsno;

    @Column(nullable = true)
    private String aboutme;

    @Column(nullable = true)
    private String talkabout;

    public Aboutme() {
    }

    public Aboutme(String nhsno, String aboutme, String talkabout) {
        this.nhsno = nhsno;
        this.aboutme = aboutme;
        this.talkabout = talkabout;
    }

    public Aboutme(Long id, String nhsno, String aboutme, String talkabout) {
        this.setId(id);
        this.nhsno = nhsno;
        this.talkabout = talkabout;
        this.aboutme = aboutme;
    }

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public String getAboutme() {
        return aboutme;
    }

    public void setAboutme(String aboutme) {
        this.aboutme = aboutme;
    }

    public String getTalkabout() {
        return talkabout;
    }

    public void setTalkabout(String talkabout) {
        this.talkabout = talkabout;
    }
}
