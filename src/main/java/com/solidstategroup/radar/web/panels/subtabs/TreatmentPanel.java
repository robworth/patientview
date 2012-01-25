package com.solidstategroup.radar.web.panels.subtabs;

import com.solidstategroup.radar.dao.DiagnosisDao;
import com.solidstategroup.radar.dao.ImmunosuppressionDao;
import com.solidstategroup.radar.dao.TherapyDao;
import com.solidstategroup.radar.model.Immunosuppression;
import com.solidstategroup.radar.model.ImmunosuppressionTreatment;
import com.solidstategroup.radar.model.Plasmapheresis;
import com.solidstategroup.radar.model.PlasmapheresisExchangeUnit;
import com.solidstategroup.radar.model.enums.RemissionAchieved;
import com.solidstategroup.radar.model.sequenced.Therapy;
import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.components.RadarComponentFactory;
import com.solidstategroup.radar.web.components.RadarDateTextField;
import com.solidstategroup.radar.web.components.RadarRequiredDateTextField;
import com.solidstategroup.radar.web.components.RadarRequiredDropdownChoice;
import com.solidstategroup.radar.web.dataproviders.ImmunosuppressionTreatmentDataProvider;
import com.solidstategroup.radar.web.dataproviders.PlasmapheresisDataProvider;
import com.solidstategroup.radar.web.models.RadarModelFactory;
import com.solidstategroup.radar.web.panels.firstvisit.YesNoRadioGroupPanel;
import com.solidstategroup.radar.web.panels.tables.DialysisTablePanel;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TreatmentPanel extends Panel {
    @SpringBean
    private TherapyDao therapyDao;
    @SpringBean
    private DiagnosisDao diagnosisDao;
    @SpringBean
    private ImmunosuppressionDao immunosuppressionDao;

    public TreatmentPanel(String id, final IModel<Long> radarNumberModel) {
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

        final List<Component> addImmunoSuppressComponentsToUpdate = new ArrayList<Component>();

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
                        new CompoundPropertyModel<ImmunosuppressionTreatment>(new ImmunosuppressionTreatment()),
                        addImmunoSuppressComponentsToUpdate);


        addImmunosuppressionForm.add(new AjaxSubmitLink("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                target.add(addImmunoSuppressComponentsToUpdate.toArray(
                        new Component[addImmunoSuppressComponentsToUpdate.size()]));
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(addImmunoSuppressComponentsToUpdate.toArray(
                        new Component[addImmunoSuppressComponentsToUpdate.size()]));
            }
        });
        add(addImmunosuppressionForm);


        // Drugs
        final List<Component> therapyFormComponentsToUpdate = new ArrayList<Component>();

        final CompoundPropertyModel<Therapy> therapyFormModel = new CompoundPropertyModel<Therapy>(new LoadableDetachableModel
                <Therapy>() {
            @Override
            public Therapy load() {
                Therapy therapyModelObject = null;

                if (radarNumberModel.getObject() != null) {
                    List<Therapy> therapies = therapyDao.getTherapyByRadarNumber(radarNumberModel.getObject());
                    therapyModelObject = !therapies.isEmpty() ? therapies.get(0) : null;
                }

                if (therapyModelObject == null) {
                    therapyModelObject = new Therapy();
                    therapyModelObject.setSequenceNumber(1);
                }
                return therapyModelObject;
            }
        });
        Form<Therapy> therapyForm = new Form<Therapy>("therapyForm", therapyFormModel) {
            @Override
            protected void onSubmit() {
                Therapy therapy = getModelObject();
                therapy.setRadarNumber(radarNumberModel.getObject());
                therapyDao.saveTherapy(therapy);
            }
        };

        final IModel<Boolean> isSrnsModel = RadarModelFactory.getIsSrnsModel(radarNumberModel, diagnosisDao);
        IModel firstColumnLabelModel = new LoadableDetachableModel() {
            @Override
            protected Object load() {
                return isSrnsModel.getObject() ? "Prior to Referral" : "Drugs in the 4 weeks after Biopsy";
            }
        };
        Label successLabel = RadarComponentFactory.getSuccessMessageLabel("successMessage", therapyForm,
                therapyFormComponentsToUpdate);

        Label errorLabel = RadarComponentFactory.getErrorMessageLabel("errorMessage", therapyForm,
                therapyFormComponentsToUpdate);

        RadarRequiredDateTextField treatmentRecordDate = new RadarRequiredDateTextField("treatmentRecordDate",
                therapyForm, therapyFormComponentsToUpdate);

        therapyForm.add(treatmentRecordDate);


        Label firstColumnLabel = new Label("firstColumnLabel", firstColumnLabelModel);
        therapyForm.add(firstColumnLabel);

        WebMarkupContainer currentContainer = new WebMarkupContainer("currentContainer") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject();
            }
        };
        therapyForm.add(currentContainer);

        WebMarkupContainer nsaidContainerParent = new WebMarkupContainer("nsaidContainerParent") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject();
            }
        };
        YesNoRadioGroupPanel nsaidContainer = new YesNoRadioGroupPanel("nsaidContainer", true, therapyFormModel, "nsaid");
        nsaidContainerParent.add(nsaidContainer);
        therapyForm.add(nsaidContainerParent);

        nsaidContainerParent.add(new YesNoRadioGroupPanel("nsaidPriorContainer", true, therapyFormModel, "nsaidPrior") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject();
            }
        });

        WebMarkupContainer diureticContainerParent = new WebMarkupContainer("diureticContainerParent") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject();
            }
        };
        ;
        YesNoRadioGroupPanel diureticContainer = new YesNoRadioGroupPanel("diureticContainer", true, therapyFormModel,
                "diuretic");

        diureticContainerParent.add(diureticContainer);
        therapyForm.add(diureticContainerParent);

        diureticContainerParent.add(new YesNoRadioGroupPanel("diureticPriorContainer",
                true, therapyFormModel, "diureticPrior") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject();
            }
        });

        boolean antihypertensiveToggleInit = (Boolean.FALSE.equals(therapyFormModel.getObject().getAntihypertensive())
                && Boolean.FALSE.equals(therapyFormModel.getObject().getAntihypertensivePrior())) ||
                (therapyFormModel.getObject().getAntihypertensive() == null &&
                        therapyFormModel.getObject().getAntihypertensivePrior() == null)
                ? false : true;
        final IModel<Boolean> antihypertensiveToggleModel = new Model<Boolean>(antihypertensiveToggleInit);

        AjaxFormChoiceComponentUpdatingBehavior antihypertensiveToggleBehaviour = new AjaxFormChoiceComponentUpdatingBehavior() {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                antihypertensiveToggleModel.setObject(therapyFormModel.getObject().getAntihypertensive());

                target.add(therapyFormComponentsToUpdate.toArray(new Component[therapyFormComponentsToUpdate.size()]));
            }
        };

        AjaxFormChoiceComponentUpdatingBehavior antihypertensiveToggleBehaviour2 = new AjaxFormChoiceComponentUpdatingBehavior() {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                antihypertensiveToggleModel.setObject(therapyFormModel.getObject().getAntihypertensivePrior());

                target.add(therapyFormComponentsToUpdate.toArray(new Component[therapyFormComponentsToUpdate.size()]));
            }
        };

        YesNoRadioGroupPanel antihypertensiveContainer = new YesNoRadioGroupPanel("antihypertensiveContainer", true, therapyFormModel,
                "antihypertensive", antihypertensiveToggleBehaviour);
        therapyForm.add(antihypertensiveContainer);


        therapyForm.add(new YesNoRadioGroupPanel("antihypertensivePriorContainer", true, therapyFormModel,
                "antihypertensivePrior", antihypertensiveToggleBehaviour2) {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject();
            }
        });

        WebMarkupContainer aceInhibitorContainer = new WebMarkupContainer("aceInhibitorContainer") {
            @Override
            public boolean isVisible() {
                return antihypertensiveToggleModel.getObject();
            }
        };

        therapyFormComponentsToUpdate.add(aceInhibitorContainer);
        aceInhibitorContainer.setOutputMarkupId(true);
        aceInhibitorContainer.setOutputMarkupPlaceholderTag(true);

        YesNoRadioGroupPanel aceInhibitorRadioGroup = new YesNoRadioGroupPanel("aceInhibitorRadioGroup",
                true, therapyFormModel, "aceInhibitor");
        aceInhibitorContainer.add(aceInhibitorRadioGroup);
        YesNoRadioGroupPanel aceInhibitorPriorRadioGroup = new YesNoRadioGroupPanel("aceInhibitorPriorRadioGroup",
                true, therapyFormModel,
                "aceInhibitorPrior") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject();
            }
        };
        aceInhibitorContainer.add(aceInhibitorPriorRadioGroup);
        therapyForm.add(aceInhibitorContainer);


        WebMarkupContainer arb1AntagonistContainer = new WebMarkupContainer("arb1AntagonistContainer") {
            @Override
            public boolean isVisible() {
                return antihypertensiveToggleModel.getObject();
            }
        };

        therapyFormComponentsToUpdate.add(arb1AntagonistContainer);
        arb1AntagonistContainer.setOutputMarkupId(true);
        arb1AntagonistContainer.setOutputMarkupPlaceholderTag(true);

        arb1AntagonistContainer.add(new YesNoRadioGroupPanel("arb1AntagonistRadioGroup", true, therapyFormModel, "arb1Antagonist"));
        arb1AntagonistContainer.add(new YesNoRadioGroupPanel("arb1AntagonistPriorRadioGroup", true, therapyFormModel,
                "arb1AntagonistPrior") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject();
            }
        });
        therapyForm.add(arb1AntagonistContainer);

        WebMarkupContainer calciumChannelBlockerContainer = new WebMarkupContainer("calciumChannelBlockerContainer") {
            @Override
            public boolean isVisible() {
                return antihypertensiveToggleModel.getObject();
            }
        };
        therapyFormComponentsToUpdate.add(calciumChannelBlockerContainer);
        calciumChannelBlockerContainer.setOutputMarkupId(true);
        calciumChannelBlockerContainer.setOutputMarkupPlaceholderTag(true);

        calciumChannelBlockerContainer.add(new YesNoRadioGroupPanel("calciumChannelBlockerRadioGroup", true, therapyFormModel,
                "calciumChannelBlocker"));
        calciumChannelBlockerContainer.add(new YesNoRadioGroupPanel("calciumChannelBlockerPriorRadioGroup", true, therapyFormModel,
                "calciumChannelBlockerPrior") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject();
            }
        });
        therapyForm.add(calciumChannelBlockerContainer);

        WebMarkupContainer betaBlockerContainer = new WebMarkupContainer("betaBlockerContainer") {
            @Override
            public boolean isVisible() {
                return antihypertensiveToggleModel.getObject();
            }
        };
        therapyFormComponentsToUpdate.add(betaBlockerContainer);
        betaBlockerContainer.setOutputMarkupId(true);
        betaBlockerContainer.setOutputMarkupPlaceholderTag(true);

        betaBlockerContainer.add(new YesNoRadioGroupPanel("betaBlockerRadioGroup", true, therapyFormModel, "betaBlocker"));
        betaBlockerContainer.add(new YesNoRadioGroupPanel("betaBlockerPriorRadioGroup", true, therapyFormModel,
                "betaBlockerPrior") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject();
            }
        });
        therapyForm.add(betaBlockerContainer);

        WebMarkupContainer otherAntihypertensiveContainer = new WebMarkupContainer("otherAntihypertensiveContainer") {
            @Override
            public boolean isVisible() {
                return antihypertensiveToggleModel.getObject();
            }
        };
        therapyFormComponentsToUpdate.add(otherAntihypertensiveContainer);
        otherAntihypertensiveContainer.setOutputMarkupId(true);
        otherAntihypertensiveContainer.setOutputMarkupPlaceholderTag(true);

        otherAntihypertensiveContainer.add(new YesNoRadioGroupPanel("otherAntihypertensiveRadioGroup", true, therapyFormModel,
                "otherAntihypertensive"));
        otherAntihypertensiveContainer.add(new YesNoRadioGroupPanel("otherAntihypertensivePriorRadioGroup", true, therapyFormModel,
                "otherAntihypertensivePrior") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject();
            }
        });
        therapyForm.add(otherAntihypertensiveContainer);//@current


        therapyForm.add(new YesNoRadioGroupPanel("insulinContainer", true, therapyFormModel, "insulin"));
        therapyForm.add(new YesNoRadioGroupPanel("insulinPriorContainer", true, therapyFormModel, "insulinPrior") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject();
            }
        });

        WebMarkupContainer lipidLoweringAgentContainerParent =
                new WebMarkupContainer("lipidLoweringAgentContainerParent") {
                    @Override
                    public boolean isVisible() {
                        return isSrnsModel.getObject();
                    }
                };
        lipidLoweringAgentContainerParent.add(new YesNoRadioGroupPanel("lipidLoweringAgentContainer", true, therapyFormModel,
                "lipidLoweringAgent"));
        lipidLoweringAgentContainerParent.add(new YesNoRadioGroupPanel("lipidLoweringAgentPriorContainer", true, therapyFormModel,
                "lipidLoweringAgentPrior") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject();
            }
        });
        therapyForm.add(lipidLoweringAgentContainerParent);

        therapyForm.add(new YesNoRadioGroupPanel("epoContainer", true, therapyFormModel, "epo"));
        therapyForm.add(new YesNoRadioGroupPanel("epoPriorContainer", true, therapyFormModel, "epoPrior") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject();
            }
        });

        therapyForm.add(new TextField("other1"));

        WebMarkupContainer other1PriorContainer = new WebMarkupContainer("other1PriorContainer") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject();
            }
        };
        other1PriorContainer.add(new TextField("other1Prior"));
        therapyForm.add(other1PriorContainer);

        therapyForm.add(new TextField("other2"));

        WebMarkupContainer other2PriorContainer = new WebMarkupContainer("other2PriorContainer") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject();
            }
        };
        other2PriorContainer.add(new TextField("other2Prior"));
        therapyForm.add(other2PriorContainer);

        therapyForm.add(new TextField("other3"));

        WebMarkupContainer other3PriorContainer = new WebMarkupContainer("other3PriorContainer") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject();
            }
        };
        other3PriorContainer.add(new TextField("other3Prior"));
        therapyForm.add(other3PriorContainer);

        therapyForm.add(new TextField("other4"));

        WebMarkupContainer other4PriorContainer = new WebMarkupContainer("other4PriorContainer") {
            @Override
            public boolean isVisible() {
                return isSrnsModel.getObject();
            }
        };
        other4PriorContainer.add(new TextField("other4Prior"));
        therapyForm.add(other4PriorContainer);


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
        @SpringBean
        private ImmunosuppressionDao immunosuppressionDao;

        private ImmunosuppressionTreatmentForm(String id, IModel<ImmunosuppressionTreatment> model, List<Component> componentsToUpdate) {
            super(id, model);
            RadarRequiredDateTextField startDate = new RadarRequiredDateTextField("startDate", this, componentsToUpdate);
            add(startDate);

            RadarRequiredDropdownChoice immunoSuppression = new RadarRequiredDropdownChoice("immunosuppression",
                    immunosuppressionDao.getImmunosuppressions(), new ChoiceRenderer("description", "id"),
                    this, componentsToUpdate);
            add(immunoSuppression);

            endDate = new RadarDateTextField("endDate", this, componentsToUpdate);
            add(endDate);
        }

        @Override
        protected void onValidateModelObjects() {
            super.onValidateModelObjects();
            ImmunosuppressionTreatment treatment = getModelObject();
            Date start = treatment.getStartDate();
            Date end = treatment.getEndDate();
            if (start != null && end != null && start.compareTo(end) != -1) {
                endDate.error("End date cannot be less than start date");
            }
        }
    }

    private final class PlasmapheresisForm extends Form<Plasmapheresis> {
        private RadarDateTextField endDate;

        private PlasmapheresisForm(String id, IModel<Plasmapheresis> model, List<Component> componentsToUpdate) {
            super(id, model);
            add(new RadarRequiredDateTextField("startDate", this, componentsToUpdate));
            endDate = new RadarDateTextField("endDate", this, componentsToUpdate);
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
                    this, componentsToUpdate));
        }

        @Override
        protected void onValidateModelObjects() {
            super.onValidateModelObjects();
            Plasmapheresis plasmapheresis = getModelObject();
            Date start = plasmapheresis.getStartDate();
            Date end = plasmapheresis.getEndDate();
            if (start != null && end != null && start.compareTo(end) != -1) {
                endDate.error("End date cannot be less than start date");
            }
        }
    }

}
