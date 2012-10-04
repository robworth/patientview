package com.worthsoln.ibd.model.enums;

import com.worthsoln.ibd.Ibd;

import java.util.Arrays;
import java.util.List;

public enum DiseaseExtent {
    PROCTITIS(1, "Proctitis", Ibd.PROCTITIS_DIAGRAM),
    LEFT_SIDED_COLITIS(2, "Left Sided colitis", Ibd.LEFT_SIDED_COLITIS_DIAGRAM),
    EXTENSIVE_COLITIS(3, "Extensive Colitis", Ibd.EXTENSIVE_COLITIS_DIAGRAM),
    ILEAL_CROHNS(4, "Ileal Crohn's", Ibd.ILEAL_CROHNS_DIAGRAM),
    ILEO_COLONIC_DISEASE(5, "Ileo-Colonic Disease", Ibd.ILEO_COLONIC_DISEASE_DIAGRAM),
    CROHNS_COLITIS(6, "Crohn's Colitis", Ibd.CROHNS_COLITIS_DIAGRAM),
    ISOLATED_UPPER_GI_DISEASE(7, "Isolated Upper GI Disease", Ibd.ISOLATED_UPPER_GI_DISEASE_DIAGRAM);

    private long id;
    private String name;
    private String diagram;

    private DiseaseExtent(long id, String name, String diagram) {
        this.id = id;
        this.name = name;
        this.diagram = diagram;
    }

    public static DiseaseExtent getDiseaseExtent(Long id) {
        for (DiseaseExtent diseaseExtent : DiseaseExtent.values()) {
            if (diseaseExtent.getId() == id) {
                return diseaseExtent;
            }
        }

        return null;
    }

    public static DiseaseExtent getDiseaseExtent(String name) {
        for (DiseaseExtent diseaseExtent : DiseaseExtent.values()) {
            if (diseaseExtent.getName() == name) {
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

    public String getDiagram() {
        return diagram;
    }
}
