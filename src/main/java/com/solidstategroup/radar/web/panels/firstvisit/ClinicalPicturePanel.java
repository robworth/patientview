package com.solidstategroup.radar.web.panels.firstvisit;

import com.solidstategroup.radar.model.ClinicalData;
import com.solidstategroup.radar.web.components.PhenotypeChooser;
import com.solidstategroup.radar.web.components.YesNoRadioGroup;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

public class ClinicalPicturePanel extends Panel {

    public ClinicalPicturePanel(String id) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        final Form<ClinicalData> form =
                new Form<ClinicalData>("form", new CompoundPropertyModel<ClinicalData>(new ClinicalData()));

        form.add(new TextField("height"));
        form.add(new TextField("weight"));

        // Blood pressure
        form.add(new TextField("systolicBloodPressure")); // Todo: Validate 50-200
        form.add(new TextField("diastolicBloodPressure")); // Todo: Validate 20-150
        form.add(new TextField("meanArterialPressure").setEnabled(false));

        form.add(new PhenotypeChooser("phenotype1"));
        form.add(new PhenotypeChooser("phenotype2"));
        form.add(new PhenotypeChooser("phenotype3"));
        form.add(new PhenotypeChooser("phenotype4"));

        form.add(new TextArea("comments"));

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

        form.add(new TextField("significantDiagnosis1"));
        form.add(new TextField("significantDiagnosis2"));

        // Yes/No/Unknown for the following
        form.add(new YesNoRadioGroup("oedema", true));
        form.add(new YesNoRadioGroup("hypovalaemia", true));
        form.add(new YesNoRadioGroup("fever", true));
        form.add(new YesNoRadioGroup("thrombosis", true));
        form.add(new YesNoRadioGroup("peritonitis", true));
        form.add(new YesNoRadioGroup("pulmonaryOedema", true));
        form.add(new YesNoRadioGroup("hypertension", true));

        // Diabetes
        form.add(new DropDownChoice<ClinicalData.DiabetesType>("diabetesType"));

        // Todo: Visibilities depend on the diagnosis - see first-clinical.aspx.vb:287

        // More yes/no options
        form.add(new YesNoRadioGroup("rash", true));
        form.add(new YesNoRadioGroup("possibleImmunisationTrigger", true));
        form.add(new YesNoRadioGroup("partialLipodystrophy", true));
        form.add(new YesNoRadioGroup("preceedingInfection", true));
        form.add(new YesNoRadioGroup("chronicInfection", true));
        form.add(new YesNoRadioGroup("ophthalmoscopy", true));

        // Rash details needs show/hide
        MarkupContainer rashDetailContainer = new WebMarkupContainer("rashDetailContainer") {
            @Override
            public boolean isVisible() {
                return Boolean.TRUE.equals(form.getModelObject().getRash());
            }
        };
        rashDetailContainer.add(new TextArea("rashDetail"));
        form.add(rashDetailContainer);

        // Ophthalmoscopy show/hide
        MarkupContainer ophthalmoscopyDetailContainer = new WebMarkupContainer("ophthalmoscopyDetailContainer") {
            @Override
            public boolean isVisible() {
                return Boolean.TRUE.equals(form.getModelObject().getOphthalmoscopy());
            }
        };
        ophthalmoscopyDetailContainer.add(new TextArea("ophthalmoscopyDetail"));
        form.add(ophthalmoscopyDetailContainer);

        // Preceeding infection show/hide
        MarkupContainer preceedingInfectionDetailContainer =
                new WebMarkupContainer("preceedingInfectionDetailContainer") {
                    @Override
                    public boolean isVisible() {
                        return Boolean.TRUE.equals(form.getModelObject().getPreceedingInfection());
                    }
                };
        preceedingInfectionDetailContainer.add(new TextArea("preceedingInfectionDetail"));
        form.add(preceedingInfectionDetailContainer);

        // Chronic infection show/hide
        MarkupContainer chronicInfectionDetailContainer = new WebMarkupContainer("chronicInfectionDetailContainer") {
            @Override
            public boolean isVisible() {
                return Boolean.TRUE.equals(form.getModelObject().getChronicInfection());
            }
        };
        ophthalmoscopyDetailContainer.add(new TextArea("chronicInfectionDetail"));
        form.add(chronicInfectionDetailContainer);

        add(form);
    }

    @Override
    public boolean isVisible() {
        return ((FirstVisitPanel) getParent()).getCurrentTab().equals(FirstVisitPanel.CurrentTab.CLINICAL_PICTURE);
    }

}
