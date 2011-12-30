package com.solidstategroup.radar.web.panels.firstvisit;

import com.solidstategroup.radar.web.panels.subtabs.TreatmentPanel;
import org.apache.wicket.markup.html.panel.Panel;

public class FirstVisitTreatmentPanel extends Panel {

    public FirstVisitTreatmentPanel(String id) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        add(new TreatmentPanel("treatmentPanel"));
    }

    @Override
    public boolean isVisible() {
        return ((FirstVisitPanel) getParent()).getCurrentTab().equals(FirstVisitPanel.CurrentTab.TREATMENT);
    }
}
