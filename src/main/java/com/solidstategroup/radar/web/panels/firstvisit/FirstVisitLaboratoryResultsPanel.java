package com.solidstategroup.radar.web.panels.firstvisit;

import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.components.RadarFormComponentFeedbackIndicator;
import com.solidstategroup.radar.web.panels.FirstVisitPanel;
import com.solidstategroup.radar.web.panels.subtabs.LaboratoryResultsPanel;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;

import java.util.Date;

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
