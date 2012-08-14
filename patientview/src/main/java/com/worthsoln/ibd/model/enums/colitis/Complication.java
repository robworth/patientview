package com.worthsoln.ibd.model.enums.colitis;

import java.util.Arrays;
import java.util.List;

public enum Complication {

    NONE(0, "None", 0),
    JOINT_PROBLEMS(1, "Joint Problems(arthralgia)", 1),
    SKIN_PROBLEMS(2, "Skin Problems (erythema nodusum, pyoderma gangrenosum)", 1),
    EYE_PROBLEMS(3, "Eye Problems (uveitis, scleritis)", 1);

    private int id;
    private String displayText;
    private int score;

    private Complication(int id, String displayText, int score) {
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

    public static List<Complication> getAsList() {
        return Arrays.asList(Complication.values());
    }
}
