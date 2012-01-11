package com.solidstategroup.radar.web.panels.followup;

import com.solidstategroup.radar.web.panels.FollowUpPanel;
import com.solidstategroup.radar.web.panels.subtabs.LaboratoryResultsPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class FollowUpLaboratoryResultsPanel extends Panel {

    public FollowUpLaboratoryResultsPanel(String id, IModel<Long> radarNumberModel) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        add(new LaboratoryResultsPanel("formContainer", radarNumberModel));
    }

    @Override
    public boolean isVisible() {
        return ((FollowUpPanel) getParent()).getCurrentTab().equals(FollowUpPanel.CurrentTab.LABORATORY_RESULTS);
    }
}
