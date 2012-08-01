package com.worthsoln.ibd.model.enums;

import java.util.Arrays;
import java.util.List;

public enum Diagnosis {
    ULCERATIVE_COLITIS(1, "Ulcerative colitis"),
    COLITIS_UNSPECIFIED(2, "Colitis unspecified"),
    CROHNS(3, "Chrohn's");

    private long id;
    private String name;

    private Diagnosis(long id, String name) {
        this.id = id;
        this.name = name;
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
