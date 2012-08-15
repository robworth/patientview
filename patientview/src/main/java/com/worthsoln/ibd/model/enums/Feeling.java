package com.worthsoln.ibd.model.enums;

import java.util.Arrays;
import java.util.List;

public enum Feeling {
    WELL(0, "I feel well", 0),
    BELOW_PAR(1, "Slightly below par", 1),
    POOR(2, "Poor", 2),
    VERY_POOR(3, "Very poor", 3),
    TERRIBLE(4, "Terrible", 4);

    private int id;
    private String displayText;
    private int score;

    private Feeling(int id, String displayText, int score) {
        this.id = id;
        this.displayText = displayText;
        this.score = score;
    }

    public static Feeling getFeeling(int id) {
        for (Feeling feeling : Feeling.values()) {
            if (feeling.getId() == id) {
                return feeling;
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

    public static List<Feeling> getAsList() {
        return Arrays.asList(Feeling.values());
    }
}
