package com.worthsoln.ibd.model.enums;

import java.util.Arrays;
import java.util.List;

public enum Smoking {
    NEVER_SMOKED(1, "Never smoked"),
    PREVIOUSLY_SMOKED(2, "Previously smoked"),
    CURRENT_SMOKER(3, "Current smoker");

    private long id;
    private String name;

    private Smoking(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Smoking getSmoking(Long id) {
        for (Smoking smoking : Smoking.values()) {
            if (smoking.getId() == id) {
                return smoking;
            }
        }

        return NEVER_SMOKED;
    }

    public static Smoking getSmoking(String name) {
        for (Smoking smoking : Smoking.values()) {
            if (smoking.getName().equals(name)) {
                return smoking;
            }
        }

        return NEVER_SMOKED;
    }

    public static List<Smoking> getAsList() {
        return Arrays.asList(Smoking.values());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
