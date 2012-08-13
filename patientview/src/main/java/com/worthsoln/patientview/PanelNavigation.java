package com.worthsoln.patientview;

import com.worthsoln.patientview.model.Panel;

import java.util.List;

public class PanelNavigation {

    private List<Panel> panels;
    private Panel currentPanel;
    private Panel previousPanel;
    private Panel nextPanel;
    private Panel firstPanel;
    private Panel lastPanel;

    public Panel getLastPanel() {
        return lastPanel;
    }

    public void setLastPanel(Panel lastPanel) {
        this.lastPanel = lastPanel;
    }

    public Panel getFirstPanel() {
        return firstPanel;
    }

    public void setFirstPanel(Panel firstPanel) {
        this.firstPanel = firstPanel;
    }

    public PanelNavigation(Panel currentPanel, List<Panel> panels) {
        if (currentPanel == null) {
            currentPanel = new Panel(1);
        }
        this.currentPanel = currentPanel;
        this.panels = panels;
        for (int i = 0; i < panels.size(); i++) {
            Panel panel = panels.get(i);
            if (i == 0) {
                firstPanel = panel;
            }
            if (i == panels.size() - 1) {
                lastPanel = panel;
            }
            if (panel.equals(currentPanel)) {
                panel.setCurrentPanel(true);
                if (i == 0) {
                    previousPanel = null;
                } else {
                    previousPanel = (Panel) panels.get(i - 1);
                }
                if (i == panels.size() - 1) {
                    nextPanel = null;
                } else {
                    nextPanel = (Panel) panels.get(i + 1);
                }
            }
        }
    }

    public Panel getPreviousPanel() {
        return previousPanel;
    }

    public Panel getNextPanel() {
        return nextPanel;
    }

    public Panel getCurrentPanel() {
        return currentPanel;
    }

    public void setCurrentPanel(Panel currentPanel) {
        this.currentPanel = currentPanel;
    }

    public List getPanels() {
        return panels;
    }

    public void setPanels(List panels) {
        this.panels = panels;
    }
}
