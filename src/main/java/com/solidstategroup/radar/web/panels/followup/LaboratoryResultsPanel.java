package com.solidstategroup.radar.web.panels.followup;

import org.apache.wicket.markup.html.panel.Panel;

public class LaboratoryResultsPanel extends Panel {

    public LaboratoryResultsPanel(String id) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);
    }

    @Override
    public boolean isVisible() {
        return ((FollowUpPanel) getParent()).getCurrentTab().equals(FollowUpPanel.CurrentTab.LABORATORY_RESULTS);
    }
}
