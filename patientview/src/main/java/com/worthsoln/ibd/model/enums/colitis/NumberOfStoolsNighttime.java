package com.worthsoln.ibd.model.enums.colitis;

import java.util.Arrays;
import java.util.List;

public enum NumberOfStoolsNighttime {

    ONE_TO_THREE(0, "1-3", 1),
    FOUR_TO_SIX(1, "4-6", 2);

    private int id;
    private String displayText;
    private int score;

    private NumberOfStoolsNighttime(int id, String displayText, int score) {
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

    public static List<NumberOfStoolsNighttime> getAsList() {
        return Arrays.asList(NumberOfStoolsNighttime.values());
    }
}
