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

    AbdominalPain(int id, String displayText, int score) {
        this.id = id;
        this.displayText = displayText;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static List<AbdominalPain> getAsList() {
        return Arrays.asList(AbdominalPain.values());
    }
}
