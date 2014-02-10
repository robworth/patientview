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

package org.patientview.radar.util;

import com.Ostermiller.util.RandPass;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.patientview.model.Patient;
import org.patientview.model.generic.DiseaseGroup;
import org.patientview.radar.web.pages.BasePage;
import org.patientview.radar.web.pages.patient.GenericPatientPage;
import org.patientview.radar.web.pages.patient.alport.AlportPatientPage;
import org.patientview.radar.web.pages.patient.hnf1b.HNF1BPatientPage;
import org.patientview.radar.web.pages.patient.srns.SrnsPatientPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/**
 * Radar Utility - miscellaneous utility methods go here
 */
public class RadarUtility {

    private static final Logger LOGGER = LoggerFactory.getLogger(RadarUtility.class);
    private static Properties properties;

    static {
        properties = new Properties();
        InputStream inputStream = RadarUtility.class.getClassLoader().getResourceAsStream("radar.properties");
        try {
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException io) {
            LOGGER.error("Properties not loaded");
        }
    }

    public static String getProperty(String name) {
        return properties.getProperty(name);
    }

    /**
     * @param event1Start cannot be null
     * @param event2Start cannot be null
     * @return true if there is an overlap
     */
    public static boolean isEventsOverlapping(Date event1Start, Date event1End, Date event2Start, Date event2End) {
        if (event1End == null) {
            if (event2End == null) {
                if (event1Start.compareTo(event2Start) == 0) {
                    return true;
                }
            } else {
                if (event1Start.compareTo(event2Start) >= 0 &&
                        event1Start.compareTo(event2End) < 1) {
                    return true;
                }
            }
        } else {
            if (event2Start.compareTo(event1Start) >= 0 &&
                    event2Start.compareTo(event1End) < 1) {
                return true;
            }
            if (event2End != null) {
                if (event1Start.compareTo(event2Start) >= 0 && event1Start.compareTo(event2End) < 1 ||
                        event1End.compareTo(event2Start) >= 0 && event1End.compareTo(event2End) < 1 ||
                        event2Start.compareTo(event1Start) >= 0 && event2Start.compareTo(event1End) < 1 ||
                        event2End.compareTo(event1Start) >= 0 && event2End.compareTo(event1End) < 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * returns a user friendly label for an enum for example PART1_PART2 will return Part1 Part2
     */
    public static String getLabelFromEnum(String enumString) {
        String label = enumString;
        label = label.replace("_", " ");
        String[] parts = label.split(" ");
        label = "";
        for (String part : parts) {
            String formatted = part.toLowerCase();
            formatted = Character.toUpperCase(formatted.charAt(0)) + formatted.substring(1);
            label += formatted;
            label += " ";
        }
        return label;
    }

    public static String hashPassword(String password) {
        return DigestUtils.sha256Hex(password);
    }

    public static String generateNewPassword() {
        return new RandPass(RandPass.NONCONFUSING_ALPHABET).getPass(8);
    }

    // Merge the two records together, source record taking priority on certain fields.
    // Done by getting the source record and just adding any radar stuff in it if it's found
    public static Patient mergePatientRecords(Patient source, Patient link) {

        Patient mergedPatient = new Patient();

        BeanUtils.copyProperties(source, mergedPatient);

        // Properties to mock the source object into the linked one
        mergedPatient.setId(link.getId());
        mergedPatient.setUnitcode(link.getUnitcode());
        mergedPatient.setNhsno(link.getNhsno());

        // Properties overridden has radar data
        if (StringUtils.hasText(link.getSurnameAlias())) {
            mergedPatient.setSurnameAlias(link.getSurnameAlias());
        }

        if (StringUtils.hasText(link.getEthnicGp())) {
            mergedPatient.setEthnicGp(link.getEthnicGp());
        }

        if (StringUtils.hasText(link.getTelephone2())){
            mergedPatient.setTelephone2(link.getTelephone2());
        }

        if (StringUtils.hasText(link.getMobile())) {
            mergedPatient.setMobile(link.getMobile());
        }

        if (link.getRrtModality() != null) {
            mergedPatient.setRrtModality(link.getRrtModality());
        }

        if (StringUtils.hasText(link.getDiagnosis())) {
            mergedPatient.setDiagnosis(link.getDiagnosis());
        }

        if (link.getDiagnosisDate() != null) {
            mergedPatient.setDiagnosisDate(link.getDiagnosisDate());
        }

        if (link.getOtherClinicianAndContactInfo() != null) {
            mergedPatient.setOtherClinicianAndContactInfo(link.getOtherClinicianAndContactInfo());
        }

        if (StringUtils.hasText(link.getComments())) {
            mergedPatient.setComments(link.getComments());
        }

        return mergedPatient;
    }


    public static void cleanLinkRecord(Patient patient) {
        patient.setSurname(null);
        patient.setForename(null);
        patient.setDob(null);
        patient.setDateofbirth(null);
        patient.setAddress1(null);
        patient.setAddress2(null);
        patient.setAddress3(null);
        patient.setAddress4(null);
        patient.setPostcode(null);
        patient.setSex(null);
        patient.setTelephone1(null);
        patient.setHospitalnumber(null);
    }


    public static BasePage getDiseasePage(Patient patient, PageParameters pageParameters){

        if (patient.getDiseaseGroup() != null) {

            if (patient.getDiseaseGroup().getId().equals(DiseaseGroup.SRNS_DISEASE_GROUP_ID) ||
                    patient.getDiseaseGroup().getId().
                            equals(DiseaseGroup.MPGN_DISEASEGROUP_ID)) {
                return new SrnsPatientPage(patient);
            } else if (patient.getDiseaseGroup().getId().equals(DiseaseGroup.ALPORT_DISEASEGROUP_ID)) {
                return new AlportPatientPage(patient, pageParameters);
            } else if (patient.getDiseaseGroup().getId().equals(DiseaseGroup.HNF1B_DISEASEGROUP_ID)) {
                return new HNF1BPatientPage(patient, pageParameters);
            } else {
                return new GenericPatientPage(patient, pageParameters);
            }
        }  else {
            return new GenericPatientPage(patient, pageParameters);
        }

    }






}
