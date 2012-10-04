package com.worthsoln.ibd.model.enums;

import java.util.Arrays;
import java.util.List;

public enum BodyPartAffected {
    NONE(1, "None"),
    PRIMARY_SCLEROSING_CHOLANGITIS(2, "Primary Sclerosing Cholangitis"),
    NON_SPECIFIC_ARTRALGIA(3, "Non-specific arthralgia"),
    TYPE_1_ARTHRITIS(4, "Type 1 arthritis (>5 joints, worse with active disease)"),
    TYPE_2_ARTHRITIS(5, "Type 2 arthritis (<5 joints, small joints, symmetrical)"),
    SACROILILITIS(6, "Sacroililitis"),
    ANKYLOSING_SPONDYLITIS(7, "Ankylosing Spondylitis"),
    ERYTHEMA_NODOSUM(8, "Erythema Nodosum"),
    PYODERMA_GANGRENOSUM(9, "Pyoderma Gangrenosum"),
    EPISCLERITIS(10, "Episcleritis"),
    UVEITIS(11, "Uveitis");

    private long id;
    private String name;

    private BodyPartAffected(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static BodyPartAffected getBodyPartAffected(Long id) {
        for (BodyPartAffected bodyPartAffected : BodyPartAffected.values()) {
            if (bodyPartAffected.getId() == id) {
                return bodyPartAffected;
            }
        }

        return NONE;
    }

    public static BodyPartAffected getBodyPartAffected(String name) {
        for (BodyPartAffected bodyPartAffected : BodyPartAffected.values()) {
            if (bodyPartAffected.getName() == name) {
                return bodyPartAffected;
            }
        }

        return NONE;
    }

    public static List<BodyPartAffected> getAsList() {
        return Arrays.asList(BodyPartAffected.values());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
