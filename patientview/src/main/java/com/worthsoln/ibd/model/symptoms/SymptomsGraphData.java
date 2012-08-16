package com.worthsoln.ibd.model.symptoms;

import java.util.ArrayList;
import java.util.List;

public class SymptomsGraphData {
    public String error = "";
    List<SymptomsData> graphData = new ArrayList<SymptomsData>();

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
}
