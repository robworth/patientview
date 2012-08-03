package com.worthsoln.ibd.model.medication.enums;

import java.util.Arrays;
import java.util.List;

public enum MedicationFrequency {
    ONCE_A_DAY(1, "Once a day"),
    TWICE_A_DAY(2, "Twice a day"),
    THREE_A_DAY(3, "Three a day"),
    FOUR_A_DAY(4, "Four a day"),
    AT_NIGHT(5, "At night"),
    ONCE_A_WEEK(6, "Once a week"),
    EVERY_2_WEEKS(7, "Every 2 weeks"),
    EVERY_4_WEEKS(8, "Every 4 weeks"),
    EVERY_8_WEEKS(9, "Every 8 weeks");

    private long id;
    private String name;

    private MedicationFrequency(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static MedicationFrequency getMedicineFrequency(Long id) {
        for (MedicationFrequency medicineFrequency : MedicationFrequency.values()) {
            if (medicineFrequency.getId() == id) {
                return medicineFrequency;
            }
        }

        return null;
    }

    public static List<MedicationFrequency> getAsList() {
        return Arrays.asList(MedicationFrequency.values());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
