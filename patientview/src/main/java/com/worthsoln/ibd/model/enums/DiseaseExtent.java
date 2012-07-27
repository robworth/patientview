package com.worthsoln.ibd.model.enums;

public enum DiseaseExtent {
    PROCTITIS(1, "Proctitis"),
    LEFT_SIDED_COLITIS(2, "Left Sided colitis"),
    EXTENSIVE_COLITIS(3, "Extensive Colitis"),
    ILEAL_CROHNS(4, "Ileal Chrohn%27s"),
    ILEO_COLONIC_DISEASE(5, "Ileal%2DColonic Disease"),
    CROHNS_COLITIS(6, "Crohn%27s Colitis"),
    ISOLATED_UPPER_GI_DISEASE(7, "Isolated Upper GI Disease");

    private int id;
    private String name;

    private DiseaseExtent(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static DiseaseExtent getDiseaseExtent(long id) {
        for (DiseaseExtent diseaseExtent : DiseaseExtent.values()) {
            if (diseaseExtent.getId() == id) {
                return diseaseExtent;
            }
        }

        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
