package com.solidstategroup.radar.web.panels;

import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.model.Ethnicity;
import com.solidstategroup.radar.model.Sex;
import com.solidstategroup.radar.web.pages.PatientPage;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

import java.util.List;

public class PatientDemographicsPanel extends Panel {

    public PatientDemographicsPanel(String id) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        Form<Demographics> form = new Form<Demographics>("form", new CompoundPropertyModel<Demographics>(new Demographics()));
        add(form);

        TextField radarNumber = new TextField("radarNumber");
        radarNumber.setEnabled(false);

        DateTextField dateRegistered = new DateTextField("dateRegistered");
        dateRegistered.setEnabled(false);

        TextField surname = new TextField("surname");
        TextField firstName = new TextField("firstName");
        DateTextField dob = new DateTextField("dob");
        DropDownChoice<Sex> sex = new DropDownChoice<Sex>("sex");
        DropDownChoice<Ethnicity> ethnicity = new DropDownChoice<Ethnicity>("ethnicity");
        TextField address1 = new TextField("address1");
        TextField address2 = new TextField("address2");
        TextField address3 = new TextField("address3");
        TextField address4 = new TextField("address4");
        TextField postcode = new TextField("postcode");
        TextField surnameAlias = new TextField("surnameAlias");
        TextField previousPostcode = new TextField("previousPostcode");
        TextField hospital = new TextField("hospital");
        TextField nhsNumber = new TextField("nhsNumber");
        TextField renalRegistryNumber = new TextField("renalRegistryNumber");
        TextField ukTransplantNumber = new TextField("ukTransplantNumber");
        TextField chiNumber = new TextField("chiNumber");
        DropDownChoice status = new DropDownChoice("status");
        DropDownChoice consultant = new DropDownChoice("consultant");
        DropDownChoice renalUnit = new DropDownChoice("renalUnit");
        CheckBox consent = new CheckBox("consent");
        DropDownChoice renalUnitAuthorised = new DropDownChoice("renalUnitAuthorised");

        //form.add(radarNumber, dateRegistered, surname, firstName, dob, sex, ethnicity, address1, address2, address3, address4, postcode, surnameAlias, previousPostcode,hospital, nhsNumber,renalRegistryNumber,ukTransplantNumber, chiNumber, status,consultant, renalUnit, consent, renalUnitAuthorised);
    }

    @Override
    public boolean isVisible() {
        return ((PatientPage) getPage()).getCurrentTab().equals(PatientPage.CurrentTab.DEMOGRAPHICS);
    }
}
