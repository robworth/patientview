package com.solidstategroup.radar.web.panels.tables;

import com.solidstategroup.radar.model.Modality;
import com.solidstategroup.radar.model.Treatment;
import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.components.RadarDateTextField;
import com.solidstategroup.radar.web.components.RadarRequiredDateTextField;
import com.solidstategroup.radar.web.components.RadarRequiredDropdownChoice;
import com.solidstategroup.radar.web.dataproviders.DialysisDataProvider;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DialysisTablePanel extends Panel {

    public DialysisTablePanel(String id) {
        super(id);

        // Dialysis
        add(new DataView<Treatment>("dialysis", new DialysisDataProvider()) {
            @Override
            protected void populateItem(Item<Treatment> item) {
                item.add(new Label("treatmentModality.type"));
                item.add(DateLabel.forDatePattern("dateStarted", RadarApplication.DATE_PATTERN));
                item.add(DateLabel.forDatePattern("dateStopped", RadarApplication.DATE_PATTERN));
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

        // Edit dialysis container
        MarkupContainer editDialysisContainer = new WebMarkupContainer("editDialysisContainer");
        editDialysisContainer.setOutputMarkupPlaceholderTag(true);
        editDialysisContainer.setVisible(false);
        add(editDialysisContainer);

        DialysisForm editDialysisForm =
                new DialysisForm("editDialysisForm", new CompoundPropertyModel<Treatment>(new Treatment()),
                        new ArrayList<Component>());
        editDialysisForm.add(new AjaxSubmitLink("save") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                // Todo: Implement
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                // Todo: Implement
            }
        });
        editDialysisForm.add(new AjaxLink("cancel") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                // Todo: Implement
            }
        });
        editDialysisContainer.add(editDialysisForm);


        final List<Component> addDialysisFormComponentsToUpdate = new ArrayList<Component>();
        // Add dialysis form
        DialysisForm addDialysisForm =
                new DialysisForm("addDialysisForm", new CompoundPropertyModel<Treatment>(new Treatment()),
                        addDialysisFormComponentsToUpdate);
        addDialysisForm.add(new AjaxSubmitLink("save") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                target.add(addDialysisFormComponentsToUpdate
                        .toArray(new Component[addDialysisFormComponentsToUpdate.size()]));
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(addDialysisFormComponentsToUpdate
                        .toArray(new Component[addDialysisFormComponentsToUpdate.size()]));
            }
        });
        add(addDialysisForm);
    }

    private final class DialysisForm extends Form<Treatment> {
        private RadarDateTextField endDate;

        private DialysisForm(String id, IModel<Treatment> treatmentIModel, List<Component> componentsToUpdate) {
            super(id, treatmentIModel);

            List<Modality> modalityList = new ArrayList<Modality>();
            Modality modality = new Modality();
            modality.setType("temp");
            modalityList.add(modality);

            add(new RadarRequiredDropdownChoice("treatmentModality", modalityList, new ChoiceRenderer("type"), this,
                    componentsToUpdate));
            add(new RadarRequiredDateTextField("startDate", this, componentsToUpdate));
            endDate = new RadarDateTextField("endDate", this, componentsToUpdate);
            add(endDate);
        }

        @Override
        protected void onValidateModelObjects() {
            super.onValidateModelObjects();
            Treatment treatment = getModelObject();
            Date start = treatment.getStartDate();
            Date end = treatment.getEndDate();
            if (start != null && end != null && start.compareTo(end) != -1) {
                endDate.error("End date cannot be less than start date");
            }
        }
    }

}
