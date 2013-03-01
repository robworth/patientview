package com.worthsoln.ibd.model.enums;

import java.util.Arrays;
import java.util.List;

public enum VaccinationRecord {
    NOT_KNOWN(1, "Not known"),
    PREVIOUSLY_HISTORY_OF_CHICKEN_POX(2, "Previously history of Chicken Pox"),
    PREVIOUS_TB_VACCINATION(3, "Previous TB Vaccination (BCG)"),
    PREVIOUS_HEP_B_VACCINATION(4, "Previous Hep B Vaccination");

    private long id;
    private String name;

    private VaccinationRecord(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static VaccinationRecord getVaccinationRecord(Long id) {
        for (VaccinationRecord vaccinationRecord : VaccinationRecord.values()) {
            if (vaccinationRecord.getId() == id) {
                return vaccinationRecord;
            }
        }

        return NOT_KNOWN;
    }

    public static VaccinationRecord getVaccinationRecord(String name) {
        for (VaccinationRecord vaccinationRecord : VaccinationRecord.values()) {
            if (vaccinationRecord.getName().equals(name)) {
                return vaccinationRecord;
            }
        }

        return NOT_KNOWN;
    }

    public static List<VaccinationRecord> getAsList() {
        return Arrays.asList(VaccinationRecord.values());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
