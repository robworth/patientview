package com.solidstategroup.radar.web.panels.followup;

import org.apache.wicket.markup.html.panel.Panel;

public class TreatmentPanel extends Panel {

    public TreatmentPanel(String id) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);
    }

    @Override
    public boolean isVisible() {
        return ((FollowUpPanel) getParent()).getCurrentTab().equals(FollowUpPanel.CurrentTab.TREATMENT);
    }
}
