package com.solidstategroup.radar.web.panels;

import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.model.Ethnicity;
import com.solidstategroup.radar.model.Sex;
import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.components.RadarDatePicker;
import com.solidstategroup.radar.web.pages.PatientPage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.FormComponentFeedbackIndicator;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.PatternValidator;

import java.util.Arrays;
import java.util.List;

public class DemographicsPanel extends Panel {

    public DemographicsPanel(String id) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        Form<Demographics> form =
                new Form<Demographics>("form", new CompoundPropertyModel<Demographics>(new Demographics()));
        add(form);

        TextField radarNumber = new TextField("radarNumber");
        radarNumber.setEnabled(false);

        DateTextField dateRegistered = DateTextField.forDatePattern("dateRegistered", RadarApplication.DATE_PATTERN);
        dateRegistered.setEnabled(false);

        DropDownChoice diagnosis =
                new DropDownChoice("diagnosis", new Model<String>(), Arrays.asList("MPGN/DDD", "SRNS"));
        diagnosis.setRequired(true);

        // Basic fields
        TextField surname = new TextField("surname");
        surname.setRequired(true);
        TextField forename = new TextField("forename");
        forename.setRequired(true);
        DateTextField dateOfBirth = DateTextField.forDatePattern("dateOfBirth", RadarApplication.DATE_PATTERN);

        DatePicker datePicker = new RadarDatePicker();

        dateOfBirth.add(datePicker);
        dateOfBirth.setRequired(true);

        form.add(diagnosis, surname, forename, dateOfBirth);

        final FormComponentFeedbackIndicator surnameErrorIndicator =
                new FormComponentFeedbackIndicator("surnameErrorIndicator");
        surnameErrorIndicator.setIndicatorFor(surname);
        surnameErrorIndicator.setOutputMarkupId(true);
        surnameErrorIndicator.setOutputMarkupPlaceholderTag(true);

        final FormComponentFeedbackIndicator foreNameErrorIndicator =
                new FormComponentFeedbackIndicator("forenameErrorIndicator");
        foreNameErrorIndicator.setIndicatorFor(forename);
        foreNameErrorIndicator.setOutputMarkupId(true);
        foreNameErrorIndicator.setOutputMarkupPlaceholderTag(true);

        final FormComponentFeedbackIndicator dobErrorIndicator =
                new FormComponentFeedbackIndicator("dobErrorIndicator");
        dobErrorIndicator.setIndicatorFor(dateOfBirth);
        dobErrorIndicator.setOutputMarkupId(true);
        dobErrorIndicator.setOutputMarkupPlaceholderTag(true);

        final FormComponentFeedbackIndicator diagnosisErrorIndicator =
                new FormComponentFeedbackIndicator("diagnosisErrorIndicator");
        diagnosisErrorIndicator.setIndicatorFor(diagnosis);
        diagnosisErrorIndicator.setOutputMarkupId(true);
        diagnosisErrorIndicator.setOutputMarkupPlaceholderTag(true);

        form.add(diagnosisErrorIndicator, surnameErrorIndicator, foreNameErrorIndicator, dobErrorIndicator);

        final ComponentFeedbackPanel dobFeedbackPanel = new ComponentFeedbackPanel("dobFeedbackPanel", dateOfBirth) {


            @Override
            public boolean isVisible() {
                List<FeedbackMessage> feedbackMessages = getCurrentMessages();
                for (FeedbackMessage feedbackMessage : feedbackMessages) {
                    if (feedbackMessage.getMessage().toString().contains("required")) {
                        return false;
                    }
                }
                return super.isVisible();
            }
        };

        dobFeedbackPanel.setOutputMarkupId(true);
        dobFeedbackPanel.setOutputMarkupPlaceholderTag(true);
        form.add(dobFeedbackPanel);


        // Drop downs for sex and ethnicity
        Sex tempSex = new Sex();
        tempSex.setType("temp");
        tempSex.setId(Long.valueOf(1));

        DropDownChoice<Sex> sex =
                new DropDownChoice<Sex>("sex", Arrays.asList(tempSex), new ChoiceRenderer<Sex>("type"));
        sex.setRequired(true);
        DropDownChoice<Ethnicity> ethnicity = new DropDownChoice<Ethnicity>("ethnicity");
        form.add(sex, ethnicity);

        final FormComponentFeedbackIndicator sexErrorIndicator =
                new FormComponentFeedbackIndicator("sexErrorIndicator");
        sexErrorIndicator.setIndicatorFor(sex);
        sexErrorIndicator.setOutputMarkupId(true);
        sexErrorIndicator.setOutputMarkupPlaceholderTag(true);
        form.add(sexErrorIndicator);

        // Address fields
        TextField address1 = new TextField("address1");
        TextField address2 = new TextField("address2");
        TextField address3 = new TextField("address3");
        TextField address4 = new TextField("address4");
        TextField postcode = new TextField("postcode");
        postcode.add(new PatternValidator("[a-zA-Z]{1,2}[0-9][0-9A-Za-z]{0,1} {0,1}[0-9][A-Za-z]{2}$"));
        form.add(address1, address2, address3, address4, postcode);

        final ComponentFeedbackPanel postcodeFeedback = new ComponentFeedbackPanel("postcodeFeedback", postcode);
        postcodeFeedback.setOutputMarkupId(true);
        postcode.setOutputMarkupPlaceholderTag(true);

        form.add(postcodeFeedback);

        // Archive fields
        TextField surnameAlias = new TextField("surnameAlias");
        TextField previousPostcode = new TextField("previousPostcode");
        form.add(surnameAlias, previousPostcode);

        // More info
        TextField hospitalNumber = new TextField("hospitalNumber");
        hospitalNumber.setRequired(true);
        TextField nhsNumber = new TextField("nhsNumber");
        TextField renalRegistryNumber = new TextField("renalRegistryNumber");
        TextField ukTransplantNumber = new TextField("ukTransplantNumber");
        TextField chiNumber = new TextField("chiNumber");
        form.add(hospitalNumber, nhsNumber, renalRegistryNumber, ukTransplantNumber, chiNumber);

        final FormComponentFeedbackIndicator hospitalNumberErrorIndicator =
                new FormComponentFeedbackIndicator("hospitalNumberErrorIndicator");
        hospitalNumberErrorIndicator.setIndicatorFor(hospitalNumber);
        hospitalNumberErrorIndicator.setOutputMarkupId(true);
        hospitalNumberErrorIndicator.setOutputMarkupPlaceholderTag(true);

        form.add(hospitalNumberErrorIndicator);


        DropDownChoice status = new DropDownChoice("status");
        DropDownChoice consultant = new DropDownChoice("consultant");
        DropDownChoice renalUnit = new DropDownChoice("renalUnit");
        form.add(status, consultant, renalUnit);

        CheckBox consent = new CheckBox("consent");
        DropDownChoice renalUnitAuthorised = new DropDownChoice("renalUnitAuthorised");
        form.add(consent, renalUnitAuthorised);

        form.add(new AjaxSubmitLink("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                // Todo: Implement
            }

            @Override
            protected void onError(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                ajaxRequestTarget
                        .add(diagnosisErrorIndicator, surnameErrorIndicator, foreNameErrorIndicator, dobErrorIndicator,
                                dobFeedbackPanel, sexErrorIndicator, hospitalNumberErrorIndicator, postcodeFeedback);
                dobFeedbackPanel.getFeedbackMessages();

            }
        });
    }

    @Override
    public boolean isVisible() {
        return ((PatientPage) getPage()).getCurrentTab().equals(PatientPage.CurrentTab.DEMOGRAPHICS);
    }
}
