package com.solidstategroup.radar.web.panels;


import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.web.RadarApplication;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

public class PatientDetailPanel extends Panel {
    public PatientDetailPanel(String id, Demographics demographics, String title) {
        super(id);
        WebMarkupContainer details = new WebMarkupContainer("details", new CompoundPropertyModel<Object>(demographics));
        add(details);

        // add components
        details.add(new Label("title", title));
        TextField<Long> radarNumberField = new TextField<Long>("id");
        Label diseaseGroup = new Label("diseaseGroup", demographics.getDiseaseGroup().getName());
        DateTextField dateRegistered = DateTextField.forDatePattern("dateRegistered", RadarApplication.DATE_PATTERN);

        details.add(radarNumberField, diseaseGroup, dateRegistered);
    }
}
