package com.worthsoln.ibd.model.symptoms;

import java.util.ArrayList;
import java.util.List;

public class SymptomsGraphData {
    public String error = "";
    private List<SymptomsData> graphData = new ArrayList<SymptomsData>();
    private int severeLevel, moderateLevel, mildLevel;

    public void addGraphData(SymptomsData symptomsData) {
        if (graphData == null) {
            graphData = new ArrayList<SymptomsData>();
        }

        graphData.add(symptomsData);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<SymptomsData> getGraphData() {
        return graphData;
    }

    public void setGraphData(List<SymptomsData> graphData) {
        this.graphData = graphData;
    }

    public int getSevereLevel() {
        return severeLevel;
    }

    public void setSevereLevel(int severeLevel) {
        this.severeLevel = severeLevel;
    }

    public int getModerateLevel() {
        return moderateLevel;
    }

    public void setModerateLevel(int moderateLevel) {
        this.moderateLevel = moderateLevel;
    }

    public int getMildLevel() {
        return mildLevel;
    }

    public void setMildLevel(int mildLevel) {
        this.mildLevel = mildLevel;
    }
}
