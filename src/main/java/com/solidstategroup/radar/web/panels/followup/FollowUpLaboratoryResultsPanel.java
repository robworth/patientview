package com.solidstategroup.radar.web.panels.followup;

import com.solidstategroup.radar.web.panels.subtabs.LaboratoryResultsPanel;
import org.apache.wicket.markup.html.panel.Panel;

public class FollowUpLaboratoryResultsPanel extends Panel {

    public FollowUpLaboratoryResultsPanel(String id) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        add(new LaboratoryResultsPanel("formContainer"));
    }

    @Override
    public boolean isVisible() {
        return ((FollowUpPanel) getParent()).getCurrentTab().equals(FollowUpPanel.CurrentTab.LABORATORY_RESULTS);
    }
}
