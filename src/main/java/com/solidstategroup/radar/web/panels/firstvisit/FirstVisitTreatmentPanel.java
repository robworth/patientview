package com.solidstategroup.radar.web.panels.firstvisit;

import com.solidstategroup.radar.web.panels.FirstVisitPanel;
import com.solidstategroup.radar.web.panels.subtabs.TreatmentPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class FirstVisitTreatmentPanel extends Panel {

    public FirstVisitTreatmentPanel(String id, IModel<Long> radarNumberModel) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        add(new TreatmentPanel("treatmentPanel", radarNumberModel));
    }

    @Override
    public boolean isVisible() {
        return ((FirstVisitPanel) getParent()).getCurrentTab().equals(FirstVisitPanel.CurrentTab.TREATMENT);
    }
}
