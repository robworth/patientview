package org.patientview.radar.web.panels;


import org.patientview.radar.model.Demographics;
import org.patientview.radar.web.RadarApplication;
import org.apache.commons.lang.StringUtils;
import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;

import java.util.Date;

public class PatientDetailPanel extends Panel {
    public PatientDetailPanel(String id, final Demographics demographics, String title) {
        super(id);

        WebMarkupContainer details = new WebMarkupContainer("details", new CompoundPropertyModel<Object>(demographics));
        details.setOutputMarkupId(true);
        details.setOutputMarkupPlaceholderTag(true);
        add(details);

        // Note: this panel is shown after initial enter new patient, and you may not have all patient data yet

        /**
         * Add components
         */

        // title
        details.add(new Label("title", title));

        // radar number
        TextField<Long> radarNumberField = new TextField<Long>("id");
        details.add(radarNumberField);

        // disease group
        Label diseaseGroup = new Label("diseaseGroup", new PropertyModel<Object>(demographics.getDiseaseGroup(),
                "name"));
        details.add(diseaseGroup);

        // forename
        Label nameLabel = new Label("nameLabel", "Patient Name") {
            @Override
            public boolean isVisible() {
                return StringUtils.isNotBlank(demographics.getForename());
            }
        };
        nameLabel.setOutputMarkupId(true);
        nameLabel.setOutputMarkupPlaceholderTag(true);
        details.add(nameLabel);

        TextField<Long> forename = new TextField<Long>("forename") {
            @Override
            public boolean isVisible() {
                return StringUtils.isNotBlank(demographics.getForename());
            }
        };
        forename.setOutputMarkupId(true);
        forename.setOutputMarkupPlaceholderTag(true);
        details.add(forename);

        // surname
        TextField<Long> surname = new TextField<Long>("surname") {
            @Override
            public boolean isVisible() {
                return StringUtils.isNotBlank(demographics.getSurname());
            }
        };
        surname.setOutputMarkupId(true);
        surname.setOutputMarkupPlaceholderTag(true);
        details.add(surname);

        // date registered
        DateTextField dateRegistered = DateTextField.forDatePattern("dateRegistered", RadarApplication.DATE_PATTERN);
        details.add(dateRegistered);

        // date of birth
        Label dobLabel = new Label("dobLabel", "Patient DOB") {
            @Override
            public boolean isVisible() {
                return demographics.getDateOfBirth() != null;
            }
        };
        dobLabel.setOutputMarkupId(true);
        dobLabel.setOutputMarkupPlaceholderTag(true);
        details.add(dobLabel);

        DateTextField dateOfBirthTextField = new DateTextField("dob",
                new PropertyModel<Date>(demographics, "dateOfBirth"), new PatternDateConverter(
                RadarApplication.DATE_PATTERN, true)) {
            @Override
            public boolean isVisible() {
                return demographics.getDateOfBirth() != null;
            }
        };
        dateOfBirthTextField.setOutputMarkupId(true);
        dateOfBirthTextField.setOutputMarkupPlaceholderTag(true);
        details.add(dateOfBirthTextField);
    }
}
