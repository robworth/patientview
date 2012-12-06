package com.solidstategroup.radar.web.panels;


import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.web.RadarApplication;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;

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
        details.add(new Label("name", demographics.getForename() + " " + demographics.getSurname()));

        DateTimeFormatter df = DateTimeFormat.forPattern("EEE MMM dd HH:mm:ss 'GMT' yyyy");
        DateTime dateTime = df.withOffsetParsed().parseDateTime(demographics.getDateOfBirth().toString());
        details.add(new Label("dob", new SimpleDateFormat(RadarApplication.DATE_PATTERN).format(dateTime.toDate())));

        details.add(radarNumberField, diseaseGroup, dateRegistered);
    }
}
