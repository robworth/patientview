package com.solidstategroup.radar.web.panels.followup;

import com.solidstategroup.radar.web.panels.FollowUpPanel;
import com.solidstategroup.radar.web.panels.subtabs.TreatmentPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class FollowUpTreatmentPanel extends Panel {

    public FollowUpTreatmentPanel(String id, IModel<Long> radarNumberModel) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        add(new TreatmentPanel("treatmentPanel", radarNumberModel));
    }

    @Override
    public boolean isVisible() {
        return ((FollowUpPanel) getParent()).getCurrentTab().equals(FollowUpPanel.CurrentTab.TREATMENT);
    }
}
