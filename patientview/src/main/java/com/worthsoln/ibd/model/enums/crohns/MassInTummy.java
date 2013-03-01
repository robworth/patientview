package com.worthsoln.ibd.model.enums.crohns;

import java.util.Arrays;
import java.util.List;

public enum MassInTummy {
    NO(0, "No", 0),
    POSSIBLE(1, "Possible", 1),
    DEFINITE(2, "Definite", 2),
    DEFINITE_AND_TENDER(3, "Definite and tender", 3);

    private int id;
    private String displayText;
    private int score;

    private MassInTummy(int id, String displayText, int score) {
        this.id = id;
        this.displayText = displayText;
        this.score = score;
    }

    public static MassInTummy getMassInTummy(int id) {
        for (MassInTummy massInTummy : MassInTummy.values()) {
            if (massInTummy.getId() == id) {
                return massInTummy;
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

    public static List<MassInTummy> getAsList() {
        return Arrays.asList(MassInTummy.values());
    }

}
