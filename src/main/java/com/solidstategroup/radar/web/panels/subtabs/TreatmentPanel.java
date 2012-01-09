package com.solidstategroup.radar.web.panels.subtabs;

import com.solidstategroup.radar.model.Immunosuppression;
import com.solidstategroup.radar.model.ImmunosuppressionTreatment;
import com.solidstategroup.radar.model.Plasmapheresis;
import com.solidstategroup.radar.model.PlasmapheresisExchangeUnit;
import com.solidstategroup.radar.model.enums.RemissionAchieved;
import com.solidstategroup.radar.model.sequenced.Therapy;
import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.components.RadarDatePicker;
import com.solidstategroup.radar.web.components.RadarDateTextField;
import com.solidstategroup.radar.web.components.RadarFormComponentFeedbackIndicator;
import com.solidstategroup.radar.web.components.RadarRequiredDateTextField;
import com.solidstategroup.radar.web.components.RadarRequiredDropdownChoice;
import com.solidstategroup.radar.web.dataproviders.ImmunosuppressionTreatmentDataProvider;
import com.solidstategroup.radar.web.dataproviders.PlasmapheresisDataProvider;
import com.solidstategroup.radar.web.panels.firstvisit.YesNoRadioGroupPanel;
import com.solidstategroup.radar.web.panels.tables.DialysisTablePanel;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TreatmentPanel extends Panel {
    public TreatmentPanel(String id) {
        super(id);

        // Immunosuppression including Monoclonals

        add(new DataView<ImmunosuppressionTreatment>("immunosuppressions",
                new ImmunosuppressionTreatmentDataProvider()) {
            @Override
            protected void populateItem(Item<ImmunosuppressionTreatment> item) {
                item.add(DateLabel.forDatePattern("startDate", RadarApplication.DATE_PATTERN));
                item.add(DateLabel.forDatePattern("endDate", RadarApplication.DATE_PATTERN));
                item.add(new Label("immunosuppression.description"));
                item.add(new AjaxLink("deleteLink") {
                    @Override
                    public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                        // Todo: Implement
                    }
                });
                item.add(new AjaxLink("editLink") {
                    @Override
                    public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                        // Todo: Implement
                    }
                });
            }
        });

        // For showing edit from ajax call
        MarkupContainer editContainer = new WebMarkupContainer("editContainer");
        editContainer.setOutputMarkupPlaceholderTag(true);
        editContainer.setVisible(false);

        final List<Component> editImmunoSuppressComponentsToUpdate = new ArrayList<Component>();

        // Construct the form
        ImmunosuppressionTreatmentForm editImmunosuppressionForm =
                new ImmunosuppressionTreatmentForm("editImmunosuppressionForm",
                        new CompoundPropertyModel<ImmunosuppressionTreatment>(new ImmunosuppressionTreatment()), new ArrayList<Component>());
        editImmunosuppressionForm.add(new AjaxSubmitLink("save") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                // Todo: Implement
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {

            }
        });
        editImmunosuppressionForm.add(new AjaxLink("cancel") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                // Todo: Implement
            }
        });
        editContainer.add(editImmunosuppressionForm);
        add(editContainer);

        // Construct the add form
        ImmunosuppressionTreatmentForm addImmunosuppressionForm =
                new ImmunosuppressionTreatmentForm("addImmunosuppressionForm",
                        new CompoundPropertyModel<ImmunosuppressionTreatment>(new ImmunosuppressionTreatment()), editImmunoSuppressComponentsToUpdate);


        addImmunosuppressionForm.add(new AjaxSubmitLink("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                target.add(editImmunoSuppressComponentsToUpdate.toArray(new Component[editImmunoSuppressComponentsToUpdate.size()]));
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(editImmunoSuppressComponentsToUpdate.toArray(new Component[editImmunoSuppressComponentsToUpdate.size()]));
            }
        });
        add(addImmunosuppressionForm);


        // Drugs
        final List<Component> therapyFormComponentsToUpdate = new ArrayList<Component>();

        Form<Therapy> therapyForm = new Form<Therapy>("therapyForm", new CompoundPropertyModel<Therapy>(new Therapy()));

        RadarRequiredDateTextField treatmentRecordDate = new RadarRequiredDateTextField("treatmentRecordDate",
                new Model<Date>(),
                RadarApplication.DATE_PATTERN, therapyForm, therapyFormComponentsToUpdate);

        therapyForm.add(treatmentRecordDate);
        therapyForm.add(new YesNoRadioGroupPanel("nsaid", true));
        therapyForm.add(new YesNoRadioGroupPanel("nsaidPrior", true));
        therapyForm.add(new YesNoRadioGroupPanel("diuretic", true));
        therapyForm.add(new YesNoRadioGroupPanel("diureticPrior", true));
        therapyForm.add(new YesNoRadioGroupPanel("antihypertensive", true));
        therapyForm.add(new YesNoRadioGroupPanel("antihypertensivePrior", true));
        therapyForm.add(new YesNoRadioGroupPanel("aceInhibitor", true));
        therapyForm.add(new YesNoRadioGroupPanel("aceInhibitorPrior", true));
        therapyForm.add(new YesNoRadioGroupPanel("arb1Antagonist", true));
        therapyForm.add(new YesNoRadioGroupPanel("arb1AntagonistPrior", true));
        therapyForm.add(new YesNoRadioGroupPanel("calciumChannelBlocker", true));
        therapyForm.add(new YesNoRadioGroupPanel("calciumChannelBlockerPrior", true));
        therapyForm.add(new YesNoRadioGroupPanel("betaBlocker", true));
        therapyForm.add(new YesNoRadioGroupPanel("betaBlockerPrior", true));
        therapyForm.add(new YesNoRadioGroupPanel("otherAntihypertensive", true));
        therapyForm.add(new YesNoRadioGroupPanel("otherAntihypertensivePrior", true));
        therapyForm.add(new YesNoRadioGroupPanel("insulin", true));
        therapyForm.add(new YesNoRadioGroupPanel("insulinPrior", true));
        therapyForm.add(new YesNoRadioGroupPanel("lipidLoweringAgent", true));
        therapyForm.add(new YesNoRadioGroupPanel("lipidLoweringAgentPrior", true));
        therapyForm.add(new YesNoRadioGroupPanel("epo", true));
        therapyForm.add(new YesNoRadioGroupPanel("epoPrior", true));

        therapyForm.add(new TextField("other1"));
        therapyForm.add(new TextField("other1Prior"));
        therapyForm.add(new TextField("other2"));
        therapyForm.add(new TextField("other2Prior"));
        therapyForm.add(new TextField("other3"));
        therapyForm.add(new TextField("other3Prior"));
        therapyForm.add(new TextField("other4"));
        therapyForm.add(new TextField("other4Prior"));


        AjaxSubmitLink save = new AjaxSubmitLink("save", therapyForm) {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                target.add(therapyFormComponentsToUpdate.toArray(new Component[therapyFormComponentsToUpdate.size()]));
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(therapyFormComponentsToUpdate.toArray(new Component[therapyFormComponentsToUpdate.size()]));
            }
        };

        therapyForm.add(save);

        add(therapyForm);

        // Plasmapheresis
        add(new DataView<Plasmapheresis>("plasmapheresis", new PlasmapheresisDataProvider()) {
            @Override
            protected void populateItem(Item<Plasmapheresis> item) {
                item.add(DateLabel.forDatePattern("dateStarted", RadarApplication.DATE_PATTERN));
                item.add(DateLabel.forDatePattern("dateStopped", RadarApplication.DATE_PATTERN));
                item.add(new Label("plasmapheresisExchanges.name"));
                item.add(new Label("response"));
                item.add(new AjaxLink("deleteLink") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        // Todo: Implement
                    }
                });
                item.add(new AjaxLink("editLink") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        // Todo: Implement
                    }
                });
            }
        });

        MarkupContainer editPlasmapheresisContainer = new WebMarkupContainer("editPlasmapheresisContainer");
        editPlasmapheresisContainer.setVisible(false);

        // Add the form
        PlasmapheresisForm editPlasmapheresisForm = new PlasmapheresisForm("editPlasmapheresisForm",
                new CompoundPropertyModel<Plasmapheresis>(new Plasmapheresis()), new ArrayList<Component>());
        editPlasmapheresisForm.add(new AjaxSubmitLink("save") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                // Todo: Implement
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                // Todo: Implement
            }
        });
        editPlasmapheresisForm.add(new AjaxLink("cancel") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                // Todo: Implement
            }
        });
        add(editPlasmapheresisContainer);

        final List<Component> plasmapheresisComponentsToUpdate = new ArrayList<Component>();
        // Add the add plasmapheresis form
        PlasmapheresisForm addPlasmapheresisForm = new PlasmapheresisForm("addPlasmapheresisForm",
                new CompoundPropertyModel<Plasmapheresis>(new Plasmapheresis()), plasmapheresisComponentsToUpdate);

        addPlasmapheresisForm.add(new AjaxSubmitLink("save") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                target.add(plasmapheresisComponentsToUpdate.toArray(new Component[plasmapheresisComponentsToUpdate.size()]));
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
               target.add(plasmapheresisComponentsToUpdate.toArray(new Component[plasmapheresisComponentsToUpdate.size()]));
            }
        });
        add(addPlasmapheresisForm);

        add(new DialysisTablePanel("dialysisContainer"));

    }

    private final class ImmunosuppressionTreatmentForm extends Form<ImmunosuppressionTreatment> {
        private RadarDateTextField endDate;
        private ImmunosuppressionTreatmentForm(String id, IModel<ImmunosuppressionTreatment> model, List<Component> componentsToUpdate) {
            super(id, model);
            RadarRequiredDateTextField startDate = new RadarRequiredDateTextField("startDate", RadarApplication.DATE_PATTERN, this, componentsToUpdate);
            add(startDate);

            List<Immunosuppression> immunosuppressionList = new ArrayList<Immunosuppression>();
            Immunosuppression immunosuppression = new Immunosuppression();
            immunosuppression.setDescription("temp");
            immunosuppressionList.add(immunosuppression);

            RadarRequiredDropdownChoice immunoSuppression = new RadarRequiredDropdownChoice("immunosuppression", immunosuppressionList, new ChoiceRenderer("description"), this, componentsToUpdate);
            add(immunoSuppression);

            endDate = new RadarDateTextField("endDate", RadarApplication.DATE_PATTERN, this, componentsToUpdate);
            add(endDate);
        }

        @Override
        protected void onValidateModelObjects() {
            super.onValidateModelObjects();
            ImmunosuppressionTreatment treatment = getModelObject();
            Date start = treatment.getStartDate();
            Date end = treatment.getEndDate();
            if(start != null && end != null && start.compareTo(end) == 1) {
                endDate.error("End date cannot be less than start date");
            }
        }
    }

    private final class PlasmapheresisForm extends Form<Plasmapheresis> {
        private RadarDateTextField endDate;
        private PlasmapheresisForm(String id, IModel<Plasmapheresis> model, List<Component> componentsToUpdate) {
            super(id, model);
            add(new RadarRequiredDateTextField("startDate", RadarApplication.DATE_PATTERN, this, componentsToUpdate));
            endDate = new RadarDateTextField("endDate", RadarApplication.DATE_PATTERN, this, componentsToUpdate);
            add(endDate);

            List<PlasmapheresisExchangeUnit> plasmapheresisExchangeUnitList =
                    new ArrayList<PlasmapheresisExchangeUnit>();
            PlasmapheresisExchangeUnit plasmapheresisExchangeUnit = new PlasmapheresisExchangeUnit();
            plasmapheresisExchangeUnit.setName("temp");
            plasmapheresisExchangeUnitList.add(plasmapheresisExchangeUnit);

            add(new RadarRequiredDropdownChoice("plasmapheresisExchanges", plasmapheresisExchangeUnitList,
                    new ChoiceRenderer("name"), this, componentsToUpdate));

            List<RemissionAchieved> remissionAchievedList =
                    new ArrayList<RemissionAchieved>();
            RemissionAchieved remissionAchieved = RemissionAchieved.COMPLETE;
            remissionAchievedList.add(remissionAchieved);

            add(new RadarRequiredDropdownChoice("response", remissionAchievedList, new ChoiceRenderer(),
                    this, componentsToUpdate ));
        }

        @Override
        protected void onValidateModelObjects() {
            super.onValidateModelObjects();
            Plasmapheresis plasmapheresis = getModelObject();
            Date start = plasmapheresis.getStartDate();
            Date end = plasmapheresis.getEndDate();
            if(start != null && end != null && start.compareTo(end) == 1) {
                endDate.error("End date cannot be less than start date");
            }
        }
    }

}
