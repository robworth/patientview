package org.patientview.radar.service.impl;

import org.patientview.radar.dao.DemographicsDao;
import org.patientview.radar.model.Centre;
import org.patientview.radar.model.Demographics;
import org.patientview.radar.model.Sex;
import org.patientview.radar.model.Status;
import org.patientview.radar.model.filter.DemographicsFilter;
import org.patientview.radar.service.DemographicsManager;

import java.util.List;

public class DemographicsManagerImpl implements DemographicsManager {

    private DemographicsDao demographicsDao;

    public void saveDemographics(Demographics demographics) {
        // Save or update the demographics object
        demographicsDao.saveDemographics(demographics);
    }

    public Demographics getDemographicsByRadarNumber(long radarNumber) {
        return demographicsDao.getDemographicsByRadarNumber(radarNumber);
    }

    public List<Demographics> getDemographicsByRenalUnit(Centre centre) {
        return demographicsDao.getDemographicsByRenalUnit(centre);
    }

    public List<Demographics> getDemographics() {
        return getDemographics(new DemographicsFilter(), -1, -1);
    }

    public List<Demographics> getDemographics(DemographicsFilter filter) {
        return getDemographics(filter, -1, -1);
    }

    public List<Demographics> getDemographics(DemographicsFilter filter, int page, int numberPerPage) {
        return demographicsDao.getDemographics(filter, page, numberPerPage);
    }

    public Sex getSex(long id) {
        return demographicsDao.getSex(id);
    }

    public List<Sex> getSexes() {
        return demographicsDao.getSexes();
    }

    public Status getStatus(long id) {
        return demographicsDao.getStatus(id);
    }

    public List<Status> getStatuses() {
        return demographicsDao.getStatuses();
    }

    public DemographicsDao getDemographicsDao() {
        return demographicsDao;
    }

    public boolean isNhsNumberValid(String nhsNumber) {
        return isNhsNumberValid(nhsNumber, false);
    }

    public boolean isNhsNumberValidWhenUppercaseLettersAreAllowed(String nhsNumber) {
        return isNhsNumberValid(nhsNumber, true);
    }

    private boolean isNhsNumberValid(String nhsNumber, boolean ignoreUppercaseLetters) {

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

    private boolean isNhsChecksumValid(String nhsNumber) {
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

    public void setDemographicsDao(DemographicsDao demographicsDao) {
        this.demographicsDao = demographicsDao;
    }

}
