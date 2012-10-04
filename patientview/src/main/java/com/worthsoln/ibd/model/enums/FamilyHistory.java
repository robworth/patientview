package com.worthsoln.ibd.model.enums;

import java.util.Arrays;
import java.util.List;

public enum FamilyHistory {
    NONE(1, "None"),
    CROHNS_DISEASE(2, "Crohn's Disease"),
    ULCERATIVE_COLITIS(3, "Ulcerative Colitis"),
    BOWEL_CANCER(4, "Bowel Cancer");

    private long id;
    private String name;

    private FamilyHistory(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static FamilyHistory getFamilyHistory(Long id) {
        for (FamilyHistory familyHistory : FamilyHistory.values()) {
            if (familyHistory.getId() == id) {
                return familyHistory;
            }
        }

        return NONE;
    }

    public static FamilyHistory getFamilyHistory(String name) {
            for (FamilyHistory familyHistory : FamilyHistory.values()) {
                if (familyHistory.getName() == name) {
                    return familyHistory;
                }
            }

            return NONE;
        }

    public static List<FamilyHistory> getAsList() {
        return Arrays.asList(FamilyHistory.values());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
