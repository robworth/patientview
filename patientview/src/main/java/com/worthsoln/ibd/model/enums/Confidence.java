package com.worthsoln.ibd.model.enums;

import java.util.Arrays;
import java.util.List;

public enum Confidence {

    NOT_CONFIDENT(1, "Not confident"),
    SOMEWHAT_CONFIDENT(2, "Somewhat confident"),
    CONFIDENT(3, "Confident"),
    VERY_CONFIDENT(4, "Very confident");

    private long id;
    private String name;

    private Confidence(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Confidence getConfidence(Long id) {
        for (Confidence confidence : Confidence.values()) {
            if (confidence.getId() == id) {
                return confidence;
            }
        }

        return NOT_CONFIDENT;
    }

    public static List<Confidence> getAsList() {
        return Arrays.asList(Confidence.values());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
