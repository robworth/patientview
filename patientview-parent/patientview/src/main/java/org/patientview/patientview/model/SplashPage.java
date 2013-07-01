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


import org.patientview.utils.XssUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SplashPage extends BaseModel {

    @ManyToOne(optional = false)
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean live;

    @Column(nullable = false)
    private String headline;

    @Column(nullable = false)
    private String bodytext;

    @Column(nullable = false)
    private String unitcode;

    private static final String IDENTIFIER = "splashPage";

    public SplashPage() {
    }

    public SplashPage(Long id) {
        this.setId(id);
    }

    public SplashPage(Long id, String name, boolean live, String headline, String bodytext, String unitcode) {
        this.setId(id);
        this.name = name;
        this.live = live;
        this.headline = headline;
        this.bodytext = bodytext;
        this.unitcode = unitcode;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getBodytext() {
        return bodytext;
    }

    public void setBodytext(String bodytext) {
        this.bodytext = bodytext;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public static final String getIdentifier() {
        return IDENTIFIER;
    }

     public String getBodyTextForHtml() {

        return XssUtils.encodeForHTML(getBodytext(), new String[]{"&#xd;&#xa;"});
    }
}
