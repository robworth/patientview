package com.worthsoln.ibd.model.enums.colitis;

import java.util.Arrays;
import java.util.List;

public enum PresentBlood {

    NONE(0, "None", 0),
    A_TRACE(1, "A trace", 1),
    OCCASIONAL(2, "Occasional Blood", 2),
    ALWAYS_PRESENT(3, "Blood is always present", 3);

    private int id;
    private String displayText;
    private int score;

    private PresentBlood(int id, String displayText, int score) {
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

    public static List<PresentBlood> getAsList() {
        return Arrays.asList(PresentBlood.values());
    }
}
