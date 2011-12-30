package com.solidstategroup.radar.web.panels.subtabs;

import com.solidstategroup.radar.model.Immunosuppression;
import com.solidstategroup.radar.model.ImmunosuppressionTreatment;
import com.solidstategroup.radar.model.Plasmapheresis;
import com.solidstategroup.radar.model.PlasmapheresisExchangeUnit;
import com.solidstategroup.radar.model.Therapy;
import com.solidstategroup.radar.model.enums.RemissionAchieved;
import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.dataproviders.ImmunosuppressionTreatmentDataProvider;
import com.solidstategroup.radar.web.dataproviders.PlasmapheresisDataProvider;
import com.solidstategroup.radar.web.panels.firstvisit.YesNoRadioGroupPanel;
import com.solidstategroup.radar.web.panels.tables.DialysisTablePanel;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

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

        // Construct the form
        ImmunosuppressionTreatmentForm editImmunosuppressionForm =
                new ImmunosuppressionTreatmentForm("editImmunosuppressionForm",
                        new CompoundPropertyModel<ImmunosuppressionTreatment>(new ImmunosuppressionTreatment()));
        editImmunosuppressionForm.add(new AjaxSubmitLink("save") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                // Todo: Implement
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                // Todo: Implement
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
                        new CompoundPropertyModel<ImmunosuppressionTreatment>(new ImmunosuppressionTreatment()));
        addImmunosuppressionForm.add(new AjaxSubmitLink("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                // Todo: Implement
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                // Todo: Implement
            }
        });
        add(addImmunosuppressionForm);

        // Drugs
        Form<Therapy> therapyForm = new Form<Therapy>("therapyForm", new CompoundPropertyModel<Therapy>(new Therapy()));
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
                new CompoundPropertyModel<Plasmapheresis>(new Plasmapheresis()));
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

        // Add the add plasmapheresis form
        PlasmapheresisForm addPlasmapheresisForm = new PlasmapheresisForm("addPlasmapheresisForm",
                new CompoundPropertyModel<Plasmapheresis>(new Plasmapheresis()));
        addPlasmapheresisForm.add(new AjaxSubmitLink("save") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                // Todo: Implement
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                // Todo: Implement
            }
        });
        add(addPlasmapheresisForm);

        add(new DialysisTablePanel("dialysisContainer"));

    }

    private class ImmunosuppressionTreatmentForm extends Form<ImmunosuppressionTreatment> {
        private ImmunosuppressionTreatmentForm(String id, IModel<ImmunosuppressionTreatment> model) {
            super(id, model);
            add(DateTextField.forDatePattern("startDate", RadarApplication.DATE_PATTERN));
            add(new DropDownChoice<Immunosuppression>("immunosuppression"));
            add(DateTextField.forDatePattern("endDate", RadarApplication.DATE_PATTERN));
        }
    }

    private class PlasmapheresisForm extends Form<Plasmapheresis> {
        private PlasmapheresisForm(String id, IModel<Plasmapheresis> model) {
            super(id, model);
            add(DateTextField.forDatePattern("startDate", RadarApplication.DATE_PATTERN));
            add(DateTextField.forDatePattern("endDate", RadarApplication.DATE_PATTERN));
            add(new DropDownChoice<PlasmapheresisExchangeUnit>("plasmapheresisExchanges"));
            add(new DropDownChoice<RemissionAchieved>("response"));
        }
    }

}
