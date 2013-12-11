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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Radar Utility - miscellaneous utility methods go here
 */
public class RadarUtility {

    private static final Logger LOGGER = LoggerFactory.getLogger(RadarUtility.class);

    private static final String DATE_FORMAT_0 = "dd-MM-yy";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATE_FORMAT_1 = "dd.MM.y";
    private static final String DATE_FORMAT_2 = "dd-MM-y";
    private static final String DATE_FORMAT_3 = "dd/MM/y";

    private static final String[] LENGTH_8_DATE_FORMATS = new String[]{DATE_FORMAT_0, DATE_FORMAT_1, DATE_FORMAT_2,
            DATE_FORMAT_3};
    private static final String[] LENGTH_10_DATE_FORMATS = new String[] {DATE_FORMAT};

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

    public static boolean isNhsNumberValid(String nhsNumber) {
        return isNhsNumberValid(nhsNumber, false);
    }

    public static boolean isNhsNumberValidWhenUppercaseLettersAreAllowed(String nhsNumber) {
        return isNhsNumberValid(nhsNumber, true);
    }

    private static boolean isNhsNumberValid(String nhsNumber, boolean ignoreUppercaseLetters) {

        // Remove all whitespace and non-visible characters such as tab, new line etc
        nhsNumber = nhsNumber.replaceAll("\\s", "");

        // Only permit 10 characters
        if (nhsNumber.length() != 10) {
            return false;
        }

        boolean nhsNoContainsOnlyNumbers = nhsNumber.matches("[0-9]+");
        boolean nhsNoContainsLowercaseLetters = !nhsNumber.equals(nhsNumber.toUpperCase());

        if (!nhsNoContainsOnlyNumbers && ignoreUppercaseLetters && !nhsNoContainsLowercaseLetters) {
            return true;
        }

        return isNhsChecksumValid(nhsNumber);
    }

    private static boolean isNhsChecksumValid(String nhsNumber) {
        /**
         * Generate the checksum using modulus 11 algorithm
         */
        int checksum = 0;

        try {
            // Multiply each of the first 9 digits by 10-character position (where the left character is in position 0)
            for (int i = 0; i <= 8; i++) {
                int value = Integer.parseInt(nhsNumber.charAt(i) + "") * (10 - i);
                checksum += value;
            }

            //(modulus 11)
            checksum = 11 - checksum % 11;

            if (checksum == 11) {
                checksum = 0;
            }

            // Does checksum match the 10th digit?
            if (checksum == Integer.parseInt(nhsNumber.charAt(9) + "")) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false; // nhsNumber contains letters
        }
    }

    /**
     * Class to return the date from the database text field representation of a date.
     *
     *
     * @param dateField
     * @return
     */
    public static Date parseDate(String dateField) {

        if (StringUtils.hasText(dateField)) {

            // select the dat mask of the length of the field
            String[] dataFormats;
            if (dateField.length() == 8) {
                dataFormats = LENGTH_8_DATE_FORMATS;
            } else {
                dataFormats = LENGTH_10_DATE_FORMATS;
            }

            Date dateOfBirth = null;
            // It seems that the strings in the DB have different date formats, nice.
            for (String dateFormat : dataFormats) {
                try {
                    dateOfBirth = new SimpleDateFormat(dateFormat).parse(dateField);
                    break;
                } catch (ParseException e) {
                    LOGGER.debug("Could not parse date of birth {}", dateField);
                }
            }

            return dateOfBirth;
        }

        return null;
    }



}
