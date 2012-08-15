package com.worthsoln.ibd.model.enums.crohns;

import java.util.Arrays;
import java.util.List;

public enum Complication {
    NO(0, "No", 0),
    MILD(1, "Arthralgia", 1),
    UVEITIS(2, "Uveitis", 1),
    ERYTHEMA_NODUSUM(3, "Erythema nodusum", 1),
    APTHOUS_ULCERS(4, "Apthous ulcers", 1),
    PYODERMA_GANGRENOSUM(5, "Pyoderma gangrenosum", 1),
    ANAL_FISSURE(6, "Anal fissure", 1),
    FISTULA(7, "Fistula", 1),
    ABSCESS(8, "Abscess", 1);

    private int id;
    private String displayText;
    private int score;

    private Complication(int id, String displayText, int score) {
        this.id = id;
        this.displayText = displayText;
        this.score = score;
    }

    public static Complication getComplication(int id) {
        for (Complication complication : Complication.values()) {
            if (complication.getId() == id) {
                return complication;
            }
        }

        return null;
    }

    public int getId() {
        return id;
    }

    public String getDisplayText() {
        return displayText;
    }

    public int getScore() {
        return score;
    }

    public static List<Complication> getAsList() {
        return Arrays.asList(Complication.values());
    }
}
