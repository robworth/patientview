package com.worthsoln.ibd.model.symptoms;

import com.worthsoln.ibd.Ibd;

public class SymptomsData {
    public int score;
    public String date;

    public SymptomsData(BaseSymptoms baseSymptoms) {
        this.score = baseSymptoms.getScore();
        this.date = Ibd.DATE_FORMAT.format(baseSymptoms.getSymptomDate());
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
