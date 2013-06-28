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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EdtaCode extends BaseModel {

    @Column(nullable = false, unique = true)
    private String edtaCode;

    @ManyToOne(optional = false)
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    @Column(nullable = true)
    private String linkType;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private String medicalLink01;

    @Column(nullable = true)
    private String medicalLink02;

    @Column(nullable = true)
    private String medicalLink03;

    @Column(nullable = true)
    private String medicalLink04;

    @Column(nullable = true)
    private String medicalLink05;

    @Column(nullable = true)
    private String medicalLink06;

    @Column(nullable = true)
    private String medicalLinkText01;

    @Column(nullable = true)
    private String medicalLinkText02;

    @Column(nullable = true)
    private String medicalLinkText03;

    @Column(nullable = true)
    private String medicalLinkText04;

    @Column(nullable = true)
    private String medicalLinkText05;

    @Column(nullable = true)
    private String medicalLinkText06;

    @Column(nullable = true)
    private String patientLink01;

    @Column(nullable = true)
    private String patientLink02;

    @Column(nullable = true)
    private String patientLink03;

    @Column(nullable = true)
    private String patientLink04;

    @Column(nullable = true)
    private String patientLink05;

    @Column(nullable = true)
    private String patientLink06;

    @Column(nullable = true)
    private String patientLinkText01;

    @Column(nullable = true)
    private String patientLinkText02;

    @Column(nullable = true)
    private String patientLinkText03;

    @Column(nullable = true)
    private String patientLinkText04;

    @Column(nullable = true)
    private String patientLinkText05;

    @Column(nullable = true)
    private String patientLinkText06;

    private static final String IDENTIFIER = "edtaCode";

    public EdtaCode() {
    }

    public EdtaCode(String edtaCode) {
        this.edtaCode = edtaCode;
    }

    public EdtaCode(String edtaCode, Specialty specialty, String linkType, String description,
                    String medicalLink01, String medicalLink02, String medicalLink03,
                    String medicalLink04, String medicalLink05, String medicalLink06,
                    String medicalLinkText01, String medicalLinkText02, String medicalLinkText03,
                    String medicalLinkText04, String medicalLinkText05, String medicalLinkText06,
                    String patientLink01, String patientLink02, String patientLink03,
                    String patientLink04, String patientLink05, String patientLink06,
                    String patientLinkText01, String patientLinkText02, String patientLinkText03,
                    String patientLinkText04, String patientLinkText05, String patientLinkText06) {
        this.description = description;
        this.specialty = specialty;
        this.edtaCode = edtaCode;
        this.linkType = linkType;
        this.medicalLink01 = medicalLink01;
        this.medicalLink02 = medicalLink02;
        this.medicalLink03 = medicalLink03;
        this.medicalLink04 = medicalLink04;
        this.medicalLink05 = medicalLink05;
        this.medicalLink06 = medicalLink06;
        this.medicalLinkText01 = medicalLinkText01;
        this.medicalLinkText02 = medicalLinkText02;
        this.medicalLinkText03 = medicalLinkText03;
        this.medicalLinkText04 = medicalLinkText04;
        this.medicalLinkText05 = medicalLinkText05;
        this.medicalLinkText06 = medicalLinkText06;
        this.patientLink01 = patientLink01;
        this.patientLink02 = patientLink02;
        this.patientLink03 = patientLink03;
        this.patientLink04 = patientLink04;
        this.patientLink05 = patientLink05;
        this.patientLink06 = patientLink06;
        this.patientLinkText01 = patientLinkText01;
        this.patientLinkText02 = patientLinkText02;
        this.patientLinkText03 = patientLinkText03;
        this.patientLinkText04 = patientLinkText04;
        this.patientLinkText05 = patientLinkText05;
        this.patientLinkText06 = patientLinkText06;
    }

    public static final String getIdentifier() {
        return IDENTIFIER;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public String getMedicalLinkText01() {
        return medicalLinkText01;
    }

    public void setMedicalLinkText01(String medicalLinkText01) {
        this.medicalLinkText01 = medicalLinkText01;
    }

    public String getMedicalLinkText02() {
        return medicalLinkText02;
    }

    public void setMedicalLinkText02(String medicalLinkText02) {
        this.medicalLinkText02 = medicalLinkText02;
    }

    public String getMedicalLinkText03() {
        return medicalLinkText03;
    }

    public void setMedicalLinkText03(String medicalLinkText03) {
        this.medicalLinkText03 = medicalLinkText03;
    }

    public String getMedicalLinkText04() {
        return medicalLinkText04;
    }

    public void setMedicalLinkText04(String medicalLinkText04) {
        this.medicalLinkText04 = medicalLinkText04;
    }

    public String getMedicalLinkText05() {
        return medicalLinkText05;
    }

    public void setMedicalLinkText05(String medicalLinkText05) {
        this.medicalLinkText05 = medicalLinkText05;
    }

    public String getMedicalLinkText06() {
        return medicalLinkText06;
    }

    public void setMedicalLinkText06(String medicalLinkText06) {
        this.medicalLinkText06 = medicalLinkText06;
    }

    public String getPatientLinkText01() {
        return patientLinkText01;
    }

    public void setPatientLinkText01(String patientLinkText01) {
        this.patientLinkText01 = patientLinkText01;
    }

    public String getPatientLinkText02() {
        return patientLinkText02;
    }

    public void setPatientLinkText02(String patientLinkText02) {
        this.patientLinkText02 = patientLinkText02;
    }

    public String getPatientLinkText03() {
        return patientLinkText03;
    }

    public void setPatientLinkText03(String patientLinkText03) {
        this.patientLinkText03 = patientLinkText03;
    }

    public String getPatientLinkText04() {
        return patientLinkText04;
    }

    public void setPatientLinkText04(String patientLinkText04) {
        this.patientLinkText04 = patientLinkText04;
    }

    public String getPatientLinkText05() {
        return patientLinkText05;
    }

    public void setPatientLinkText05(String patientLinkText05) {
        this.patientLinkText05 = patientLinkText05;
    }

    public String getPatientLinkText06() {
        return patientLinkText06;
    }

    public void setPatientLinkText06(String patientLinkText06) {
        this.patientLinkText06 = patientLinkText06;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEdtaCode() {
        return edtaCode;
    }

    public void setEdtaCode(String edtaCode) {
        this.edtaCode = edtaCode;
    }

    public String getMedicalLink01() {
        return medicalLink01;
    }

    public void setMedicalLink01(String medicalLink01) {
        this.medicalLink01 = medicalLink01;
    }

    public String getMedicalLink02() {
        return medicalLink02;
    }

    public void setMedicalLink02(String medicalLink02) {
        this.medicalLink02 = medicalLink02;
    }

    public String getMedicalLink03() {
        return medicalLink03;
    }

    public void setMedicalLink03(String medicalLink03) {
        this.medicalLink03 = medicalLink03;
    }

    public String getMedicalLink04() {
        return medicalLink04;
    }

    public void setMedicalLink04(String medicalLink04) {
        this.medicalLink04 = medicalLink04;
    }

    public String getMedicalLink05() {
        return medicalLink05;
    }

    public void setMedicalLink05(String medicalLink05) {
        this.medicalLink05 = medicalLink05;
    }

    public String getMedicalLink06() {
        return medicalLink06;
    }

    public void setMedicalLink06(String medicalLink06) {
        this.medicalLink06 = medicalLink06;
    }

    public String getPatientLink01() {
        return patientLink01;
    }

    public void setPatientLink01(String patientLink01) {
        this.patientLink01 = patientLink01;
    }

    public String getPatientLink02() {
        return patientLink02;
    }

    public void setPatientLink02(String patientLink02) {
        this.patientLink02 = patientLink02;
    }

    public String getPatientLink03() {
        return patientLink03;
    }

    public void setPatientLink03(String patientLink03) {
        this.patientLink03 = patientLink03;
    }

    public String getPatientLink04() {
        return patientLink04;
    }

    public void setPatientLink04(String patientLink04) {
        this.patientLink04 = patientLink04;
    }

    public String getPatientLink05() {
        return patientLink05;
    }

    public void setPatientLink05(String patientLink05) {
        this.patientLink05 = patientLink05;
    }

    public String getPatientLink06() {
        return patientLink06;
    }

    public void setPatientLink06(String patientLink06) {
        this.patientLink06 = patientLink06;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }
}
