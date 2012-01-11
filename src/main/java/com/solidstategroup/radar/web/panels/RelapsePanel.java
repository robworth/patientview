package com.solidstategroup.radar.web.panels;

import com.solidstategroup.radar.model.Plasmapheresis;

import com.solidstategroup.radar.model.PlasmapheresisExchangeUnit;
import com.solidstategroup.radar.model.sequenced.Relapse;
import com.solidstategroup.radar.model.enums.KidneyTransplantedNative;
import com.solidstategroup.radar.model.enums.RemissionAchieved;
import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.components.RadarDateTextField;
import com.solidstategroup.radar.web.components.RadarRequiredDateTextField;
import com.solidstategroup.radar.web.components.RadarRequiredDropdownChoice;
import com.solidstategroup.radar.web.pages.PatientPage;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class RelapsePanel extends Panel {
    public RelapsePanel(String id) {
        super(id);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);

        final List<Component> componentsToUpdate = new ArrayList<Component>();

        Form<Relapse> form = new Form<Relapse>("form", new CompoundPropertyModel<Relapse>(new Relapse()));

        form.add(new RadarRequiredDateTextField("dateOfRelapse", RadarApplication.DATE_PATTERN, form, componentsToUpdate));

        // Transplanted / native radio options
        RadioGroup<KidneyTransplantedNative> transplantedNative =
                new RadioGroup<KidneyTransplantedNative>("transplantedNative");
        transplantedNative.add(new Radio<KidneyTransplantedNative>("tx",
                new Model<KidneyTransplantedNative>(KidneyTransplantedNative.TRANSPLANTED)));
        transplantedNative.add(new Radio<KidneyTransplantedNative>("native",
                new Model<KidneyTransplantedNative>(KidneyTransplantedNative.NATIVE)));
        form.add(transplantedNative);

        // Triggers
        form.add(new TextField("viralTrigger"));
        form.add(new TextField("immunisationTrigger"));
        form.add(new TextField("otherTrigger"));

        // Drugs
        form.add(new TextField("drug1"));
        form.add(new TextField("drug2"));
        form.add(new TextField("drug3"));


        final List<Component> plasmapheresisComponentsToUpdate = new ArrayList<Component>();

        // Inner form for plasmapheresis
        Form<Plasmapheresis> plasmapheresisForm =
                new Form<Plasmapheresis>("plasmapheresisForm",
                        new CompoundPropertyModel<Plasmapheresis>(new Plasmapheresis())){
                    @Override
                    protected void onValidateModelObjects() {
                        super.onValidateModelObjects();
                        Plasmapheresis plasmapheresis = getModelObject();
                        Date startDate = plasmapheresis.getStartDate();
                        Date endDate = plasmapheresis.getEndDate();
                        if(startDate != null && endDate != null && startDate.compareTo(endDate) ==1) {
                          Component endDateComp =  get("endDate");
                            endDateComp.error("End date cannot be before start date.");
                        }
                    }
                };

        plasmapheresisForm.add(new RadarRequiredDateTextField("startDate", RadarApplication.DATE_PATTERN, plasmapheresisForm, plasmapheresisComponentsToUpdate));
        final RadarDateTextField endDate = new RadarDateTextField("endDate", RadarApplication.DATE_PATTERN, plasmapheresisForm, plasmapheresisComponentsToUpdate);
        plasmapheresisForm.add(endDate);

        PlasmapheresisExchangeUnit plasmapheresisTemp = new PlasmapheresisExchangeUnit();
        plasmapheresisTemp.setName("temp");

        plasmapheresisForm.add(new RadarRequiredDropdownChoice("plasmapheresisExchanges", Arrays.asList(plasmapheresisTemp), new ChoiceRenderer("name"), plasmapheresisForm, plasmapheresisComponentsToUpdate));
        plasmapheresisForm.add(new RadarRequiredDropdownChoice("response", Arrays.asList(RemissionAchieved.PARTIAL), new ChoiceRenderer(), plasmapheresisForm, plasmapheresisComponentsToUpdate));
        plasmapheresisForm.add(new AjaxSubmitLink("savePlasmapheresis") {
            @Override
            protected void onSubmit(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                ajaxRequestTarget.add(plasmapheresisComponentsToUpdate.toArray(new Component[plasmapheresisComponentsToUpdate.size()]));
            }

            @Override
            protected void onError(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
               ajaxRequestTarget.add(plasmapheresisComponentsToUpdate.toArray(new Component[plasmapheresisComponentsToUpdate.size()]));

            }
        });



        // Remission radio group
        RadioGroup<RemissionAchieved> remissionAchieved = new RadioGroup<RemissionAchieved>("remissionAchieved");
        remissionAchieved.add(new Radio<RemissionAchieved>("complete",
                new Model<RemissionAchieved>(RemissionAchieved.COMPLETE)));
        remissionAchieved
                .add(new Radio<RemissionAchieved>("partial", new Model<RemissionAchieved>(RemissionAchieved.PARTIAL)));
        remissionAchieved
                .add(new Radio<RemissionAchieved>("none", new Model<RemissionAchieved>(RemissionAchieved.NONE)));
        form.add(remissionAchieved);

        form.add(new RadarDateTextField("dateOfRemission", RadarApplication.DATE_PATTERN, form, componentsToUpdate));

        form.add(new AjaxSubmitLink("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                ajaxRequestTarget.add(componentsToUpdate.toArray(new Component[componentsToUpdate.size()]));
            }

            @Override
            protected void onError(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                ajaxRequestTarget.add(componentsToUpdate.toArray(new Component[componentsToUpdate.size()]));

            }
        });

        add(form);
        add(plasmapheresisForm);

    }

    @Override
    public boolean isVisible() {
        return ((PatientPage) getPage()).getCurrentTab().equals(PatientPage.CurrentTab.RELAPSE);
    }
}
