package com.worthsoln.ibd.model.enums;

import java.util.Arrays;
import java.util.List;

public enum Importance {
    NOT_IMPORTANT_AT_ALL(1, "Not important at all"),
    SOMEWHAT_IMPORTANT(2, "Somewhat important"),
    IMPORTANT(3, "Important"),
    VERY_IMPORTANT(6, "Very important");

    private long id;
    private String name;

    private Importance(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Importance getImportance(Long id) {
        for (Importance importance : Importance.values()) {
            if (importance.getId() == id) {
                return importance;
            }
        }

        return NOT_IMPORTANT_AT_ALL;
    }

    public static List<Importance> getAsList() {
        return Arrays.asList(Importance.values());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
