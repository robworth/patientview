package com.worthsoln.patientview;

import com.worthsoln.patientview.model.ResultHeading;

import java.util.List;

public class Panel {

    private int panel;
    private boolean currentPanel;
    List<ResultHeading> resultHeadings;

    public Panel() {
    }

    public Panel(int panel) {
        this.panel = panel;
    }

    public int getPanel() {
        return panel;
    }

    public void setPanel(int panel) {
        this.panel = panel;
    }

    public boolean isCurrentPanel() {
        return currentPanel;
    }

    public void setCurrentPanel(boolean currentPanel) {
        this.currentPanel = currentPanel;
    }

    public List<ResultHeading> getResultHeadings() {
        return resultHeadings;
    }

    public void setResultHeadings(List<ResultHeading> resultHeadings) {
        this.resultHeadings = resultHeadings;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Panel)) {
            return false;
        }
        final Panel panel1 = (Panel) o;
        return panel == panel1.panel;
    }

    public int hashCode() {
        return panel;
    }

    public String toString() {
        return panel + "";
    }
}
