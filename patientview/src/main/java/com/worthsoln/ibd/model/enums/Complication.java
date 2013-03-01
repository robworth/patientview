package com.worthsoln.ibd.model.enums;

import java.util.Arrays;
import java.util.List;

public enum Complication {
    NONE(1, "None"),
    PERI_ANAL_DISEASE(2, "Peri-anal disease"),
    PERFORATING_DISEASE_FISTULATION(3, "Perforating Disease / Fistulation"),
    SMALL_BOWEL_STRICTURING(4, "Small bowel stricturing"),
    LARGE_BOWEL_STRICTURING(5, "Large bowel stricturing"),
    ABSCESS(6, "Abscess"),
    GASTRO_DUODENAL_CROHNS(7, "Gasto-duodenal Crohn's"),
    ORAL_CROHNS(8, "Oral Crohn's"),
    LOW_GRADE_COLONIC_DYSPLASIA(9, "Low grade colonic dysplasia"),
    HIGH_GRADE_COLONIC_DYSPLASIA(10, "High grade colonic dysplasia"),
    INTESTINAL_FAILURE(11, "Intestinal failure"),
    OSTEOPAENIA(12, "Osteopaenia"),
    OSTEOPORSOSIS(13, "Osteoporsosis"),
    IRON_DEFICIENCY(14, "Iron deficiency"),
    PROXIMAL_CONSTIPATION(15, "Proximal constipation");

    private long id;
    private String name;

    private Complication(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Complication getComplication(Long id) {
        for (Complication complication : Complication.values()) {
            if (complication.getId() == id) {
                return complication;
            }
        }

        return NONE;
    }

    public static Complication getComplication(String name) {
            for (Complication complication : Complication.values()) {
                if (complication.getName() == name) {
                    return complication;
                }
            }

            return NONE;
        }

    public static List<Complication> getAsList() {
        return Arrays.asList(Complication.values());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
