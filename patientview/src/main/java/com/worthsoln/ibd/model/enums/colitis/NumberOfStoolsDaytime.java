package com.worthsoln.ibd.model.enums.colitis;

import java.util.Arrays;
import java.util.List;

public enum NumberOfStoolsDaytime {
    ZERO_TO_THREE(0, "0-3", 0),
    FOUR_TO_SIX(1, "4-6", 1),
    SEVEN_TO_NINE(2, "7-9", 2),
    MORE_THAN_NINE(3, ">9", 3);

    private int id;
    private String displayText;
    private int score;

    private NumberOfStoolsDaytime(int id, String displayText, int score) {
        this.id = id;
        this.displayText = displayText;
        this.score = score;
    }

    public static NumberOfStoolsDaytime getNumberOfStoolsDaytime(int id) {
            for (NumberOfStoolsDaytime numberOfStoolsDaytime : NumberOfStoolsDaytime.values()) {
                if (numberOfStoolsDaytime.getId() == id) {
                    return numberOfStoolsDaytime;
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

    public static List<NumberOfStoolsDaytime> getAsList() {
        return Arrays.asList(NumberOfStoolsDaytime.values());
    }
}
