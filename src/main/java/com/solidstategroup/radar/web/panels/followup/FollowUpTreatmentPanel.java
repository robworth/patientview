package com.solidstategroup.radar.web.panels.followup;

import com.solidstategroup.radar.web.panels.subtabs.TreatmentPanel;
import org.apache.wicket.markup.html.panel.Panel;

public class FollowUpTreatmentPanel extends Panel {

    public FollowUpTreatmentPanel(String id) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        add(new TreatmentPanel("treatmentPanel"));
    }

    @Override
    public boolean isVisible() {
        return ((FollowUpPanel) getParent()).getCurrentTab().equals(FollowUpPanel.CurrentTab.TREATMENT);
    }
}
