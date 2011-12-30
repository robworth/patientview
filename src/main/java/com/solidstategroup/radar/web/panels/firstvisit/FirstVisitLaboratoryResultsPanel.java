package com.solidstategroup.radar.web.panels.firstvisit;

import com.solidstategroup.radar.web.panels.FirstVisitPanel;
import com.solidstategroup.radar.web.panels.subtabs.LaboratoryResultsPanel;
import org.apache.wicket.markup.html.panel.Panel;

public class FirstVisitLaboratoryResultsPanel extends Panel {

    public FirstVisitLaboratoryResultsPanel(String id) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        add(new LaboratoryResultsPanel("formContainer"));
    }

    @Override
    public boolean isVisible() {
        return ((FirstVisitPanel) getParent()).getCurrentTab().equals(FirstVisitPanel.CurrentTab.LABORATORY_RESULTS);
    }
}
