package com.worthsoln.ibd.model.enums.colitis;

import java.util.Arrays;
import java.util.List;

public enum ToiletTiming {
    DONT_NEED_TO_RUSH(0, "I don't need to hurry", 0),
    NEED_TO_HURRY(1, "I need to hurry ", 1),
    NEED_TO_GO_IMMEDIATELY(2, "I need to go immediately", 2),
    HAVING_ACCIDENTS(3, "I am having accidents", 3);

    private int id;
    private String displayText;
    private int score;

    private ToiletTiming(int id, String displayText, int score) {
        this.id = id;
        this.displayText = displayText;
        this.score = score;
    }

    public static ToiletTiming getToiletTiming(int id) {
        for (ToiletTiming toiletTiming : ToiletTiming.values()) {
            if (toiletTiming.getId() == id) {
                return toiletTiming;
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

    public static List<ToiletTiming> getAsList() {
        return Arrays.asList(ToiletTiming.values());
    }
}