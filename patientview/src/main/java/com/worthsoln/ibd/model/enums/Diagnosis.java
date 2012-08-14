package com.worthsoln.ibd.model.enums;

import com.worthsoln.ibd.Ibd;

import java.util.Arrays;
import java.util.List;

public enum Diagnosis {
    ULCERATIVE_COLITIS(1, "Ulcerative colitis", Ibd.ULCERATIVE_COLITIS_DIAGRAM),
    COLITIS_UNSPECIFIED(2, "Colitis unspecified", Ibd.COLITIS_UNSPECIFIED_DIAGRAM),
    CROHNS(3, "Crohn's", Ibd.CROHNS_DIAGRAM);

    private long id;
    private String name;
    private String diagram;

    private Diagnosis(long id, String name, String diagram) {
        this.id = id;
        this.name = name;
        this.diagram = diagram;
    }

    public static Diagnosis getDiagnosis(Long id) {
        for (Diagnosis diagnosis : Diagnosis.values()) {
            if (diagnosis.getId() == id) {
                return diagnosis;
            }
        }

        return null;
    }

    public static List<Diagnosis> getAsList() {
        return Arrays.asList(Diagnosis.values());
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
