package com.worthsoln.ibd.model.enums.crohns;

import java.util.Arrays;
import java.util.List;

public enum Complication {
    NONE(0, "None", 0),
    IBD_RELATED_JOINT_COMPLICATIONS(1, "IBD related joint complications (inflammatory arthritis, sacroilelitis)", 1),
    IBD_RELATED_SKIN_COMPLICATIONS(2, "IBD related skin complications (erythema nodosum, pyoderma gangrenosum)", 1),
    IBD_RELATED_EYE_COMPLICATIONS(3, "IBD related eye complications (uveitis, episclreitis)", 1),
    MOUTH_ULCERS(4, "Mouth ulcers", 1),
    ANAL_FISSURE(5, "Anal fissure", 1),
    FISTULA(6, "Fistula", 1),
    ABSCESS(7, "Abscess", 1);

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
