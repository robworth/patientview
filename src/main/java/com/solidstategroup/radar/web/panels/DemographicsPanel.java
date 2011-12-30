package com.solidstategroup.radar.web.panels;

import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.model.Ethnicity;
import com.solidstategroup.radar.model.Sex;
import com.solidstategroup.radar.web.pages.PatientPage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

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

        DateTextField dateRegistered = new DateTextField("dateRegistered");
        dateRegistered.setEnabled(false);

        // Basic fields
        TextField surname = new TextField("surname");
        TextField forename = new TextField("forename");
        DateTextField dateOfBirth = new DateTextField("dateOfBirth");
        form.add(surname, forename, dateOfBirth);

        // Drop downs for sex and ethnicity
        DropDownChoice<Sex> sex = new DropDownChoice<Sex>("sex");
        DropDownChoice<Ethnicity> ethnicity = new DropDownChoice<Ethnicity>("ethnicity");
        form.add(sex, ethnicity);

        // Address fields
        TextField address1 = new TextField("address1");
        TextField address2 = new TextField("address2");
        TextField address3 = new TextField("address3");
        TextField address4 = new TextField("address4");
        TextField postcode = new TextField("postcode");
        form.add(address1, address2, address3, address4, postcode);

        // Archive fields
        TextField surnameAlias = new TextField("surnameAlias");
        TextField previousPostcode = new TextField("previousPostcode");
        form.add(surnameAlias, previousPostcode);

        // More info
        TextField hospitalNumber = new TextField("hospitalNumber");
        TextField nhsNumber = new TextField("nhsNumber");
        TextField renalRegistryNumber = new TextField("renalRegistryNumber");
        TextField ukTransplantNumber = new TextField("ukTransplantNumber");
        TextField chiNumber = new TextField("chiNumber");
        form.add(hospitalNumber, nhsNumber, renalRegistryNumber, ukTransplantNumber, chiNumber);

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
                // Todo: Implement
            }
        });
    }

    @Override
    public boolean isVisible() {
        return ((PatientPage) getPage()).getCurrentTab().equals(PatientPage.CurrentTab.DEMOGRAPHICS);
    }
}
