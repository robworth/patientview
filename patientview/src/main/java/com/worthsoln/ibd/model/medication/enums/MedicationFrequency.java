package com.worthsoln.ibd.model.medication.enums;

import java.util.Arrays;
import java.util.List;

public enum MedicationFrequency {
    ONCE_A_DAY(1, "Once a day"),
    ONCE_A_DAY_IN_THE_MORNING(2, "Once a day in the morning"),
    TWICE_A_DAY(3, "Twice a day"),
    THREE_A_DAY(4, "Three a day"),
    FOUR_A_DAY(5, "Four a day"),
    AT_NIGHT(6, "At night"),
    ONCE_A_WEEK(7, "Once a week"),
    ONCE_A_WEEK_BY_TABLET(8, "Once a week by tablet"),
    ONCE_A_WEEK_BY_INJECTION(9, "Once a week by injection"),
    EVERY_2_WEEKS(10, "Every 2 weeks"),
    EVERY_4_WEEKS(11, "Every 4 weeks"),
    EVERY_8_WEEKS(12, "Every 8 weeks"),
    EVERY_3_MONTHS(13, "Every 3 months"),
    AS_REQUIRED(14, "As required"),
    NA(15, "N/A");

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
