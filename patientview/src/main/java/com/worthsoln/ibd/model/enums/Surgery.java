package com.worthsoln.ibd.model.enums;

import java.util.Arrays;
import java.util.List;

public enum Surgery {
    NO_PREVIOUS_OPERATIONS(1, "No previous operations"),
    PREVIOUS_APPENDECTOMY(2, "Previous appendectomy"),
    SETON_PLACEMENT(3, "Seton placement"),
    ABSCESS_INICISION_AND_DRAINAGE(4, "Abscess Incision and Drainage"),
    DOUBLE_BALLOON_ENTEROSTOMY(5, "Double Balloon enterostomy"),
    TOTAL_COLECTOMY_AND_ILEOSTOMY(6, "Total Colectomy and ileostomy"),
    SUB_TOTAL_COLECTOMY_AND_ILEOSTOMY(7, "Sub-total Colectomy and ileostomy"),
    SMALL_BOWEL_RESECTION(8, "Small bowel resection"),
    LARGE_BOWEL_RESECTION(9, "Large bowel resection"),
    ILEO_CAECAL_RESECTION(10, "Ileo-caecal  resection"),
    POUCH_FORMATION(11, "Pouch formation"),
    EUA(12, "E.U.A");

    private long id;
    private String name;

    private Surgery(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Surgery getSurgery(Long id) {
        for (Surgery surgery : Surgery.values()) {
            if (surgery.getId() == id) {
                return surgery;
            }
        }

        return NO_PREVIOUS_OPERATIONS;
    }

    public static Surgery getSurgery(String name) {
        for (Surgery surgery : Surgery.values()) {
            if (surgery.getName().equals(name)) {
                return surgery;
            }
        }

        return NO_PREVIOUS_OPERATIONS;
    }

    public static List<Surgery> getAsList() {
        return Arrays.asList(Surgery.values());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
