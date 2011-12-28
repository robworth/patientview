package com.solidstategroup.radar.web.panels.firstvisit;

import org.apache.wicket.markup.html.panel.Panel;

public class LaboratoryResultsPanel extends Panel {

    public LaboratoryResultsPanel(String id) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);
    }

    @Override
    public boolean isVisible() {
        return ((FirstVisitPanel) getParent()).getCurrentTab().equals(FirstVisitPanel.CurrentTab.LABORATORY_RESULTS);
    }
}
