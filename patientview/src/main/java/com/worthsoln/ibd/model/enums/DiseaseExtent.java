package com.worthsoln.ibd.model.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum DiseaseExtent {
    PROCTITIS(1, "Proctitis"),
    LEFT_SIDED_COLITIS(2, "Left Sided colitis"),
    EXTENSIVE_COLITIS(3, "Extensive Colitis"),
    ILEAL_CROHNS(4, "Ileal Chrohn's"),
    ILEO_COLONIC_DISEASE(5, "Ileal-Colonic Disease"),
    CROHNS_COLITIS(6, "Crohn's Colitis"),
    ISOLATED_UPPER_GI_DISEASE(7, "Isolated Upper GI Disease");

    private long id;
    private String name;

    private DiseaseExtent(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static DiseaseExtent getDiseaseExtent(Long id) {
        for (DiseaseExtent diseaseExtent : DiseaseExtent.values()) {
            if (diseaseExtent.getId() == id) {
                return diseaseExtent;
            }
        }

        return null;
    }

    public static List<DiseaseExtent> getAsList() {
        return Arrays.asList(DiseaseExtent.values());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
