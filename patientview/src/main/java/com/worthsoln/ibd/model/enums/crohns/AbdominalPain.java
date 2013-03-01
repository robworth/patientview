package com.worthsoln.ibd.model.enums.crohns;

import java.util.Arrays;
import java.util.List;

public enum AbdominalPain {
    NO(0, "No", 0),
    MILD(1, "Mild", 1),
    MODERATE(2, "Moderate", 2),
    SEVERE(3, "Severe", 3);

    private int id;
    private String displayText;
    private int score;

    private AbdominalPain(int id, String displayText, int score) {
        this.id = id;
        this.displayText = displayText;
        this.score = score;
    }

    public static AbdominalPain getAbdominalPain(int id) {
        for (AbdominalPain abdominalPain : AbdominalPain.values()) {
            if (abdominalPain.getId() == id) {
                return abdominalPain;
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

    public static List<AbdominalPain> getAsList() {
        return Arrays.asList(AbdominalPain.values());
    }
}
