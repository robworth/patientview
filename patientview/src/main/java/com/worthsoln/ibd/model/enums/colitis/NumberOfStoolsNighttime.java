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

    public static NumberOfStoolsNighttime getNumberOfStoolsNighttime(int id) {
        for (NumberOfStoolsNighttime numberOfStoolsNighttime : NumberOfStoolsNighttime.values()) {
            if (numberOfStoolsNighttime.getId() == id) {
                return numberOfStoolsNighttime;
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

    public static List<NumberOfStoolsNighttime> getAsList() {
        return Arrays.asList(NumberOfStoolsNighttime.values());
    }
}
