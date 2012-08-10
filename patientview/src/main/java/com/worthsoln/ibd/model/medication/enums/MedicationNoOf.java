package com.worthsoln.ibd.model.medication.enums;

import java.util.Arrays;
import java.util.List;

public enum MedicationNoOf {
    ONE_TO_TWO(1, "1-2"),
    ONE_TO_FOUR(2, "1-4"),
    ONE_TO_SIX(3, "1-6"),
    NA(4, "N/A");

    private long id;
    private String name;

    private MedicationNoOf(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static MedicationNoOf getMedicineNoOf(Long id) {
        for (MedicationNoOf medicineNoOf : MedicationNoOf.values()) {
            if (medicineNoOf.getId() == id) {
                return medicineNoOf;
            }
        }

        return null;
    }

    public static List<MedicationNoOf> getAsList() {
        return Arrays.asList(MedicationNoOf.values());
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
